package vn.hust.kstn.tkxdpm.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Lớp đinh nghĩa một đối tượng Card với các trường thông tin tương ứng
 */
@Entity
@Table(name = "card", schema = "eco_bike_2")
public class CardEntity {
    private long cardId;
    private String cardCode;
    private String owner;
    private String cvvCode;
    private String dateExpired;
    private Collection<RenttransactionEntity> renttransactionsByCardId;

    /**
     * Gets card id.
     *
     * @return the card id
     */
    @Id
    @Column(name = "cardId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
     * Gets card code.
     *
     * @return the card code
     */
    @Basic
    @Column(name = "cardCode")
    public String getCardCode() {
        return cardCode;
    }

    /**
     * Sets card code.
     *
     * @param cardCode the card code
     */
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    @Basic
    @Column(name = "owner")
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets cvv code.
     *
     * @return the cvv code
     */
    @Basic
    @Column(name = "cvvCode")
    public String getCvvCode() {
        return cvvCode;
    }

    /**
     * Sets cvv code.
     *
     * @param cvvCode the cvv code
     */
    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    /**
     * Gets date expired.
     *
     * @return the date expired
     */
    @Basic
    @Column(name = "dateExpired")
    public String getDateExpired() {
        return dateExpired;
    }

    /**
     * Sets date expired.
     *
     * @param dateExpired the date expired
     */
    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return cardId == that.cardId &&
                Objects.equals(cardCode, that.cardCode) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(cvvCode, that.cvvCode) &&
                Objects.equals(dateExpired, that.dateExpired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardCode, owner, cvvCode, dateExpired);
    }

    /**
     * Gets renttransactions by card id.
     *
     * @return the renttransactions by card id
     */
    @OneToMany(mappedBy = "cardByCardId")
    public Collection<RenttransactionEntity> getRenttransactionsByCardId() {
        return renttransactionsByCardId;
    }

    /**
     * Sets renttransactions by card id.
     *
     * @param renttransactionsByCardId the renttransactions by card id
     */
    public void setRenttransactionsByCardId(Collection<RenttransactionEntity> renttransactionsByCardId) {
        this.renttransactionsByCardId = renttransactionsByCardId;
    }
}
