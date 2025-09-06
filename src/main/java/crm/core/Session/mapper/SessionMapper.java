package crm.core.Session.mapper;

import crm.core.Session.dto.SessionDto;
import crm.core.Session.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionDto toSessionDto(Session session);

    Session toSession(SessionDto sessionDto);
}
