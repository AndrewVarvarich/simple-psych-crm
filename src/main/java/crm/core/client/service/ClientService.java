package crm.core.client.service;

import crm.core.client.dto.ClientDto;
import crm.core.client.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Client createClient(ClientDto client);

    Client getClient(Long id);

    void deleteClient(Long id);

    Client updateClient(Long id, ClientDto client);

    Page<Client> getAllClients(Pageable pageable);

}
