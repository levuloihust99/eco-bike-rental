package vn.hust.kstn.tkxdpm.utils.FeeCalculate;

import vn.hust.kstn.tkxdpm.entity.RenttransactionEntity;

import javax.management.timer.Timer;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Lớp có nhiệm vụ tính toán phí thuê xe
 */
public class NomalBikeFeeCalculator implements FeeCalculatorInterface {
    /**
     * Calculate fee.
     *
     * @param renttransactionEntity thông tin thuê xe
     * @return Số tiền phí tương ứng dạng Long
     */
    public Long calculateFee(RenttransactionEntity renttransactionEntity){
         Timestamp startTime = renttransactionEntity.getStartTime() ;
         long fee = 0 ;
         Date date = new Date();
         Long rentTimeByMls = (date.getTime() - startTime.getTime());
         if (rentTimeByMls < 10 * Timer.ONE_MINUTE){
             fee = 0 ;
         } else
             if (rentTimeByMls < 30 * Timer.ONE_MINUTE){
                 fee = 10000 ;
             }
             else {
                 fee = 10000;
                 long extra_time = rentTimeByMls - 30 * Timer.ONE_MINUTE ;
                 System.out.println(extra_time);
                 long time = extra_time / (15 * Timer.ONE_MINUTE ) + extra_time % (15 * Timer.ONE_MINUTE);
                 System.out.println(time);
                 fee +=  time * 15000 ;
             }
             return fee ;
    }
}
