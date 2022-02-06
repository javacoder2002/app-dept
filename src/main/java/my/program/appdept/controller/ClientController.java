package my.program.appdept.controller;

import my.program.appdept.entity.Client;
import my.program.appdept.model.ApiResponse;
import my.program.appdept.model.ClientDto;
import my.program.appdept.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Client")
public class ClientController {

    @Autowired
    ClientService clientService;

    /**
     * list of all Clients
     * @return ClientList
     */
    @GetMapping
    public HttpEntity<List<Client>> getClients(){
        List<Client> clients = clientService.getClients();
        return ResponseEntity.ok(clients);
    }

    /**
     * one Client is taken by id
     * @param id
     * @return Client
     */
    @GetMapping("/{id}")
    public HttpEntity<Client> getClientById(@PathVariable Integer id){
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    /**
     * Client is added by this method.
     * @param ClientDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addClient(@Valid @RequestBody ClientDto ClientDto){
        ApiResponse apiResponse = clientService.addClient(ClientDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    /**
     * The Client is edited by this method.
     * @param id
     * @param ClientDto
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editeClient(@PathVariable Integer id,
                                             @Valid @RequestBody ClientDto ClientDto){
        ApiResponse apiResponse = clientService.editeClient(id, ClientDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
     * Client is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteClient(@PathVariable Integer id){
        ApiResponse apiResponse = clientService.deleteClient(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    /**
    *-----
    * */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}












