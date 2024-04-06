package kg.demo.dodo.service;

import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;

public interface AuthService {

    String auth(AuthRequest request, int lang);

    String validate(ValidateEmailReq request, int lang);

    Long getUserIdByToken(String token, int lang);

}
