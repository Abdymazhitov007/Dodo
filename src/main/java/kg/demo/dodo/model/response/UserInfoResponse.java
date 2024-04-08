package kg.demo.dodo.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoResponse {

    String name;
    String email;
    Double dodoCoins;
    Integer numOfOrders;
    Integer numOfAddress;


}
