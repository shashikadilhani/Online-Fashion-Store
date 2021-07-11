package com.shoppingcart.demo.dto.response;

public class CommonDataResponseDTO {
    private boolean success;
    private Object body;

    public CommonDataResponseDTO() {
    }

    public CommonDataResponseDTO(boolean success, Object body) {
        this.success = success;
        this.body = body;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonDataResponseDTO{" +
                "success=" + success +
                ", body=" + body +
                '}';
    }
}
