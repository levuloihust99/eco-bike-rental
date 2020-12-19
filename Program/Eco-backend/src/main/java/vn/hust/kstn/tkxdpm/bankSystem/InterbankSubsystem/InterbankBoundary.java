package vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import vn.hust.kstn.tkxdpm.utils.HttpConnector;

@Slf4j
public class InterbankBoundary {
    /**
     * Phương thức thực hiện thanh toán
     *
     * @param base_url : Đường dẫn URL của API
     * @param body : Body của request lên API
     * @return Kết quả thanh toán là mã code dạng String
     */
    public String processTransaction(String base_url , String  body){
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
