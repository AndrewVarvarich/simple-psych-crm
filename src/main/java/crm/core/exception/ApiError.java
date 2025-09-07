package crm.core.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    @Schema(example = "2025-09-07T10:00:00")
    private LocalDateTime timestamp;

    @Schema(example = "404")
    private int code;

    @Schema(example = "Client with id 999 not found")
    private String message;
}