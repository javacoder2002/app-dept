package my.program.appdept.repository;

import my.program.appdept.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByLogin(String login);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    boolean existsByLoginAndIdNot(String login, Integer id);

}
