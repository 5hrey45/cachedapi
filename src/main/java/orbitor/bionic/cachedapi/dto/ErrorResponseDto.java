package orbitor.bionic.cachedapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private HttpStatus status;

    private String errorMessage;

    private LocalDateTime timestamp;
}
