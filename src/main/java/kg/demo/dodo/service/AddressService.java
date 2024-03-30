package kg.demo.dodo.service;

import kg.demo.dodo.base.BaseService;
import kg.demo.dodo.model.dto.AddressDTO;
import kg.demo.dodo.model.requests.AddressCreateRequest;
import kg.demo.dodo.model.response.AddressListResponse;
import kg.demo.dodo.model.response.AddressResponse;

import java.util.List;

public interface AddressService extends BaseService<AddressDTO> {
    String create(AddressCreateRequest request, int lang);

    List<AddressListResponse> getByUserId(String token);
}
