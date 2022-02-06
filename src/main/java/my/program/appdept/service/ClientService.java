package my.program.appdept.service;

import my.program.appdept.entity.Address;
import my.program.appdept.entity.Client;
import my.program.appdept.model.ApiResponse;
import my.program.appdept.model.ClientDto;
import my.program.appdept.repository.AddressRepository;
import my.program.appdept.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * list of all Clients
     * @return ClientList
     */
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * one Client is taken by id
     * @param id
     * @return Client
     */
    public Client getClientById(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    /**
     * Client is added by this method.
     * @param ClientDto
     * @return ApiResponse
     */
    public ApiResponse addClient(ClientDto ClientDto) {

        boolean existPhoneNumber = clientRepository.existsByPhoneNumber(ClientDto.getPhoneNumber());
        if (existPhoneNumber)
            return new ApiResponse(false, "this phone number already exist!");

        Address address = new Address();
        address.setDistrict(ClientDto.getDistrict());
        address.setStreet(ClientDto.getStreet());
        address.setHomeNumber(ClientDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Client client = new Client();
        client.setFirstName(ClientDto.getFirstName());
        client.setLastName(ClientDto.getLastName());
        client.setPhoneNumber(ClientDto.getPhoneNumber());
        client.setAddress(saveAddress);
        client.setActive(ClientDto.isActive());

        clientRepository.save(client);
        return new ApiResponse(true, "Client saved!");
    }

    /**
     * The Client is edited by this method.
     * @param id
     * @param ClientDto
     * @return ApiResponse
     */
    public ApiResponse editeClient(Integer id, ClientDto ClientDto) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new ApiResponse(false, "Client not found!");

        boolean existPhoneNumber = clientRepository.existsByPhoneNumberAndIdNot(ClientDto.getPhoneNumber(), id);
        if (existPhoneNumber)
            return new ApiResponse(false, "this phone number already exist!");

        Address address = new Address();
        address.setDistrict(ClientDto.getDistrict());
        address.setStreet(ClientDto.getStreet());
        address.setHomeNumber(ClientDto.getHomeNumber());
        Address saveAddress = addressRepository.save(address);

        Client client = optionalClient.get();
        client.setFirstName(ClientDto.getFirstName());
        client.setLastName(ClientDto.getLastName());
        client.setPhoneNumber(ClientDto.getPhoneNumber());
        client.setAddress(saveAddress);
        client.setActive(ClientDto.isActive());

        clientRepository.save(client);
        return new ApiResponse(true, "Client edited!");

    }

    /**
     * Client is deleted by this method.
     * @param id
     * @return ApiResponse
     */
    public ApiResponse deleteClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new ApiResponse(false, "Client not found!");
        clientRepository.deleteById(id);
        addressRepository.delete(optionalClient.get().getAddress());
        return new ApiResponse(true, "Client deleted!");
    }
}












