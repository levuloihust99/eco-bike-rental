package vn.hust.kstn.tkxdpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.kstn.tkxdpm.entity.ParkinglotEntity;

/**
 * Interface ParkingLotRepository dùng để CRUD table ParkingLot trong database
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkinglotEntity, Long> {
//    List<ParkingLot> findAllByParkingLotId(int atk);
}