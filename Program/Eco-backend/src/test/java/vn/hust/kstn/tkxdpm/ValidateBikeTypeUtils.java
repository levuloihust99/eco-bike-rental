package vn.hust.kstn.tkxdpm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vn.hust.kstn.tkxdpm.utils.BarcodeUtils;
import vn.hust.kstn.tkxdpm.utils.BikeTypeUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateBikeTypeUtils {
    @BeforeEach
    void setUp() throws Exception {
    }
    @ParameterizedTest
    @CsvSource({"1,Xe đạp đơn","2,Xe đạp đôi","3,Xe đạp điện", "4,Xe đạp điện đôi", "15,Loại xe chưa định nghĩa"})
    public void test(int  type, String detail){
        String jsonBikeID = BikeTypeUtils.translateType(type) ;
        assertNotNull(jsonBikeID);
        assertTrue(jsonBikeID.contains(detail));
    }
}
