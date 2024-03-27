package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.SizeMapper;
import kg.demo.dodo.model.dto.SizeDTO;
import kg.demo.dodo.model.entity.Size;
import kg.demo.dodo.repository.SizeRep;
import kg.demo.dodo.service.SizeService;
import org.springframework.stereotype.Service;

@Service
public class SizeServiceImpl extends BaseServiceImpl<Size, SizeRep, SizeDTO, SizeMapper> implements SizeService {
    public SizeServiceImpl(SizeRep rep, SizeMapper mapper) {
        super(rep, mapper);
    }
}
