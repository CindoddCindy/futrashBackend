package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "confirm_order")
public class ConfirmOrder extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String terima_tolak;

    @NotNull
    private String catatan_alasan;



    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "confirm_order_id")
    private List<Order> orderList;

    public ConfirmOrder() {
    }

    public ConfirmOrder(@NotNull String terima_tolak, @NotNull String catatan_alasan) {
        this.terima_tolak = terima_tolak;
        this.catatan_alasan = catatan_alasan;
    }

    public ConfirmOrder(@NotNull String terima_tolak, @NotNull String catatan_alasan, List<Order> orderList) {
        this.terima_tolak = terima_tolak;
        this.catatan_alasan = catatan_alasan;
        this.orderList = orderList;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerima_tolak() {
        return terima_tolak;
    }

    public void setTerima_tolak(String terima_tolak) {
        this.terima_tolak = terima_tolak;
    }

    public String getCatatan_alasan() {
        return catatan_alasan;
    }

    public void setCatatan_alasan(String catatan_alasan) {
        this.catatan_alasan = catatan_alasan;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
