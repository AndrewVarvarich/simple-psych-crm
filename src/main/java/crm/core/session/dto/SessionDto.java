package crm.core.session.dto;

import crm.core.client.model.Client;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SessionDto {
    private Long id;
    private Client client;
    private LocalDateTime dateTime;
    private Status status;
    private Long price;
    private String notes;
}
