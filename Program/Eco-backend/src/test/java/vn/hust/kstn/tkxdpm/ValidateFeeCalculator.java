package vn.hust.kstn.tkxdpm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import vn.hust.kstn.tkxdpm.entity.RenttransactionEntity;
import vn.hust.kstn.tkxdpm.utils.BikeTypeUtils;
import vn.hust.kstn.tkxdpm.utils.FeeCalculator;

import javax.management.timer.Timer;
import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidateFeeCalculator {
    private FeeCalculator feeCalculator;
    @BeforeEach
    void setUp() throws Exception {
        feeCalculator = new FeeCalculator();
    }
    @ParameterizedTest
    @CsvSource({"1,0","2,0","15,10000", "29,10000"})
    public void test(int  timeInMinute, long ans){
        RenttransactionEntity renttransactionEntity = new RenttransactionEntity();
        renttransactionEntity.setBikeId(1);
        renttransactionEntity.setStartTime(new Timestamp(System.currentTimeMillis() -timeInMinute* Timer.ONE_MINUTE));
        long amount = feeCalculator.calculateFee(renttransactionEntity) ;
        assertTrue(amount == ans);
    }
}
