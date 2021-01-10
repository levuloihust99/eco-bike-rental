package vn.hust.kstn.tkxdpm.entity.bike;

import vn.hust.kstn.tkxdpm.utils.FeeCalculate.EBikeFeeCalculator;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.FeeCalculatorInterface;
import vn.hust.kstn.tkxdpm.utils.FeeCalculate.NomalBikeFeeCalculator;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "bike", schema = "eco_bike_2")
public class EBikeEntity extends RootBikeEntity {
    protected Time maxTimeUsed;
    @Basic
    @Column(name = "maxTimeUsed")
    public Time getMaxTimeUsed() {
        return maxTimeUsed;
    }

    @Transient
    @Override
    public FeeCalculatorInterface getFeeCalculatorMethod(){
        feeCalculator = new EBikeFeeCalculator();
        return  feeCalculator;
    }

    /**
     * Sets max time used.
     *
     * @param maxTimeUsed the max time used
     */

    public void setMaxTimeUsed(Time maxTimeUsed) {
        this.maxTimeUsed = maxTimeUsed;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBikeEntity that = (EBikeEntity) o;
        return bikeId == that.bikeId &&
                parkingLotId == that.parkingLotId &&
                upfrontPrice == that.upfrontPrice &&
                rentPrice == that.rentPrice &&
                isAvailable == that.isAvailable &&
                Objects.equals(lastUsed, that.lastUsed) &&
                Objects.equals(maxTimeUsed, that.maxTimeUsed);
    }
}
