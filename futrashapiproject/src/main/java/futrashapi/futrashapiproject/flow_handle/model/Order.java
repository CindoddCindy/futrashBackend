package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends AuditModel{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String image_url;

    @NotNull
    private String food_name;

    @NotNull
    private String mitra_name;

    @NotNull
    private String food_location;

    @NotNull
    private String food_price;


    @NotNull
    private String customer_name;

    @Nullable
    private String customer_location;

    @Nullable
    private String customer_phone;

    @Nullable
    private String shipping_type;


}
