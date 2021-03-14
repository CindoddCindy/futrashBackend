package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {


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




    public Order() {
    }

    public Order(@NotNull String image_url, @NotNull String food_name, @NotNull String mitra_name, @NotNull String food_location, @NotNull String food_price, @NotNull String customer_name, String customer_location, String customer_phone, String shipping_type) {
        this.image_url = image_url;
        this.food_name = food_name;
        this.mitra_name = mitra_name;
        this.food_location = food_location;
        this.food_price = food_price;
        this.customer_name = customer_name;
        this.customer_location = customer_location;
        this.customer_phone = customer_phone;
        this.shipping_type = shipping_type;
    }


    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Item> itemSet = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "orderReview_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private OrderReview orderReview;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getMitra_name() {
        return mitra_name;
    }

    public void setMitra_name(String mitra_name) {
        this.mitra_name = mitra_name;
    }

    public String getFood_location() {
        return food_location;
    }

    public void setFood_location(String food_location) {
        this.food_location = food_location;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_location() {
        return customer_location;
    }

    public void setCustomer_location(String customer_location) {
        this.customer_location = customer_location;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getShipping_type() {
        return shipping_type;
    }

    public void setShipping_type(String shipping_type) {
        this.shipping_type = shipping_type;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setItemSet(Set<Item> itemSet) {
        this.itemSet = itemSet;

        for(Item i : itemSet) {
            i.setOrder(this);
        }
    }

    public OrderReview getOrderReview() {
        return orderReview;
    }

    public void setOrderReview(OrderReview orderReview) {
        this.orderReview = orderReview;
    }
}
