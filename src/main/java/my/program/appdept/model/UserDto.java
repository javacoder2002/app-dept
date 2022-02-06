package my.program.appdept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import my.program.appdept.entity.Address;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "firstName must not be empty.")
    private String firstName;

    @NotNull(message = "lastName must not be empty.")
    private String lastName;

    @NotNull(message = "phoneNumber must not be empty.")
    private String phoneNumber;

    @NotNull(message = "district must not be empty.")
    private String district;

    @NotNull(message = "street must not be empty.")
    private String street;

    @NotNull(message = "homeNumber must not be empty.")
    private Integer homeNumber;

    @NotNull(message = "active must not be empty.")
    private boolean active;

    @NotNull(message = "login must not be empty.")
    private String login;

    @NotNull(message = "password must not be empty.")
    private String password;

}
