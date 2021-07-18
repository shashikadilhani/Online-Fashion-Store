package com.fashion.demo.dto.response;

public class ErrorMessageResponseDTO {
    private int error_code;
    private String message;
//    private String timestamp;
//    private boolean success;

    public ErrorMessageResponseDTO() {
    }

    public ErrorMessageResponseDTO(int status, String message) {
        this.error_code = status;
        this.message = message;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessageResponseDTO{" +
//                "success=" + success +
                ", error_code=" + error_code +
                ", message='" + message + '\'' +
//                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
