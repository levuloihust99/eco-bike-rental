package vn.hust.kstn.tkxdpm.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Lớp định nghĩa đối tượng requestModel, chứa các thông tin front-end gửi lên thông qua restAPI
 * Parse thông tin gửi lên, bỏ qua các trường null
 */
@Slf4j
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestModel implements Serializable {
    /**
     * The Card id.
     */
    String cardID ;
    /**
     * The Bike id.
     */
    String bikeID ;
    /**
     * The Parking lot id.
     */
    String parkingLotID ;
    /**
     * The Card code.
     */
    String cardCode ;
    /**
     * The User name.
     */
    String userName ;
    /**
     * The Cvv.
     */
    String cvv ;
    /**
     * The Expire date.
     */
    String expireDate ;
}
