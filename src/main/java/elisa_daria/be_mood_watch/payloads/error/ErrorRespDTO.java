package elisa_daria.be_mood_watch.payloads.error;

import java.time.LocalDateTime;

public record ErrorRespDTO(String msg, LocalDateTime timestamp) {
}
