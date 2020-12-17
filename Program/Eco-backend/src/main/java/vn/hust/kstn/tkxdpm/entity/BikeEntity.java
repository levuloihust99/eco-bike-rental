package vn.hust.kstn.tkxdpm.entity;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

/**
 * Lớp đinh nghĩa một đối tượng bike với các trường thông tin tương ứng
 */
@Entity
@Table(name = "bike", schema = "eco_bike_2")
public class BikeEntity {
    private long bikeId;
    private long parkingLotId;
    private int type;
    private int upfrontPrice;
    private int rentPrice;
    private Timestamp lastUsed;
    private Time maxTimeUsed;
    private byte isAvailable;
    private ParkinglotEntity parkinglotByParkingLotId;
    private Collection<RenttransactionEntity> renttransactionsByBikeId;

    /**
     * Gets bike id.
     *
     * @return the bike id
     */
    @Id
    @Column(name = "bikeId")
    public long getBikeId() {
        return bikeId;
    }

    /**
     * Sets bike id.
     *
     * @param bikeId the bike id
     */
    public void setBikeId(long bikeId) {
        this.bikeId = bikeId;
    }

    /**
     * Gets parking lot id.
     *
     * @return the parking lot id
     */
    @Basic
    @Column(name = "parkingLotId", insertable = false , updatable =  false)
    public long getParkingLotId() {
        return parkingLotId;
    }

    /**
     * Sets parking lot id.
     *
     * @param parkingLotId the parking lot id
     */
    public void setParkingLotId(long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets upfront price.
     *
     * @return the upfront price
     */
    @Basic
    @Column(name = "upfrontPrice")
    public int getUpfrontPrice() {
        return upfrontPrice;
    }

    /**
     * Sets upfront price.
     *
     * @param upfrontPrice the upfront price
     */
    public void setUpfrontPrice(int upfrontPrice) {
        this.upfrontPrice = upfrontPrice;
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

    /**
     * Gets last used.
     *
     * @return the last used
     */
    @Basic
    @Column(name = "lastUsed")
    public Timestamp getLastUsed() {
        return lastUsed;
    }

    /**
     * Sets last used.
     *
     * @param lastUsed the last used
     */
    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }

    /**
     * Gets max time used.
     *
     * @return the max time used
     */
    @Basic
    @Column(name = "maxTimeUsed")
    public Time getMaxTimeUsed() {
        return maxTimeUsed;
    }

    /**
     * Sets max time used.
     *
     * @param maxTimeUsed the max time used
     */
    public void setMaxTimeUsed(Time maxTimeUsed) {
        this.maxTimeUsed = maxTimeUsed;
    }

    /**
     * Gets is available.
     *
     * @return the is available
     */
    @Basic
    @Column(name = "isAvailable")
    public byte getIsAvailable() {
        return isAvailable;
    }

    /**
     * Sets is available.
     *
     * @param isAvailable the is available
     */
    public void setIsAvailable(byte isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BikeEntity that = (BikeEntity) o;
        return bikeId == that.bikeId &&
                parkingLotId == that.parkingLotId &&
                type == that.type &&
                upfrontPrice == that.upfrontPrice &&
                rentPrice == that.rentPrice &&
                isAvailable == that.isAvailable &&
                Objects.equals(lastUsed, that.lastUsed) &&
                Objects.equals(maxTimeUsed, that.maxTimeUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bikeId, parkingLotId, type, upfrontPrice, rentPrice, lastUsed, maxTimeUsed, isAvailable);
    }

    /**
     * Gets parkinglot by parking lot id.
     *
     * @return the parkinglot by parking lot id
     */
    @ManyToOne
    @JoinColumn(name = "parkingLotId", referencedColumnName = "parkingLotId", nullable = false)
    public ParkinglotEntity getParkinglotByParkingLotId() {
        return parkinglotByParkingLotId;
    }

    /**
     * Sets parkinglot by parking lot id.
     *
     * @param parkinglotByParkingLotId the parkinglot by parking lot id
     */
    public void setParkinglotByParkingLotId(ParkinglotEntity parkinglotByParkingLotId) {
        this.parkinglotByParkingLotId = parkinglotByParkingLotId;
    }

    /**
     * Gets renttransactions by bike id.
     *
     * @return the renttransactions by bike id
     */
    @OneToMany(mappedBy = "bikeByBikeId")
    public Collection<RenttransactionEntity> getRenttransactionsByBikeId() {
        return renttransactionsByBikeId;
    }

    /**
     * Sets renttransactions by bike id.
     *
     * @param renttransactionsByBikeId the renttransactions by bike id
     */
    public void setRenttransactionsByBikeId(Collection<RenttransactionEntity> renttransactionsByBikeId) {
        this.renttransactionsByBikeId = renttransactionsByBikeId;
    }
}
