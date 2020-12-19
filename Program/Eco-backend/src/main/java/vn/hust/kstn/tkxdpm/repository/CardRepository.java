package vn.hust.kstn.tkxdpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.kstn.tkxdpm.entity.CardEntity;

/**
 * Interface CardRepository dùng để CRUD table Card trong database
 */
@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
//    List<ParkingLot> findAllByParkingLotId(int atk);
}