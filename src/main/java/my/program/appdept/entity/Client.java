package my.program.appdept.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import my.program.appdept.entity.template.AbstractPeople;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Client extends AbstractPeople {

}
