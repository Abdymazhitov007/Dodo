package kg.demo.dodo.service.impl;

import kg.demo.dodo.base.BaseServiceImpl;
import kg.demo.dodo.mapper.AddressMapper;
import kg.demo.dodo.model.dto.AddressDTO;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.entity.Address;
import kg.demo.dodo.model.requests.AddressCreateRequest;
import kg.demo.dodo.model.response.AddressListResponse;
import kg.demo.dodo.repository.AddressRep;
import kg.demo.dodo.service.AddressService;
import kg.demo.dodo.service.AuthService;
import kg.demo.dodo.service.UserService;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends BaseServiceImpl<Address, AddressRep, AddressDTO, AddressMapper> implements AddressService {

    private final UserService userService;
    private final AuthService authService;

    public AddressServiceImpl(AddressRep rep, AddressMapper mapper, UserService userService, AuthService authService) {
        super(rep, mapper);
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    public List<AddressListResponse> getAllByToken(String accessToken, int lang) {
        Long userId = authService.getUserIdByToken(accessToken, lang);
        return rep.findAddressList(userId);

    }


    @Override
    public String create(AddressCreateRequest request, String accessToken, int lang) {

        UserDTO userDTO = userService.findById(authService.getUserIdByToken(accessToken, lang), lang);

        AddressDTO dto = new AddressDTO();
        dto.setCity(request.getCity());
        dto.setStreet(request.getStreet());
        dto.setNum(request.getNum());
        dto.setComment(request.getComment());
        dto.setUser(userDTO);

        save(dto);

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "entityCreated");
    }
}
