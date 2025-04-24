package com.workintech.s18d4.service;


import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if(optionalAddress.isPresent())
        {
            return optionalAddress.get();
        }
        return null;
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Address updatingAddress = find(id);
        updatingAddress.setId(address.getId());
        updatingAddress.setNo(address.getNo());
        updatingAddress.setStreet(address.getStreet());
        updatingAddress.setDescription(address.getDescription());
        updatingAddress.setCountry(address.getCountry());
        updatingAddress.setCity(address.getCity());
        updatingAddress.setCustomer(address.getCustomer());
        return addressRepository.save(updatingAddress);
    }

    @Override
    public Address delete(Long id) {
        Address address = find(id);
        addressRepository.deleteById(id);
        return address;
    }
}
