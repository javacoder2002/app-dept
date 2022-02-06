package my.program.appdept.controller;

import my.program.appdept.entity.User;
import my.program.appdept.model.ApiResponse;
import my.program.appdept.model.UserDto;
import my.program.appdept.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * list of all users
     * @return UserList
     */
    @GetMapping
    public HttpEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * one user is taken by id
     * @param id
     * @return User
     */
    @GetMapping("/{id}")
    public HttpEntity<User> getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * User is added by this method.
     * @param userDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * The user is edited by this method.
     * @param id
     * @param userDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeUser(@PathVariable Integer id,
                                             @Valid @RequestBody UserDto userDto){
        ApiResponse apiResponse = userService.editeUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * user is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

}












