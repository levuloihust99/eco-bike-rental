package vn.hust.kstn.tkxdpm.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.kstn.tkxdpm.entity.ParkinglotEntity;
import vn.hust.kstn.tkxdpm.repository.ParkingLotRepository;

import java.util.List;

/**
 * Lớp điều khiển các tác vụ liên quan đến truy vấn Các Trạm gửi xe.
 * Tạo, định nghĩa restAPI tương ứng để FrontEnd có thể tương tác đến
 */
@Slf4j
@RestController
public class ParkingLotController {
    /**
     * The Parking lot repository.
     */
    @Autowired
    ParkingLotRepository parkingLotRepository ;

    /**
     * Method định nghĩa API để front-end truy vấn thông tin xe
     * Đường dẫn API : /listParkingLot
     * Request Method : Get
     *
     * @return kết quả trả về là chuỗi json array dạng string là danh sách các bãi xe có trong hệ thống
     */
    @GetMapping(value = "/listParkingLot" , produces = MediaType.APPLICATION_JSON_VALUE)
    public String parkingList(){
        log.info("New request path /listParkingLot");
        try {
            return listParkingLot();
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null ;
        }
    }

    /**
     * Hàm lấy thông tin danh sách các bãi xe trong hệ thống
     *
     * @return Danh sách các bãi xe có trong hệ thống dưới dạng chuỗi JsonArray
     */
    public  String listParkingLot(){
        JsonArray jsonArr = new JsonArray();
        try {
            List<ParkinglotEntity> parkingList = parkingLotRepository.findAll();
            for (ParkinglotEntity p : parkingList) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", p.getParkingLotId());
                jsonObject.addProperty("name", p.getName());
                jsonObject.addProperty("address", p.getAddress());
                jsonArr.add(jsonObject);
            }
            return jsonArr.toString();
        } catch (Exception e){
            return  jsonArr.toString();
        }
    }


}
