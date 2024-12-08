package vn.ute.entity;

import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class UserCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserWeb user;

    @Column
    private Collection<Integer> productIds;

    public void addProductToCart(Integer p) {
        if (this.productIds == null) {
            this.productIds = new ArrayList<>();
        }
        this.productIds.add(p);
    }

}
