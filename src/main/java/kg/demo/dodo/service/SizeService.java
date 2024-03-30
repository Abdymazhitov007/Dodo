package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.SizeDTO;

import java.util.List;

public interface SizeService extends BaseService<SizeDTO> {
    String create(String name, int lang);

    List<String> getSizeList();
}
