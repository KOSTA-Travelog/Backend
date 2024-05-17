package kosta.travelog.servlet;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Objects;

public class ResponseModel implements Serializable {
    private static final long serialVersionUID = 8259148161000485338L;
    private int status;
    private String message;
    private String data;

    public ResponseModel(int status) {
        this(status, new JsonObject(), "");
    }

    public ResponseModel(int status, String message) {
        this(status, new JsonObject(), message);
    }

    public ResponseModel(int status, JsonObject json, String message) {
        setStatus(status);
        setData(json);
        setMessage(message);
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setData(JsonObject json) {
        this.data = json.toString();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResponseModel that = (ResponseModel) o;
        return getStatus() == that.getStatus() && Objects.equals(getData(), that.getData())
                && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        int result = getStatus();
        result = 31 * result + Objects.hashCode(getData());
        result = 31 * result + Objects.hashCode(getMessage());
        return result;
    }

    @Override
    public String toString() {
        if (data == null) {
            data = "";
        }
        if (message == null) {
            message = "";
        }
        return String.format("{status:%d, data:%s, message:\"%s\"}", status, data, message);
    }
}
