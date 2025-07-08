package client.service;

import client.dto.ClientDto;
import client.model.Client;

public interface ClientSevice {

    Client createClient(ClientDto client);

    Client getClient(Long id);

    void deleteClient(Long id);

    Client updateClient(Long id, ClientDto client);

}
