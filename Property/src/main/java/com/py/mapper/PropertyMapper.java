package com.py.mapper;

import org.springframework.stereotype.Component;
import com.py.entity.Property;
import com.py.dto.response.PropertyResponse;
import com.py.dto.response.OwnerDTO;

@Component
public class PropertyMapper {

    public PropertyResponse toResponse(Property property, OwnerDTO owner) {
        PropertyResponse response = new PropertyResponse();
        response.setId(property.getId());
        response.setRegNum(property.getRegNum());
        response.setType(property.getType());
        response.setBhk(property.getBhk());
        response.setHouseNum(property.getHouseNum());
        response.setStreetNum(property.getStreetNum());
        response.setCity(property.getCity());
        response.setPincode(property.getPincode());
        response.setDeposit(property.getDeposit());
        response.setRent(property.getRent());
        response.setOwner(owner);
        return response;
    }
}
