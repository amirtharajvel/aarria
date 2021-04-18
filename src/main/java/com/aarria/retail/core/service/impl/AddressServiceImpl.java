package com.aarria.retail.core.service.impl;

import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.core.service.AddressService;
import com.aarria.retail.persistence.repository.AddressRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
