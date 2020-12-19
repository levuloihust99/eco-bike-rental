package vn.hust.kstn.tkxdpm.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * Lớp có chức năng gọi barcodeAPI dùng để chuyển Barcode thành mã xe tương ứng
 */
@Slf4j
public class BarcodeUtils {
    static HttpConnector httpConnector = new HttpConnector();
    /**
     * Hàm lấy mã xe từ barcode thông qua API
     *
     * @param barcode the barcode
     * @return mã xe trả về tương ứng
     */
    public static String getBikeIDbyBarCode(String barcode){
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("barcode", barcode);
            String res = httpConnector.sendPost("https://barcodeservicebykv2.herokuapp.com/barcode", jsonObject.toString());
            JsonObject o = new JsonParser().parse(res).getAsJsonObject();
            if (o.has("id")){
                return  o.get("id").getAsString();
            }
            return  null;
        } catch (Exception e){
            log.error(e.getMessage(),e);
            return  null  ;
        }
    }

}
