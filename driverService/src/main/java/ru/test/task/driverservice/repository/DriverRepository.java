package ru.test.task.driverservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;
import ru.test.task.driverservice.entity.Driver;

import java.util.Optional;

@Transactional(readOnly = true)
public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver>  {

    Optional<Driver> findByFullNameAndPassport(String fullName, String passport);
}
