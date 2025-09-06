package crm.core.Session.service;

import crm.core.Session.dto.SessionDto;
import crm.core.Session.model.Session;

public interface SessionService {

    Session createSession(SessionDto sessionDto);

    Session getSession(Long id);

    void deleteSession(Long id);

    Session updateSession(Long id, SessionDto sessionDto);
}
