package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.SizeMapper;
import kg.demo.dodo.model.dto.SizeDTO;
import kg.demo.dodo.model.entity.Size;
import kg.demo.dodo.model.response.SizeListResponse;
import kg.demo.dodo.repository.SizeRep;
import kg.demo.dodo.service.SizeService;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl extends BaseServiceImpl<Size, SizeRep, SizeDTO, SizeMapper> implements SizeService {
    public SizeServiceImpl(SizeRep rep, SizeMapper mapper) {
        super(rep, mapper);
    }

    @Override
    public String create(String name, int lang) {

        SizeDTO dto = new SizeDTO();
        dto.setName(name);
        save(dto);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");
    }

    @Override
    public List<SizeListResponse> getSizeList() {
        return rep.findALLName();
    }
}
