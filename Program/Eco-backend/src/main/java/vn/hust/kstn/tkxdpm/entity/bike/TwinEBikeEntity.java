package vn.hust.kstn.tkxdpm.entity.bike;


import vn.hust.kstn.tkxdpm.utils.FeeCalculate.FeeCalculatorInterface;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.NomalBikeFeeCalculator;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.TwinEBikeFeeCalculator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bike", schema = "eco_bike_2")
public class TwinEBikeEntity extends EBikeEntity {

    @Transient
    @Override
    public FeeCalculatorInterface getFeeCalculatorMethod(){
        feeCalculator = new TwinEBikeFeeCalculator();
        return  feeCalculator;
    }
}
