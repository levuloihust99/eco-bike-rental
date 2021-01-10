package vn.hust.kstn.tkxdpm.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Lớp đinh nghĩa một giao dịch trả xe với các trường thông tin tương ứng
 */
@Entity
@Table(name = "returntransaction", schema = "eco_bike_2")
public class ReturntransactionEntity {
    private long rentTransactionId;
    private Timestamp rentTime;
    private Timestamp finishTime;
    private int rentPrice;
    private RenttransactionEntity renttransactionByRentTransactionId;

    /**
     * Gets rent transaction id.
     *
     * @return the rent transaction id
     */
    @Id
    @Column(name = "rentTransactionId")
    public long getRentTransactionId() {
        return rentTransactionId;
    }

    /**
     * Sets rent transaction id.
     *
     * @param rentTransactionId the rent transaction id
     */
    public void setRentTransactionId(long rentTransactionId) {
        this.rentTransactionId = rentTransactionId;
    }

    /**
     * Gets rent time.
     *
     * @return the rent time
     */
    @Basic
    @Column(name = "rentTime")
    public Timestamp getRentTime() {
        return rentTime;
    }

    /**
     * Sets rent time.
     *
     * @param rentTime the rent time
     */
    public void setRentTime(Timestamp rentTime) {
        this.rentTime = rentTime;
    }

    /**
     * Gets finish time.
     *
     * @return the finish time
     */
    @Basic
    @Column(name = "finishTime")
    public Timestamp getFinishTime() {
        return finishTime;
    }

    /**
     * Sets finish time.
     *
     * @param finishTime the finish time
     */
    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * Gets rent price.
     *
     * @return the rent price
     */
    @Basic
    @Column(name = "rentPrice")
    public int getRentPrice() {
        return rentPrice;
    }

    /**
     * Sets rent price.
     *
     * @param rentPrice the rent price
     */
    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturntransactionEntity that = (ReturntransactionEntity) o;
        return rentTransactionId == that.rentTransactionId &&
                rentPrice == that.rentPrice &&
                Objects.equals(rentTime, that.rentTime) &&
                Objects.equals(finishTime, that.finishTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentTransactionId, rentTime, finishTime, rentPrice);
    }

    /**
     * Gets renttransaction by rent transaction id.
     *
     * @return the renttransaction by rent transaction id
     */
    @OneToOne
    @JoinColumn(name = "rentTransactionId", referencedColumnName = "rentTransactionId", nullable = false)
    public RenttransactionEntity getRenttransactionByRentTransactionId() {
        return renttransactionByRentTransactionId;
    }

    /**
     * Sets renttransaction by rent transaction id.
     *
     * @param renttransactionByRentTransactionId the renttransaction by rent transaction id
     */
    public void setRenttransactionByRentTransactionId(RenttransactionEntity renttransactionByRentTransactionId) {
        this.renttransactionByRentTransactionId = renttransactionByRentTransactionId;
    }
}
