package ru.test.task.driverservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ru.test.task.driverservice.entity.Driver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record DriverSpecification(

        @Schema(description = "Full name of the driver", example = "Salma Lams")
        String fullName,

        @Schema(description = "Driver's date of birth", example = "1983-07-18")
        LocalDate dateOfBirth)
        implements Specification<Driver> {

    @Override
    public Predicate toPredicate(@NonNull Root<Driver> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        root.fetch("categories", JoinType.LEFT);
        root.fetch("cars", JoinType.LEFT);
        root.fetch("account", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (fullName != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
        }

        if (dateOfBirth != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dateOfBirth"), dateOfBirth));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
