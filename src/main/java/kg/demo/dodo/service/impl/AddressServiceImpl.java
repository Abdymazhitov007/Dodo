package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.AddressMapper;
import kg.demo.dodo.model.dto.AddressDTO;
import kg.demo.dodo.model.entity.Address;
import kg.demo.dodo.repository.AddressRep;
import kg.demo.dodo.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRep, AddressDTO, AddressMapper> implements AddressService {

    public AddressServiceImpl(AddressRep rep, AddressMapper mapper) {
        super(rep, mapper);
    }
}
