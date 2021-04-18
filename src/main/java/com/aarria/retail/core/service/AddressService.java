package com.aarria.retail.core.service;


import com.aarria.retail.core.domain.Address;
import com.aarria.retail.core.domain.User;
import com.aarria.retail.web.dto.response.LatLongResponseDto;

import java.util.List;

public interface AddressService {

	public List<Address> findByUser(User user);

	public Address saveAddress(Address address);

	public Address deliverHere(User user, Long id);

	public Address getDeliveryAddress(User user);

	Address findOne(Long id);
	
	void delete(Long id);
	
	void delete(Long id, User user);

}
