package my.program.appdept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.program.appdept.entity.Address;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    private String district;
    private String street;
    private Integer homeNumber;

    private boolean active;
    private String login;
    private String password;
}
