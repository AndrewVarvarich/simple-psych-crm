package crm.core.Session.model;

import crm.core.client.model.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import crm.core.Session.dto.Status;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private Long id;

    @ManyToOne
    private Client client;

    @FutureOrPresent
    private LocalDateTime dateTime;

    private Status status;

    private Long price;

    private String notes;
}
