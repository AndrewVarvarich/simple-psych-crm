package client.service;

import client.dto.ClientDto;
import client.exception.ConflictException;
import client.exception.NotFoundException;
import client.mapper.ClientMapper;
import client.model.Client;
import client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientSevice {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public Client createClient(ClientDto client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new ConflictException("Client with email " + client.getEmail() + " already exists");
        }
        log.info("Creating client {}", client);
        Client Client = clientMapper.toClient(client);
        clientRepository.save(Client);
        log.info("Saved client {}", Client);
        return Client;
    }

    @Override
    public Client getClient(Long id) {
        log.info("Getting client with id {}", id);
       return clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public void deleteClient(Long id) {
        log.info("Deleting client with id {}", id);
        clientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Long id, ClientDto client) {
        log.info("Updating client with id {}", id);
        Client existingClient = clientRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Client with id " + id + " not found"));
        if (client.getEmail() != null) {
            if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
                throw new ConflictException("Client with email " + client.getEmail() + " already exists");
            }
            existingClient.setEmail(client.getEmail());
        }
        if (client.getFullName() != null) {
            existingClient.setFullName(client.getFullName());
        }
        if (client.getPhone() != null) {
            existingClient.setPhone(client.getPhone());
        }
        if (client.getNotes() != null) {
            existingClient.setNotes(client.getNotes());
        }
        clientRepository.save(existingClient);
        log.info("Updated client {}", existingClient);
        return existingClient;
    }
}
