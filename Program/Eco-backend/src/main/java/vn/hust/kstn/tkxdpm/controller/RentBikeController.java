package vn.hust.kstn.tkxdpm.controller;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.kstn.tkxdpm.entity.BikeEntity;
import vn.hust.kstn.tkxdpm.entity.RenttransactionEntity;
import vn.hust.kstn.tkxdpm.repository.RentTransactionRepository;
import vn.hust.kstn.tkxdpm.requestInterface.RequestModel;
import vn.hust.kstn.tkxdpm.utils.BarcodeUtils;
import vn.hust.kstn.tkxdpm.utils.BikeTypeUtils;
import vn.hust.kstn.tkxdpm.utils.FeeCalculator;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * Lớp điều khiển các tác vụ liên quan đến thông tin thuê xe, trả xe.
 * Tạo, định nghĩa restAPI tương ứng để FrontEnd có thể tương tác đến
 */
@Slf4j
@RestController
public class RentBikeController {

    /**
     * The Rent transaction repository.
     */
    @Autowired
    RentTransactionRepository rentTransactionRepository ;

    /**
     * Method định nghĩa API để front-end truy vấn thông tin trả xe
     * Đường dẫn API : /returnDetail
     * Request Method : Post
     *
     * @param model  tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là thông tin trả xe tương ứng với thông tin truyền vào
     */
    @PostMapping(value = "/returnDetail" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getReturnDetailControl(@RequestBody RequestModel model){
        log.info("New request path /returnDetail");
        try {
            String output = getReturnDetail(model);
            return output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  null ;
    }

    /**
     * Method định nghĩa API để front-end truy vấn thông tin của xe đang thuê
     * Đường dẫn API : /getRentTransaction
     * Request Method : Post
     *
     * @param model  tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là thông tin thuê xe tương ứng với thông tin truyền vào
     */
    @PostMapping(value = "/getRentTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public String getRentTransactionControl(@RequestBody RequestModel model){
        log.info("New request path /getRentTransaction");
        try {
            String output = getRentTransaction(model);
            return output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  null ;
    }

    /**
     * Hàm lấy thông tin dùng để trả xe.
     *
     * @param requestModel tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là tt xe trả tương ứng với thông tin truyền vào
     */
    private String getReturnDetail(RequestModel requestModel){
        JsonObject jsonObject = new JsonObject();
        String cardID = requestModel.getCardID() ;
        String barcode = requestModel.getBarcode() ;
        requestModel.setBarcode(BarcodeUtils.getBikeIDbyBarCode(barcode));
        String bikeID = requestModel.getBarcode();
        try {
            List<RenttransactionEntity> renttransactionEntity = rentTransactionRepository.findAllByCardId(Long.parseLong(cardID));
            // If transaction exist
            if (renttransactionEntity != null){
                RenttransactionEntity renttransaction = renttransactionEntity.get(renttransactionEntity.size()-1);
                BikeEntity bikeEntity = renttransaction.getBikeByBikeId();
                // double check if returning bike is rented by , or not
                if (bikeEntity.getBikeId() != Long.parseLong(bikeID)){
                    jsonObject.addProperty("Error", "Bike id is not matching with renting transaction");
                    System.out.println(bikeEntity.getBikeId());
                } else {
                    Timestamp returnTime = new Timestamp(System.currentTimeMillis());
                    Timestamp rentTime = new Timestamp(returnTime.getTime() - renttransaction.getStartTime().getTime());
                    long count = new FeeCalculator().calculateFee(renttransaction);

                    // form output
                    jsonObject.addProperty("Mã vạch", renttransaction.getBikeId());
                    jsonObject.addProperty("Loại xe", BikeTypeUtils.translateType(bikeEntity.getType()));
                    jsonObject.addProperty("Thời gian thuê", new Time(rentTime.getTime()).toString());
                    jsonObject.addProperty("Giá thành tiền", count + " VND");
                    jsonObject.addProperty("Đã cọc", bikeEntity.getUpfrontPrice());
                    jsonObject.addProperty("Số tiền nhận lại sau trả xe", bikeEntity.getUpfrontPrice() -count);
                }
            }
            return  jsonObject.toString();
        } catch (Exception e){
            log.error(e.getMessage(),e);
            jsonObject.addProperty("Error", "Something went wrong");
            return  jsonObject.toString() ;
        }
    }

    /**
     * Hàm lấy thông tin của xe đang được thuê
     *
     * @param idModel tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là thông tin của xe đang được thuê tương ứng
     */
    private String getRentTransaction(RequestModel idModel){
        JsonObject jsonObject = new JsonObject() ;
        String id = idModel.getCardID() ;
        log.info("ID :{}", id);
        try {
            List<RenttransactionEntity> renttransactionEntity = rentTransactionRepository.findAllByCardId(Long.parseLong(id));
            if (renttransactionEntity != null){
                RenttransactionEntity renttransaction = renttransactionEntity.get(0);
                BikeEntity bikeEntity = renttransaction.getBikeByBikeId();
                jsonObject.addProperty("Mã vạch", renttransaction.getBikeId());
                jsonObject.addProperty("Loại xe", BikeTypeUtils.translateType(bikeEntity.getType()));
                jsonObject.addProperty("Số tiền tạm tính", new FeeCalculator().calculateFee(renttransaction) + " VND");
                if (bikeEntity.getType() ==3) {
//                    log.info(renttransaction.getBikeId());
                    jsonObject.addProperty("Thời gian còn lại ước tính", bikeEntity.getMaxTimeUsed().toString());
                }
                jsonObject.addProperty("TimeStamp", renttransaction.getStartTime().getTime());
                log.info("Response string {}", jsonObject.toString());
            }
            return  jsonObject.toString();
        } catch (Exception e){
            jsonObject.addProperty("Error", "Something went wrong");
        }
        return jsonObject.toString();
    }

}
