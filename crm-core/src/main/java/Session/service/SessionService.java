package Session.service;

import Session.dto.SessionDto;
import Session.model.Session;

public interface SessionService {

    Session createSession(SessionDto sessionDto);

    Session getSession(Long id);

    void deleteSession(Long id);

    Session updateSession(Long id, SessionDto sessionDto);
}
