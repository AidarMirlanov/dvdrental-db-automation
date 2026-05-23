package pojoUser;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.InvocationTargetException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ResponseUser {

    Integer id;
    String name;
    String email;
    String gender;
    String status;



}
