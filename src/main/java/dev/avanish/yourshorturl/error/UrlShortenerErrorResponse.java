package dev.avanish.yourshorturl.error;

import java.time.LocalDateTime;
import java.util.List;

public class UrlShortenerErrorResponse {

    private String status;
    private List<String> error;
    private String message;

    public UrlShortenerErrorResponse() {
    }
    public UrlShortenerErrorResponse(String status, List<String> error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
