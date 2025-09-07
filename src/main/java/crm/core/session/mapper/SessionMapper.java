package crm.core.session.mapper;

import crm.core.session.dto.SessionDto;
import crm.core.session.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionDto toSessionDto(Session session);

    Session toSession(SessionDto sessionDto);
}
