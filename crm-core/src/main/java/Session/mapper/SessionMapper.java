package Session.mapper;

import Session.dto.SessionDto;
import Session.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionDto toSessionDto(Session session);

    Session toSession(SessionDto sessionDto);
}
