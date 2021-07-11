package com.shoppingcart.demo.dto.response;

public class CommonResponseDTO {
    private boolean success;
    private String message;

    public CommonResponseDTO() {
    }

    public CommonResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CommonResponseDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
