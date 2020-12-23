package vn.hust.kstn.tkxdpm.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Lớp đinh nghĩa một đối tượng ParkingLot với các trường thông tin tương ứng
 */
@Entity
@Table(name = "parkinglot", schema = "ecobike")
public class ParkinglotEntity {
    private long parkingLotId;
    private String name;
    private String address;
    private int area;
    private int stdBikeSlots;
    private int eBikeSlots;
    private int twinStdBikeSlots;
    private int twinEBikeSlots;
    private Collection<BikeEntity> bikesByParkingLotId;

    /**
     * Gets parking lot id.
     *
     * @return the parking lot id
     */
    @Id
    @Column(name = "parkingLotId")
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
     * Gets name.
     *
     * @return the name
     */
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets area.
     *
     * @return the area
     */
    @Basic
    @Column(name = "area")
    public int getArea() {
        return area;
    }

    /**
     * Sets area.
     *
     * @param area the area
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Gets std bike slots.
     *
     * @return the std bike slots
     */
    @Basic
    @Column(name = "stdBikeSlots")
    public int getStdBikeSlots() {
        return stdBikeSlots;
    }

    /**
     * Sets std bike slots.
     *
     * @param stdBikeSlots the std bike slots
     */
    public void setStdBikeSlots(int stdBikeSlots) {
        this.stdBikeSlots = stdBikeSlots;
    }

    /**
     * Gets bike slots.
     *
     * @return the bike slots
     */
    @Basic
    @Column(name = "eBikeSlots")
    public int geteBikeSlots() {
        return eBikeSlots;
    }

    /**
     * Sets bike slots.
     *
     * @param eBikeSlots the e bike slots
     */
    public void seteBikeSlots(int eBikeSlots) {
        this.eBikeSlots = eBikeSlots;
    }

    /**
     * Gets twin std bike slots.
     *
     * @return the twin std bike slots
     */
    @Basic
    @Column(name = "twinStdBikeSlots")
    public int getTwinStdBikeSlots() {
        return twinStdBikeSlots;
    }

    /**
     * Sets twin std bike slots.
     *
     * @param twinStdBikeSlots the twin std bike slots
     */
    public void setTwinStdBikeSlots(int twinStdBikeSlots) {
        this.twinStdBikeSlots = twinStdBikeSlots;
    }

    /**
     * Gets twin e bike slots.
     *
     * @return the twin e bike slots
     */
    @Basic
    @Column(name = "twinEBikeSlots")
    public int getTwinEBikeSlots() {
        return twinEBikeSlots;
    }

    /**
     * Sets twin e bike slots.
     *
     * @param twinEBikeSlots the twin e bike slots
     */
    public void setTwinEBikeSlots(int twinEBikeSlots) {
        this.twinEBikeSlots = twinEBikeSlots;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkinglotEntity that = (ParkinglotEntity) o;
        return parkingLotId == that.parkingLotId &&
                area == that.area &&
                stdBikeSlots == that.stdBikeSlots &&
                eBikeSlots == that.eBikeSlots &&
                twinStdBikeSlots == that.twinStdBikeSlots &&
                twinEBikeSlots == that.twinEBikeSlots &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingLotId, name, address, area, stdBikeSlots, eBikeSlots, twinStdBikeSlots, twinEBikeSlots);
    }

    /**
     * Gets bikes by parking lot id.
     *
     * @return the bikes by parking lot id
     */
    @OneToMany(mappedBy = "parkinglotByParkingLotId")
    public Collection<BikeEntity> getBikesByParkingLotId() {
        return bikesByParkingLotId;
    }

    /**
     * Sets bikes by parking lot id.
     *
     * @param bikesByParkingLotId the bikes by parking lot id
     */
    public void setBikesByParkingLotId(Collection<BikeEntity> bikesByParkingLotId) {
        this.bikesByParkingLotId = bikesByParkingLotId;
    }
}
