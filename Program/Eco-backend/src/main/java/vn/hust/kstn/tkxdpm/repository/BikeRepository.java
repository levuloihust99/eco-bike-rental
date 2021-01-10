package vn.hust.kstn.tkxdpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.kstn.tkxdpm.entity.BikeEntity;

/**
 * Interface BikeRepository dùng để CRUD table Bike trong database
 */
@Repository
public interface BikeRepository extends JpaRepository<BikeEntity, Long> {
//    BikeEntity getAllByBikeId(Integer bikeID);
}