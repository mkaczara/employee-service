package pl.mkaczara.employeeservice.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.Objects;

public class ErrorResponse {

    private String message;
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    public ErrorResponse(String message, String code, LocalDateTime timestamp) {
        this.message = message;
        this.code = code;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(message, that.message) &&
                Objects.equals(code, that.code) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code, timestamp);
    }
}
