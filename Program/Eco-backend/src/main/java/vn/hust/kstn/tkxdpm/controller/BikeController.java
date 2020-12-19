package vn.hust.kstn.tkxdpm.controller;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.kstn.tkxdpm.entity.BikeEntity;
import vn.hust.kstn.tkxdpm.repository.BikeRepository;
import vn.hust.kstn.tkxdpm.requestInterface.RequestModel;
import vn.hust.kstn.tkxdpm.utils.BarcodeUtils;
import vn.hust.kstn.tkxdpm.utils.BikeTypeUtils;
import vn.hust.kstn.tkxdpm.utils.HttpConnector;

import java.text.SimpleDateFormat;

/**
 * Lớp điều khiển các tác vụ liên quan đến truy vấn đối tượng xe.
 * Tạo,định nghĩa restAPI tương ứng để FrontEnd có thể tương tác được
 */
@Slf4j
@RestController
public class BikeController {
    /**
     * The Bike repository.
     */
    @Autowired
    private BikeRepository bikeRepository ;
    /**
     * The Barcode api.
     */
    private String barcodeAPI = "https://barcodeservicebykv2.herokuapp.com/barcode" ;
    /**
     * The Http connector.
     */
    private HttpConnector httpConnector = new HttpConnector();

    /**
     * Method tạo API để front-end truy vấn thông tin xe
     * Đường dẫn API : /getBikeByID
     * Request Method : Post
     *
     * @param model tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string tương ứng với thông tin xe cần truy vấn
     */
    @PostMapping(value = "/getBikeByBarcode", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public String getBikeControl(@RequestBody RequestModel model){
        log.info("New request path /getBikeByBarcode");
        try {
            String barcode = model.getBarcode() ;
            model.setBarcode(BarcodeUtils.getBikeIDbyBarCode(barcode));
            String output =  getBikeByBarCode(model);
            log.info(output);
            return output ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return null ;
        }
    }

    /**
     * Method có nhiệm vụ lấy thông tin xe tương ứng với thông tin barcode truyền vào.
     *
     * @param model tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return Kết quả trả về là chuỗi json
     */
    public String getBikeByBarCode(RequestModel model){
        JsonObject bike = new JsonObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");

        log.info(""+ model.getBarcode());
        try {
            long id = Long.parseLong(model.getBarcode()) ;
            BikeEntity bikeEntity = bikeRepository.getOne(id);
            if (bikeEntity != null && bikeEntity.getIsAvailable() == 1) {
                bike.addProperty("Mã vạch", bikeEntity.getBikeId());
                bike.addProperty("Loại xe", BikeTypeUtils.translateType(bikeEntity.getType()));
                bike.addProperty("Giá thuê", bikeEntity.getRentPrice()+ " VND/Giờ");
                bike.addProperty("Thời gian sử dụng tối đa", bikeEntity.getMaxTimeUsed().toString());
                bike.addProperty("Sử dụng lần cuối ", simpleDateFormat.format(bikeEntity.getLastUsed()));
                bike.addProperty("Giá cọc", bikeEntity.getUpfrontPrice()+ " VND");
                return bike.toString();
            } else if (bikeEntity != null && bikeEntity.getIsAvailable() == 0) {
                bike.addProperty("id", id);
                bike.addProperty("error", "Rất tiếc :'( Xe bạn chọn đã được thuê bởi người dùng khác !!");
                return  bike.toString();
            } else {
                bike.addProperty("id", id);
                bike.addProperty("error", "Không tìm thấy xe phù hợp với mã vạch !!");
                return bike.toString();
            }
        } catch (Exception e){
            bike.addProperty("id", model.getBarcode());
            bike.addProperty("error", "Không tìm thấy xe phù hợp với mã vạch !!");
        }
        return bike.toString() ;
    }
}
