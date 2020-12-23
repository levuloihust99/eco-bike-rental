package vn.hust.kstn.tkxdpm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankInterface;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem.InterBankTransaction;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem.InterbankSystemController;
import vn.hust.kstn.tkxdpm.entity.*;
import vn.hust.kstn.tkxdpm.repository.*;
import vn.hust.kstn.tkxdpm.requestInterface.RequestModel;
import vn.hust.kstn.tkxdpm.utils.BikeTypeUtils;
import vn.hust.kstn.tkxdpm.utils.FeeCalculator;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Lớp điều khiển các tác vụ liên quan đến Thanh toán hóa đơn thuê/trả xe.
 * Định nghĩa restAPI tương ứng để FrontEnd có thể tương tác đến
 */
@Slf4j
@RestController
public class PaymentController {
    /**
     * The Parking lot repository.
     */
    @Autowired
    ParkingLotRepository parkingLotRepository ;

    /**
     * The Bike repository.
     */
    @Autowired
    BikeRepository bikeRepository ;

    /**
     * The Rent transaction repository.
     */
    @Autowired
    RentTransactionRepository rentTransactionRepository ;

    /**
     * The Return transaction repository.
     */
    @Autowired
    ReturnTransactionRepository returnTransactionRepository;

    /**
     * The Card repository.
     */
    @Autowired
    CardRepository cardRepository;

