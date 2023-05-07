package ru.test.task.driverservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.test.task.driverservice.util.DLCategory;

@Getter
@Setter

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Schema(hidden = true)
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Schema(description = "Driver licence category", example = "B")
    @Enumerated(EnumType.STRING)
    private DLCategory category;

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }
}
