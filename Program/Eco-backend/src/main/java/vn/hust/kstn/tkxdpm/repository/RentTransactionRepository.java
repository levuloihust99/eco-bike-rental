package vn.hust.kstn.tkxdpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.kstn.tkxdpm.entity.RenttransactionEntity;

import java.util.List;

/**
 * Interface RentTransactionRepository dùng để CRUD table RentTransaction trong database
 */
@Repository
public interface RentTransactionRepository extends JpaRepository<RenttransactionEntity, Long> {
    /**
     * tìm tất cả các rentTransaction sử dụng CardID là cardId
     *
     * @param cardId the card id
     * @return danh sách các RenttransactionEntity dưới dạng list
     */
    List<RenttransactionEntity> findAllByCardId(long cardId);
}