package crm.core.Session.service;

import crm.core.Session.dto.SessionDto;
import crm.core.Session.mapper.SessionMapper;
import crm.core.Session.model.Session;
import crm.core.Session.repositories.SessionRepository;
import crm.core.client.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;
    private final SessionRepository sessionRepository;
    private final ClientRepository clientRepository;

    @Override
    public Session createSession(SessionDto sessionDto) {
        return null;
    }

    @Override
    public Session getSession(Long id) {
        return null;
    }

    @Override
    public void deleteSession(Long id) {

    }

    @Override
    public Session updateSession(Long id, SessionDto sessionDto) {
        return null;
    }
}
