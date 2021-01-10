package vn.hust.kstn.tkxdpm.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Lớp đinh nghĩa một giao dịch thuê xe với các trường thông tin tương ứng
 */
@Entity
@Table(name = "renttransaction", schema = "eco_bike_2")
public class RenttransactionEntity {
    private long rentTransactionId;
    private long cardId;
    private long bikeId;
    private Timestamp startTime;
    private CardEntity cardByCardId;
    private BikeEntity bikeByBikeId;
    private ReturntransactionEntity returntransactionByRentTransactionId;

    /**
     * Gets rent transaction id.
     *
     * @return the rent transaction id
     */
    @Id
    @Column(name = "rentTransactionId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
     * Gets card id.
     *
     * @return the card id
     */
    @Basic
    @Column(name = "cardId", insertable = false , updatable =  false)
    public long getCardId() {
        return cardId;
    }

    /**
     * Sets card id.
     *
     * @param cardId the card id
     */
    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    /**
     * Gets bike id.
     *
     * @return the bike id
     */
    @Basic
    @Column(name = "bikeId", insertable = false , updatable =  false)
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
     * Gets start time.
     *
     * @return the start time
     */
    @Basic
    @Column(name = "startTime")
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenttransactionEntity that = (RenttransactionEntity) o;
        return rentTransactionId == that.rentTransactionId &&
                cardId == that.cardId &&
                bikeId == that.bikeId &&
                Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentTransactionId, cardId, bikeId, startTime);
    }

    /**
     * Gets card by card id.
     *
     * @return the card by card id
     */
    @ManyToOne
    @JoinColumn(name = "cardId", referencedColumnName = "cardId", nullable = false)
    public CardEntity getCardByCardId() {
        return cardByCardId;
    }

    /**
     * Sets card by card id.
     *
     * @param cardByCardId the card by card id
     */
    public void setCardByCardId(CardEntity cardByCardId) {
        this.cardByCardId = cardByCardId;
    }

    /**
     * Gets bike by bike id.
     *
     * @return the bike by bike id
     */
    @ManyToOne
    @JoinColumn(name = "bikeId", referencedColumnName = "bikeId", nullable = false)
    public BikeEntity getBikeByBikeId() {
        return bikeByBikeId;
    }

    /**
     * Sets bike by bike id.
     *
     * @param bikeByBikeId the bike by bike id
     */
    public void setBikeByBikeId(BikeEntity bikeByBikeId) {
        this.bikeByBikeId = bikeByBikeId;
    }

    /**
     * Gets returntransaction by rent transaction id.
     *
     * @return the returntransaction by rent transaction id
     */
    @OneToOne(mappedBy = "renttransactionByRentTransactionId")
    public ReturntransactionEntity getReturntransactionByRentTransactionId() {
        return returntransactionByRentTransactionId;
    }

    /**
     * Sets returntransaction by rent transaction id.
     *
     * @param returntransactionByRentTransactionId the returntransaction by rent transaction id
     */
    public void setReturntransactionByRentTransactionId(ReturntransactionEntity returntransactionByRentTransactionId) {
        this.returntransactionByRentTransactionId = returntransactionByRentTransactionId;
    }
}
