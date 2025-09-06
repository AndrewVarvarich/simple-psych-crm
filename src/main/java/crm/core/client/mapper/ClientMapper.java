package crm.core.client.mapper;

import crm.core.client.dto.ClientDto;
import crm.core.client.model.Client;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    Client toClient(ClientDto clientDto);

    Collection<ClientDto> toCollectionClientDto(Collection<Client> users);
}
