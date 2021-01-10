package vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankInterface;
import vn.hust.kstn.tkxdpm.utils.HttpConnector;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

/**
 * InterBankSubsystem impliments các phương thức của InterbankInterface.
 * Điều khiển các phương thức thanh toán với Interbank
 */
@Slf4j
public class InterbankSystemController implements InterbankInterface {
    //
    private String reset_path = "api/card/reset-balance";
    private String transaction_path = "api/card/processTransaction";
    private String base_url = "https://ecopark-system-api.herokuapp.com/";
    private String sc_key = "BhErH32tmF4=";

    /**
     * Phương thức reset, chỉ sử dụng trong khi dev
     */
    private void reset() {
        JsonObject transaction = new JsonObject();
        transaction.addProperty("cardCode", "118131_group6_2020");
        transaction.addProperty("owner", "Group 6");
        transaction.addProperty("cvvCode", "266");
        transaction.addProperty("dateExpired", "1125");
        try {
            String response = new HttpConnector().sendPatch(base_url + reset_path, transaction.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                String code = responseJson.get("errorCode").getAsString();
                if (code.equals("00")) {
                    log.info("Reset balance success !!");
                    return;
                }
            }
            log.error("Reset failed !!, response {}", response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Phương thức thực hiện thanh toán
     *
     * @param transaction : Thông tin giao dịch thanh toán
     * @return Kết quả thanh toán dạng Json String
     */
    private String processTransaction(InterBankTransaction transaction) {
        try {
            String transactString = new ObjectMapper().writeValueAsString(transaction);
            JsonObject transactBody = new JsonParser().parse(transactString).getAsJsonObject();
            JsonObject transToHash = new JsonObject();
            transToHash.addProperty("secretKey", sc_key);
            transToHash.add("transaction", transactBody);
            log.info("Chuỗi băm : {}", transToHash.toString());
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(transToHash.toString().getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            log.info("MD5: {}", myHash);

            JsonObject sentJson = new JsonObject();
            sentJson.addProperty("version", "1.0.1");
            sentJson.add("transaction", transactBody);
            sentJson.addProperty("appCode", "BvqtzrCgJRk=");
            sentJson.addProperty("hashCode", myHash);

            log.info("Chuỗi gửi lên: {}", sentJson.toString());
            return processTransaction(base_url + transaction_path, sentJson.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "08";
    }

    @Override
    public String processReturnTransaction(Object trans) {
        InterBankTransaction transaction = (InterBankTransaction) trans;
        transaction.setCommand("refund");
        return processTransaction(transaction);
    }

    @Override
    public String processPayTransaction(Object trans) {
        InterBankTransaction transaction = (InterBankTransaction) trans;
        transaction.setCommand("pay");
        return processTransaction(transaction);
    }

    public int translateCode(String code) {
        if (code.equals("00")) return 0;
        if (code.equals("01")) return 1;
        if (code.equals("02")) return 2;
        if (code.equals("03")) return 3;
        if (code.equals("04")) return 4;
        if (code.equals("05")) return 4;
        if (code.equals("06")) return 4;
        if (code.equals("07")) return 4;
        return 8;
    }
    private String processTransaction(String base_url , String  body){
        try {
            try {
                String response =  new HttpConnector().sendPatch(base_url,body);
                if (response != null && new JsonParser().parse(response).isJsonObject()) {
                    JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                    String code = responseJson.get("errorCode").getAsString();
                    return code ;
                }
                log.error("Process transaction failed !!, response {}", response);
            } catch (Exception e){
                log.error(e.getMessage(),e);
            }
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return "08" ;
    }
}
