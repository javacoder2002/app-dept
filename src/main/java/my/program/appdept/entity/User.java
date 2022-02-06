package my.program.appdept.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import my.program.appdept.entity.template.AbstractPeople;

import javax.persistence.Column;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "users")
public class User extends AbstractPeople {

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

}
