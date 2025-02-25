package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.dto.request.AddressCreationRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.request.AddressUpdateRequest;
import com.poly.ASSIGNMENT_JAVA5.dto.response.AddressResponse;
import com.poly.ASSIGNMENT_JAVA5.entity.Address;
import com.poly.ASSIGNMENT_JAVA5.mapper.AddressMapper;
import com.poly.ASSIGNMENT_JAVA5.repository.AddressRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService {
    AddressRepository addressRepository;
    AddressMapper addressMapper;
    //Get
    public List<AddressResponse> getAll(){
        return addressRepository.findAll().stream().map(addressMapper::toAddressResponse).collect(Collectors.toList());
    }
    public Optional<AddressResponse> getByID(Long id){
        return addressRepository.findById(id).map(addressMapper::toAddressResponse);
    }
    public List<Address> getAllByUserId(Long userID){
        return addressRepository.findAllByUser_Id(userID);
    }
    //Put
    public AddressResponse update(Long id, AddressUpdateRequest request){
        Address address = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
        addressMapper.updateAddress(address,request);
        return addressMapper.toAddressResponse(addressRepository.save(address));
    }
    //Post
    public AddressResponse create(AddressCreationRequest request){
        Address address = addressMapper.toAddress(request);
        return addressMapper.toAddressResponse(addressRepository.save(address));
    }
    //Del
    public void delete(Long id){
        addressRepository.deleteById(id);
    }
}
 