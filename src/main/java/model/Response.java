package model;

import java.util.Objects;

public class Response<T> {
    T result;
    boolean success;
    String errorMsg;

    public Response(T result, boolean success, String errorMsg) {
        this.result = result;
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "result=" + result +
                ", success=" + success +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response<?> response = (Response<?>) o;
        return success == response.success && Objects.equals(result, response.result) && Objects.equals(errorMsg, response.errorMsg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, success, errorMsg);
    }
}
