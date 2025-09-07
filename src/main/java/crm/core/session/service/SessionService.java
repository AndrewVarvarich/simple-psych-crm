package crm.core.session.service;

import crm.core.session.dto.SessionDto;
import crm.core.session.model.Session;

public interface SessionService {

    Session createSession(SessionDto sessionDto);

    Session getSession(Long id);

    void deleteSession(Long id);

    Session updateSession(Long id, SessionDto sessionDto);
}
