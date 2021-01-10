package vn.hust.kstn.tkxdpm.entity.bike;

import vn.hust.kstn.tkxdpm.utils.FeeCalculate.EBikeFeeCalculator;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.FeeCalculatorInterface;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.NomalBikeFeeCalculator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bike", schema = "eco_bike_2")
public class TwinBikeEntity extends RootBikeEntity {
    @Transient
    public FeeCalculatorInterface getFeeCalculatorMethod(){
        feeCalculator = new EBikeFeeCalculator();
        return  feeCalculator;
    }
}
