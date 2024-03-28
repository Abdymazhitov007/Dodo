package kg.demo.dodo.service;

import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;

public interface AuthService {

    String auth(AuthRequest request);

    String validate(ValidateEmailReq request);

    Long getUserIdByToken(String token);

}
