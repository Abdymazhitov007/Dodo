package kg.demo.dodo.service;

import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;
import kg.demo.dodo.model.response.UserInfoResponse;

import java.util.Map;

public interface AuthService {

    String auth(AuthRequest request, int lang);

    boolean isValidEmail(String email);

    String validate(ValidateEmailReq request, int lang);

    Long getUserIdByToken(String accessToken, int lang);

    UserInfoResponse getUserInfoByToken(String accessToken, int lang);
}
