package ru.test.task.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.test.task.carservice.entity.Part;

import java.util.Optional;

@Transactional(readOnly = true)
public interface PartRepository extends JpaRepository<Part, Long> {
    Optional<Part> findBySerialNumber(String serialNumber);
}