    /**
     * The Interbank subsystem.
     */
    public InterbankInterface interbankSubsystem = new InterbankSystemController();
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * Method định nghĩa API để front-end yêu cầu thanh toán cọc
     * Đường dẫn API : /payUpfront
     * Request Method : Post
     *
     * @param requestModel  tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là kết quả thực hiện giao dịch
     */
    @PostMapping(value = "/payUpfront", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public String payUpFrontControl(@RequestBody RequestModel requestModel) {
        log.info("New request path /payUpfront");
        try {
            log.info(requestModel.toString());
            String output = payUpfront(requestModel);
            log.info(output);
            return  output ;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  null ;
        }

    }

    /**
     * Method định nghĩa API để front-end yêu cầu thanh toán trả xe
     * Đường dẫn API : /finalPay
     * Request Method : Post
     *
     * @param model  tham số truyền vào, đóng gói trong đối tượng thuộc lớp RequestModel
     * @return kết quả trả về là chuỗi json dạng string là kết quả thực hiện giao dịch
     */
    @PostMapping(value = "/finalPay", consumes = MediaType.APPLICATION_JSON_VALUE, produces =  MediaType.APPLICATION_JSON_VALUE)
    public String finalPayControl(@RequestBody RequestModel model){
        log.info("New request path /finalPay");
        try {
            String output = finalPay(model);
            return output;
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  null ;
    }

    /**
     * Phương thức dùng để thanh toán cọc.
     *
     * @param requestModel thông tin thanh toán
     * @return kết quả thanh toán dưới dạng String Json
     */
    private String payUpfront(RequestModel requestModel){
        JsonObject jsonObject = new JsonObject() ;
        try {
            String bikeID = requestModel.getBarcode();
            log.info(new ObjectMapper().writeValueAsString(requestModel));
            BikeEntity bike = bikeRepository.getOne(Long.parseLong(bikeID));
            int upFront = bike.getUpfrontPrice() ;
            // pay
            InterBankTransaction interBankTransaction = new InterBankTransaction();
            interBankTransaction.setAmount(String.valueOf(upFront));
            interBankTransaction.setCardCode(requestModel.getCardCode());
            interBankTransaction.setCreatedAt(df.format(new Date()));
            interBankTransaction.setCvvCode(requestModel.getCvv());
            interBankTransaction.setDateExpired(requestModel.getExpireDate());
            interBankTransaction.setOwner(requestModel.getCardOwner());
            interBankTransaction.setTransactionContent("Dat coc thue xe ecobike");
            interBankTransaction.setCommand("pay");
            String response = interbankSubsystem.processPayTransaction(interBankTransaction);
            if (response.equals("00")) {
                // Lưu card ::
                CardEntity cardEntity = new CardEntity();
                cardEntity.setCardCode(requestModel.getCardCode());
                cardEntity.setCvvCode(requestModel.getCvv());
                cardEntity.setDateExpired(requestModel.getExpireDate());
                cardEntity.setOwner(requestModel.getCardOwner());
                cardRepository.saveAndFlush(cardEntity);
                // Lưu rentTransaction :::
                RenttransactionEntity renttransactionEntity = new RenttransactionEntity();
                renttransactionEntity.setBikeId(2L) ;//Long.parseLong(bikeID));
                renttransactionEntity.setCardId(cardEntity.getCardId());
                Long timeNow = System.currentTimeMillis() ;
                Timestamp time = new Timestamp(timeNow);
                renttransactionEntity.setStartTime(time);
                renttransactionEntity.setBikeByBikeId(bike);
                renttransactionEntity.setCardByCardId(cardEntity);
                rentTransactionRepository.saveAndFlush(renttransactionEntity);
                // Update bike available status:::
                bike.setIsAvailable((byte) 0);
                bikeRepository.saveAndFlush(bike);
                // Construct result
                jsonObject.addProperty("cardID", cardEntity.getCardId());
                jsonObject.addProperty("bikeID", requestModel.getBarcode());
                jsonObject.addProperty("status", "Success");
            } else {
                String status = interbankSubsystem.codeToDetail(response);
                jsonObject.addProperty("Error", status);
            }
            return  jsonObject.toString();
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        jsonObject.addProperty("Error", "Something went wrong");
        return  jsonObject.toString() ;
    }

    /**
     * Phương thức thanh toán khi người dùng trả xe
     *
     * @param requestModel thông tin trả xe
     * @return Kết quả thanh toán
     */
    private String finalPay(RequestModel requestModel)  {
        JsonObject jsonObject = new JsonObject();
        String customerID = requestModel.getCardID() ;
//        log.info(new ObjectMapper().writeValueAsString(requestModel));
        String bikeID = requestModel.getBarcode() ;
        String parkinglotID = requestModel.getParkingLotID() ;
        try {
            List<RenttransactionEntity> renttransactionEntity = rentTransactionRepository.findAllByCardId(Long.parseLong(customerID));
            if (renttransactionEntity != null){
                RenttransactionEntity renttransaction = renttransactionEntity.get(0);
                BikeEntity bikeEntity = renttransaction.getBikeByBikeId();
                ParkinglotEntity parkinglotEntity = parkingLotRepository.getOne(Long.parseLong(parkinglotID));
                CardEntity cardEntity = renttransaction.getCardByCardId();
                Timestamp returnTime = new Timestamp(System.currentTimeMillis());
                Timestamp rentTime = new Timestamp(returnTime.getTime() - renttransaction.getStartTime().getTime());
                long count = new FeeCalculator().calculateFee(renttransaction);

                //payment
                InterBankTransaction interBankTransaction = new InterBankTransaction();
                interBankTransaction.setCardCode(cardEntity.getCardCode());
                interBankTransaction.setOwner(cardEntity.getOwner());
                interBankTransaction.setDateExpired(cardEntity.getDateExpired());
                interBankTransaction.setCvvCode(cardEntity.getCvvCode());
                interBankTransaction.setTransactionContent("Trả cọc");
                interBankTransaction.setCreatedAt(df.format(new Date()));
                String response;
                if (bikeEntity.getUpfrontPrice() - count > 0) {
                    interBankTransaction.setAmount(String.valueOf(bikeEntity.getUpfrontPrice() - count));
                    response = interbankSubsystem.processReturnTransaction(interBankTransaction);
                } else {
                    interBankTransaction.setAmount(String.valueOf(-bikeEntity.getUpfrontPrice() + count));
                    response = interbankSubsystem.processPayTransaction(interBankTransaction);
                }
                if (response.equals("00")) {
                    // Update return Transaction
                    ReturntransactionEntity returntransaction = new ReturntransactionEntity();
                    returntransaction.setRentTransactionId(renttransaction.getRentTransactionId());
                    returntransaction.setFinishTime(returnTime);
                    returntransaction.setRentPrice((int) count);
                    returntransaction.setRentTime(rentTime);
                    returnTransactionRepository.saveAndFlush(returntransaction);
                    // Update bike info
                    bikeEntity.setIsAvailable((byte) 1);
                    bikeRepository.saveAndFlush(bikeEntity);
                    bikeEntity.setParkingLotId(Long.parseLong(parkinglotID));
                    bikeEntity.setParkinglotByParkingLotId(parkinglotEntity);
                    // Form json output
                    jsonObject.addProperty("Mã vạch", renttransaction.getBikeId());
                    jsonObject.addProperty("Loại xe", BikeTypeUtils.translateType(bikeEntity.getType()));
                    jsonObject.addProperty("Thời điểm thuê", df.format(renttransaction.getStartTime()));
                    jsonObject.addProperty("Thời điểm trả", df.format(returnTime));
                    jsonObject.addProperty("Thời gian thuê", new Time(rentTime.getTime()).toString());
                    jsonObject.addProperty("Số tiền phải trả", count + " VND");
                    jsonObject.addProperty("Số tiền đã cọc", bikeEntity.getUpfrontPrice());
                    jsonObject.addProperty("Số tiền nhận lại", bikeEntity.getUpfrontPrice() - count + " VND");
                } else {
                    String status = interbankSubsystem.codeToDetail(response);
                    jsonObject.addProperty("Error", status );
                }
            }
            return  jsonObject.toString();
        } catch (Exception e){
            log.error(e.getMessage(),e);
            jsonObject.addProperty("Error", "Something went wrong");
            return  jsonObject.toString() ;
        }
    }
}
