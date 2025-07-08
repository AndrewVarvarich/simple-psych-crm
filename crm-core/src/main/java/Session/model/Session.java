package Session.model;

import client.model.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import Session.dto.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private Long id;
    private Client client;
    private LocalDateTime dateTime;
    private Status status;
    private Long price;
    private String notes;
}
