package com.poly.ASSIGNMENT_JAVA5.service;

import com.poly.ASSIGNMENT_JAVA5.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
}
