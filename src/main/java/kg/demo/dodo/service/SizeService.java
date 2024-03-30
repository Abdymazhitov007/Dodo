package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.SizeDTO;
import kg.demo.dodo.model.response.SizeListResponse;

import java.util.List;

public interface SizeService extends BaseService<SizeDTO> {
    String create(String name, int lang);

    List<SizeListResponse> getSizeList();
}
