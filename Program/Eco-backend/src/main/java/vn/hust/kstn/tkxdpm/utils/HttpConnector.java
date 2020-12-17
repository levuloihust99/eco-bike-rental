package vn.hust.kstn.tkxdpm.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Lớp có nhiệm vụ thực hiện HTTP connect, các method POST, GET, ...
 */
@Slf4j
public class HttpConnector {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;
    private static CloseableHttpClient httpClient;

    /**
     * Send patch method HTTP.
     *
     * @param url  the url
     * @param body the body
     * @return the response
     */
    public String sendPatch(String url , String body) {
        try {

            RequestBody requestBody = RequestBody.create(body, MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE));
            Request request = new Request.Builder().url(url)
//                    .header("Connection", "close")
                    .patch(requestBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Send post method.
     *
     * @param url  the url
     * @param body the body
     * @return the response
     */
    public String sendPost(String url , String body) {
        try {

            RequestBody requestBody = RequestBody.create(body, MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE));
            Request request = new Request.Builder().url(url)
//                    .header("Connection", "close")
                    .post(requestBody).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * Send get method.
     *
     * @param url the url
     * @return the response
     */
    public  String sendGet(String url){
        try{
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return  response.body().string();
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
