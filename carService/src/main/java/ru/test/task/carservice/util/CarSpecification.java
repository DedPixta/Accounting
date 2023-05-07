package ru.test.task.carservice.util;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ru.test.task.carservice.entity.Car;

import java.util.ArrayList;
import java.util.List;

public record CarSpecification(

        @Schema(description = "Unique identifier of the Car", example = "4ZJ705FMROGAQ289S")
        String vin,

        @Schema(description = "Plate number of the Car", example = "P032DB96")
        String plateNumber,

        @Schema(description = "Manufacturer of the Car", example = "FCA")
        String manufacturer,

        @Schema(description = "Brand of the Car", example = "Chrysler")
        String brand)
        implements Specification<Car> {

    @Override
    public Predicate toPredicate(@NonNull Root<Car> root,
                                 @NonNull CriteriaQuery<?> criteriaQuery,
                                 @NonNull CriteriaBuilder criteriaBuilder) {
        root.fetch("carParts", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (vin != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("vin")), "%" + vin.toLowerCase() + "%"));
        }

        if (plateNumber != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("plateNumber")), "%" + plateNumber.toLowerCase() + "%"));
        }

        if (manufacturer != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("manufacturer")), "%" + manufacturer.toLowerCase() + "%"));
        }

        if (brand != null) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("brand")), "%" + brand.toLowerCase() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

