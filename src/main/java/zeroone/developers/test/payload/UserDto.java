package zeroone.developers.test.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zeroone.developers.test.entity.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private Role role;

}
