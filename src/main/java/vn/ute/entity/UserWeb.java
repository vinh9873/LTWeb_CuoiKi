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
@Entity(name = "user_web")
public class UserWeb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column
    private String password;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column
    private String codeVerify;

    @Column
    private Boolean isActive;

    @Column
    private Collection<Integer> productIdsBought;

    public void addAllBoughtProducts(Collection<Integer> prodIds) {
        if (this.productIdsBought == null) {
            this.productIdsBought = new ArrayList<>();
        }
        this.productIdsBought.addAll(prodIds);
    }
}
