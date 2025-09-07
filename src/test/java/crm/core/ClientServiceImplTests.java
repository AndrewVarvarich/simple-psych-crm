package crm.core;

import crm.core.client.dto.ClientDto;
import crm.core.client.mapper.ClientMapper;
import crm.core.client.model.Client;
import crm.core.client.repositories.ClientRepository;
import crm.core.client.service.ClientServiceImpl;
import crm.core.exception.ClientNotFoundException;
import crm.core.exception.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Включает Mockito
class ClientServiceImplTests {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientDto clientDto;
    private Client client;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setEmail("test@example.com");
        clientDto.setFullName("John Doe");
        clientDto.setPhoneNumber(null);
        clientDto.setNotes(null);

        client = new Client();
        client.setId(1L);
        client.setEmail("test@example.com");
        client.setFullName("John Doe");
        client.setPhoneNumber("+79876543210");
        client.setNotes("Old notes");
    }

    @Test
    void createClient_shouldCreateAndReturnClient_whenClientIsValidAndEmailIsUnique() {
        when(clientRepository.findByEmail(clientDto.getEmail())).thenReturn(Optional.empty());
        when(clientMapper.toClient(clientDto)).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.createClient(clientDto);

        assertThat(result).isEqualTo(client);
        verify(clientRepository).save(client);
        verify(clientMapper).toClient(clientDto);
    }

    @Test
    void createClient_shouldThrowIllegalArgumentException_whenClientDtoIsNull() {
        assertThrows(IllegalArgumentException.class, () -> clientService.createClient(null));
    }

    @Test
    void createClient_shouldThrowConflictException_whenEmailAlreadyExists() {
        when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.of(client));

        assertThrows(ConflictException.class, () -> clientService.createClient(clientDto));
    }

    @Test
    void getClient_shouldReturnClient_whenClientExists() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        Client result = clientService.getClient(client.getId());

        assertThat(result).isEqualTo(client);
    }

    @Test
    void getClient_shouldThrowClientNotFoundException_whenClientIsNotExists() {
        when(clientRepository.findById(client.getId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () ->
                clientService.getClient(client.getId()));
    }

    @Test
    void updateClient_shouldUpdateClientFields_whenClientExistsAndEmailIsUnique() {
        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.of(client));
        when(clientRepository.findByEmail(clientDto.getEmail())).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        Client updatedClient = clientService.updateClient(clientDto.getId(), clientDto);

        assertThat(updatedClient.getEmail()).isEqualTo(clientDto.getEmail());
        assertThat(updatedClient.getFullName()).isEqualTo(clientDto.getFullName());
        assertThat(updatedClient.getPhoneNumber()).isEqualTo(client.getPhoneNumber());
        assertThat(updatedClient.getNotes()).isEqualTo(client.getNotes());

        verify(clientRepository).save(client);
    }

    @Test
    void updateClient_shouldThrowClientNotFoundException_whenClientDoesNotExist() {
        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () ->
                clientService.updateClient(clientDto.getId(), clientDto));
    }

    @Test
    void updateClient_shouldThrowConflictException_whenNewEmailAlreadyExists() {
        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.of(client));
        when(clientRepository.findByEmail(clientDto.getEmail())).thenReturn(Optional.of(client));

        assertThrows(ConflictException.class, () ->
                clientService.updateClient(clientDto.getId(), clientDto));
    }

    @Test
    void deleteClient_shouldDeleteClient_whenClientExists() {
        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.of(client));

        clientService.deleteClient(clientDto.getId());

        verify(clientRepository).deleteById(clientDto.getId());
    }

    @Test
    void deleteClient_shouldThrowClientNotFoundException_whenClientDoesNotExists() {
        when(clientRepository.findById(clientDto.getId())).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () ->
                clientService.deleteClient(clientDto.getId()));
    }

    @Test
    void getAllClients_shouldReturnPageOfClients_whenPageableIsProvided() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> expectedPage = new PageImpl<>(List.of(client));

        when(clientRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Client> result = clientService.getAllClients(pageable);

        assertThat(result).isEqualTo(expectedPage);
        assertEquals(1, result.getContent().size());
        assertThat(result.getContent().get(0)).isEqualTo(client);
    }
}
