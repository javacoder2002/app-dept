package my.program.appdept.service;

import my.program.appdept.entity.Address;
import my.program.appdept.entity.User;
import my.program.appdept.model.ApiResponse;
import my.program.appdept.model.UserDto;
import my.program.appdept.repository.AddressRepository;
import my.program.appdept.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * list of all users
     * @return UserList
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * one user is taken by id
     * @param id
     * @return User
     */
    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * User is added by this method.
     * @param userDto
     * @return ApiResponse
     */
    public ApiResponse addUser(UserDto userDto) {

        boolean existPhoneNumber = userRepository.existsByLogin(userDto.getLogin());
        if (existPhoneNumber)
            return new ApiResponse(false, "this phone number already exist!");

        boolean existLogin = userRepository.existsByLogin(userDto.getLogin());
        if (existLogin)
            return new ApiResponse(false, "this login already exist.");

        Address address = new Address();
        address.setDistrict(userDto.getDistrict());
        address.setStreet(userDto.getStreet());
        address.setHomeNumber(userDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(saveAddress);
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());

        userRepository.save(user);
        return new ApiResponse(true, "user saved!");
    }

    /**
     * The user is edited by this method.
     * @param id
     * @param userDto
     * @return ApiResponse
     */
    public ApiResponse editeUser(Integer id, UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");

        boolean existPhoneNumber = userRepository.existsByLoginAndIdNot(userDto.getLogin(), id);
        if (existPhoneNumber)
            return new ApiResponse(false, "this phone number already exist!");

        boolean existLogin = userRepository.existsByLoginAndIdNot(userDto.getLogin(), id);
        if (existLogin)
            return new ApiResponse(false, "this login already exist.");

        Address address = new Address();
        address.setDistrict(userDto.getDistrict());
        address.setStreet(userDto.getStreet());
        address.setHomeNumber(userDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(saveAddress);
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());

        userRepository.save(user);
        return new ApiResponse(true, "user edited!");

    }

    /**
     * user is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(false, "user not found!");
        userRepository.deleteById(id);
        addressRepository.delete(optionalUser.get().getAddress());
        return new ApiResponse(true, "user deleted!");
    }
}












