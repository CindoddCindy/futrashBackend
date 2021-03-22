package futrashapi.futrashapiproject.flow_handle.exception.message;

public class ResponseMessageUserImage {

    private String message;

    public ResponseMessageUserImage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
