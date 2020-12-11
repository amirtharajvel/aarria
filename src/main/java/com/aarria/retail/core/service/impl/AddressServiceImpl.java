package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.AddressService;
import com.aarria.retail.core.util.Util;
import com.aarria.retail.persistence.repository.AddressRepository;
import com.aarria.retail.web.dto.response.AddressDto;
import com.aarria.retail.web.dto.response.LatLongResponseDto;
import com.aarria.retail.web.dto.response.geocode.AddressComponent;
import com.aarria.retail.web.dto.response.geocode.GeoCode;
import com.aarria.retail.web.dto.response.geocode.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private static Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findOne(Long id) {
        return addressRepository.findById(id).get();
    }

    @Override
    public List<Address> findByUser(User user) {
        return addressRepository.findAddresses(user);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address deliverHere(User user, Long id) {
        List<Address> addresses = addressRepository.findAddresses(user);
        Address deliverHere = null;
        if (CollectionUtils.isNotEmpty(addresses)) {
            for (Address address : addresses) {
                if (address.getId().equals(id)) {
                    address.setIsDeliverHere(true);
                    address.setUpdatedDate(Timestamp.from(Instant.now()));
                    deliverHere = address;
                } else {
                    address.setIsDeliverHere(false);
                }
            }
        }

        addressRepository.saveAll(addresses);
        return deliverHere;
    }

    @Override
    public Address getDeliveryAddress(User user) {
        List<Address> addresses = addressRepository.findTop1ByUserOrderByUpdatedDateDesc(user);
        return addresses.get(0);
    }

    @Override
    public void delete(Long id) {
        Address address = new Address();
        address.setId(id);
        addressRepository.delete(address);
    }

    @Override
    public void delete(Long id, User user) {
        List<Address> addresses = addressRepository.findAddresses(user);
        if (CollectionUtils.isNotEmpty(addresses)) {
            for (int i = 0; i < addresses.size(); i++) {
                Address address = addresses.get(i);
                try {
                    if (address.getId().equals(id)) {
                        addressRepository.delete(address);
                        addresses.remove(address);
                    }
                } catch (Exception e) {
                    LOGGER.error("Unable to delete the address ", e);
                }
            }
        }

        if (addresses.size() > 1) {
            Address currentAddress = addresses.get(0);
            currentAddress.setIsDeliverHere(true);
            saveAddress(currentAddress);
        }
    }

    @Override
    public LatLongResponseDto getAddressFromLatLang(String latLang, User user) {

        GeoCode code = Util.getAddressFromLatLang(latLang);
        if (code != null) {
            AddressDto dto = createDtoFromAddressFromGoogle(code);
            if (dto != null) {

                // while detecting location by default set the user mobile itself
                return new LatLongResponseDto(dto.getDeliveryArea(), user.getMobile());
            }
        }

        return null;
    }

    private AddressDto createDtoFromAddressFromGoogle(GeoCode a) {

        AddressDto dto = null;
        try {
            if (a == null) {
                return null;
            }

            if (CollectionUtils.isNotEmpty(a.getResults())) {

                dto = new AddressDto();

                for (Result result : a.getResults()) {

                    List<AddressComponent> components = result.getAddressComponents();

                    if (CollectionUtils.isNotEmpty(components)) {

                        for (AddressComponent component : components) {

                            if (CollectionUtils.isNotEmpty(component.getTypes())) {
                                for (String type : component.getTypes()) {
                                    if (StringUtils.isEmpty(dto.getPincode()) && "postal_code".equalsIgnoreCase(type)) {
                                        try {
                                            dto.setPincode(Integer.valueOf(component.getLongName()));
                                        } catch (Exception e) {
                                            LOGGER.error("Unable to convert pincode to string " + e);
                                        }
                                    }

                                }
                            }
                        }
                    }

                    if (StringUtils.isEmpty(dto.getDeliveryArea())) {
                        dto.setDeliveryArea(result.getFormattedAddress());
                    } else if (dto.getDeliveryArea() != null && result.getFormattedAddress() != null
                            && (dto.getDeliveryArea().length() < result.getFormattedAddress().length())) {
                        dto.setDeliveryArea(result.getFormattedAddress());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Unable to fetch coordinates " + e);
        }

        return dto;
    }
}
