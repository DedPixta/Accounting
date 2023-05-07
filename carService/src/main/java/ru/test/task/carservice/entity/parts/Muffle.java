package ru.test.task.carservice.entity.parts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.test.task.carservice.entity.Part;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "mufflers")
public class Muffle extends Part {

    private Integer length;
    private String type;

}
