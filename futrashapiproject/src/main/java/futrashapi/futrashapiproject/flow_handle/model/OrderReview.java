package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_reviews")
public class OrderReview extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String mitra_name;

    @NotNull
    private String customer_name;

    @NotNull
    private String food_name;

    @NotNull
    private String food_location;

    @NotNull
    private String review_customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Order order;

    public OrderReview(@NotNull String mitra_name, @NotNull String customer_name, @NotNull String food_name, @NotNull String food_location, @NotNull String review_customer) {
        this.mitra_name = mitra_name;
        this.customer_name = customer_name;
        this.food_name = food_name;
        this.food_location = food_location;
        this.review_customer = review_customer;
    }

    public OrderReview(@NotNull String mitra_name, @NotNull String customer_name, @NotNull String food_name, @NotNull String food_location, @NotNull String review_customer, Order order) {
        this.mitra_name = mitra_name;
        this.customer_name = customer_name;
        this.food_name = food_name;
        this.food_location = food_location;
        this.review_customer = review_customer;
        this.order = order;
    }

    public OrderReview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMitra_name() {
        return mitra_name;
    }

    public void setMitra_name(String mitra_name) {
        this.mitra_name = mitra_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_location() {
        return food_location;
    }

    public void setFood_location(String food_location) {
        this.food_location = food_location;
    }

    public String getReview_customer() {
        return review_customer;
    }

    public void setReview_customer(String review_customer) {
        this.review_customer = review_customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
