package client.mapper;

import client.dto.ClientDto;
import client.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toClientDto(Client client);

    Client toClient(ClientDto clientDto);
}
