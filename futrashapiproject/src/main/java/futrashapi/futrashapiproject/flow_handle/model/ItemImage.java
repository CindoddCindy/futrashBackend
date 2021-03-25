package futrashapi.futrashapiproject.flow_handle.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import futrashapi.futrashapiproject.auth.model.User;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "item_image")
public class ItemImage {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;



    @Lob
    private byte[] data;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;



    public ItemImage() {
    }

    public ItemImage(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
