package crm.core.client.service;

import crm.core.client.dto.ClientDto;
import crm.core.exception.ConflictException;
import crm.core.client.mapper.ClientMapper;
import crm.core.client.model.Client;
import crm.core.client.repositories.ClientRepository;
import crm.core.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    @Transactional(readOnly = false)
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
    @Transactional(readOnly = false)
    public void deleteClient(Long id) {
        log.info("Deleting client with id {}", id);
        clientRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Client with id " + id + " not found")
        );
        clientRepository.deleteById(id);
        log.info("Client with id {} successfully deleted", id);
    }

    @Override
    @Transactional(readOnly = false)
    public Client updateClient(Long id, ClientDto client) {
        log.info("Updating client with id {}", id);
        Client existingClient = clientRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Client with id " + id + " not found"));
        if (client.getEmail() != null) {
            if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
                throw new ConflictException("Client with email " + client.getEmail() + " already exists");
            }
            existingClient.setEmail(client.getEmail());
        }
        if (client.getFullName() != null) {
            existingClient.setFullName(client.getFullName());
        }
        if (client.getPhoneNumber() != null) {
            existingClient.setPhoneNumber(client.getPhoneNumber());
        }
        if (client.getNotes() != null) {
            existingClient.setNotes(client.getNotes());
        }
        clientRepository.save(existingClient);
        log.info("Updated client {}", existingClient);
        return existingClient;
    }

    @Override
    public Client getClient(Long id) {
        log.info("Getting client with id {}", id);
        return clientRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public Page<Client> getAllClients(Pageable pageable) {
        log.info("Getting all clients");
        return clientRepository.findAll(pageable);
    }
}
