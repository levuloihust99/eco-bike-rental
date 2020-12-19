package vn.hust.kstn.tkxdpm;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankInterface;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem.InterBankTransaction;
import vn.hust.kstn.tkxdpm.bankSystem.InterbankSubsystem.InterbankSystemController;
import vn.hust.kstn.tkxdpm.requestInterface.RequestModel;
import vn.hust.kstn.tkxdpm.utils.HttpConnector;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateInterbankReturnTransaction {
    private InterbankInterface interbank;

    @BeforeEach
    void setUp() throws Exception {
        interbank = new InterbankSystemController();
        reset();
    }
    @Test
    public void testRefundTransaction() {
        InterBankTransaction interBankTransaction = new InterBankTransaction();
        interBankTransaction.setAmount("100");
        interBankTransaction.setCardCode("118131_group6_2020");
        interBankTransaction.setCreatedAt("2020-12-19 21:00:00");
        interBankTransaction.setCvvCode("266");
        interBankTransaction.setDateExpired("1125");
        interBankTransaction.setOwner("Group 6");
        interBankTransaction.setTransactionContent("Test refund");
        String response = interbank.processReturnTransaction(interBankTransaction);
        System.out.println(response);
        assertNotNull(response);
        assertTrue(response.contains("00"));
    }
    private String reset_path = "api/card/reset-balance" ;
    private String base_url = "https://ecopark-system-api.herokuapp.com/";
    private void reset(){
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
                    return;
                }
            }
        } catch (Exception e){
        }
    }
}
