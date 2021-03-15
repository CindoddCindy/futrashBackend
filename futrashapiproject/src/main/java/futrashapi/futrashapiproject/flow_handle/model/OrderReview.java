package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private List<Order> orderList;

    public OrderReview(@NotNull String mitra_name, @NotNull String customer_name, @NotNull String food_name, @NotNull String food_location, @NotNull String review_customer) {
        this.mitra_name = mitra_name;
        this.customer_name = customer_name;
        this.food_name = food_name;
        this.food_location = food_location;
        this.review_customer = review_customer;
    }
/*
    public OrderReview(@NotNull String mitra_name, @NotNull String customer_name, @NotNull String food_name, @NotNull String food_location, @NotNull String review_customer, Order order) {
        this.mitra_name = mitra_name;
        this.customer_name = customer_name;
        this.food_name = food_name;
        this.food_location = food_location;
        this.review_customer = review_customer;
        this.order = order;
    }


 */


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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
