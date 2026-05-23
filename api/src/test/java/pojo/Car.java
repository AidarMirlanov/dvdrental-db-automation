package pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Car {
    String brand;
    String model;
    String color;
    String engine;



}
