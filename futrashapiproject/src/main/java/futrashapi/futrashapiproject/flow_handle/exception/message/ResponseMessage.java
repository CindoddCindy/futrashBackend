package futrashapi.futrashapiproject.flow_handle.exception.message;

import futrashapi.futrashapiproject.flow_handle.model.ItemImage;

public class ResponseMessage {

    private String message;


    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
