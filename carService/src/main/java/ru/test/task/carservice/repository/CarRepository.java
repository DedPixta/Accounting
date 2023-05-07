package ru.test.task.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.test.task.carservice.entity.Car;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    @Query("select c from Car c join fetch c.carParts where c.vin = :vin ")
    Optional<Car> findByVin(String vin);

    @Query("select c from Car c join fetch c.carParts where c.plateNumber = :plateNumber ")
    Optional<Car> findByPlateNumber(String plateNumber);
}
