package crm.core.client.controller;

import crm.core.client.dto.ClientDto;
import crm.core.client.mapper.ClientMapper;
import crm.core.client.model.Client;
import crm.core.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для управления клиентами.
 * Предоставляет REST API для выполнения CRUD-операций над клиентами.
 */
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    @Value("${app.clients.page-size:20}")
    private int pageSize;

    private final ClientMapper clientMapper;

    private final ClientService clientService;

    /**
     * Создаёт нового клиента на основе переданных данных.
     *
     * @param clientDto данные клиента для создания (валидируются аннотациями)
     * @return созданный объект {@link Client}
     * @throws jakarta.validation.ConstraintViolationException если данные не прошли валидацию
     * @throws IllegalArgumentException если передан null
     * @throws crm.core.exception.ConflictException если клиент с таким email уже существует
     */
    @PostMapping
    public Client createClient (@Valid @RequestBody ClientDto clientDto) {
        log.info("Получен POST-запрос на создание клиента: {}", clientDto);
        return clientService.createClient(clientDto);
    }

    /**
     * Получает данные клиента по его уникальному идентификатору.
     *
     * @param clientId идентификатор клиента
     * @return объект {@link ClientDto} с данными клиента
     * @throws crm.core.exception.ClientNotFoundException если клиент с указанным ID не найден
     */
    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable final Long clientId) {
        log.info("Запрошено получение данных клиента с ID: {}", clientId);
        return clientMapper.toClientDto(clientService.getClient(clientId));
    }

    /**
     * Возвращает список всех клиентов с пагинацией.
     * Сортировка по дате создания в порядке убывания.
     *
     * @param page номер страницы (по умолчанию 0)
     * @return страница с {@link ClientDto}, обёрнутая в {@link Page}
     */
    @GetMapping
    public Page<ClientDto> getAllClients(@RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("createdAt").descending());

        log.info("Запрошено получение данных всех клиентов");

        Page<Client> clients = clientService.getAllClients(pageable);
        return clients.map(clientMapper::toClientDto);
    }

    /**
     * Обновляет данные существующего клиента.
     *
     * @param clientId  идентификатор клиента для обновления
     * @param clientDto новые данные клиента (валидируются аннотациями)
     * @return обновлённый объект {@link Client}
     * @throws crm.core.exception.ClientNotFoundException если клиент не найден
     * @throws jakarta.validation.ConstraintViolationException если данные не прошли валидацию
     * @throws crm.core.exception.ConflictException если клиент с таким email уже существует
     */
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable final Long clientId, @Valid @RequestBody ClientDto clientDto) {
        log.info("Получен PUT-запрос на обновление клиента с ID: {}", clientId);
        return clientService.updateClient(clientId, clientDto);
    }

    /**
     * Удаляет клиента по его идентификатору.
     *
     * @param clientId идентификатор клиента для удаления
     * @throws crm.core.exception.ClientNotFoundException если клиент не найден
     */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable final Long clientId) {
        log.info("Запрошено удаление пользователя с ID: {}", clientId);
        clientService.deleteClient(clientId);
    }
}
