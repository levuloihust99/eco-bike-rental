package vn.hust.kstn.tkxdpm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vn.hust.kstn.tkxdpm.requestInterface.RequestModel;
import vn.hust.kstn.tkxdpm.utils.BarcodeUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateBarcodeService {
    @BeforeEach
    void setUp() throws Exception {
    }
    @ParameterizedTest
    @CsvSource({"1","2","15", "abcdefgsg"})
    public void test(String barcode){
        String jsonBikeID = BarcodeUtils.getBikeIDbyBarCode(barcode) ;
        assertNotNull(jsonBikeID);
        assertTrue(jsonBikeID.equals(barcode));
    }
}
