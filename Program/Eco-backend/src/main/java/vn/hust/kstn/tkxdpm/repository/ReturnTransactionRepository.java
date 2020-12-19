package vn.hust.kstn.tkxdpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.kstn.tkxdpm.entity.ReturntransactionEntity;


/**
 * Interface RentTransactionRepository dùng để CRUD table RentTransaction trong database
 */
@Repository
public interface ReturnTransactionRepository extends JpaRepository<ReturntransactionEntity, Long> {
}