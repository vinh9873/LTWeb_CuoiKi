package vn.ute.entity;

import java.time.Instant;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserWeb user;

    @Column
    private Double rate;

    @Column(length = 1000)
    private String comment;

    @Column
    Date date;

    public String getUserName() {
        return this.user.getName();
    }

    public String getLastUpdated() {
        var lastUpdatedSeconds = Instant.now().minusMillis(this.getDate().getTime()).toEpochMilli() / 1000;
        if (lastUpdatedSeconds > 60) {
            return "%d mins ago".formatted(lastUpdatedSeconds / 60);
        }
        if (lastUpdatedSeconds > 3600) {
            return "%d hours ago".formatted(lastUpdatedSeconds / 3600);
        }
        if (lastUpdatedSeconds > 86400) {
            return "%d days ago".formatted(lastUpdatedSeconds / 86400);
        }
        if (lastUpdatedSeconds > 604800) {
            return "%d weeks ago".formatted(lastUpdatedSeconds / 604800);
        }
        return "%d seconds ago".formatted(lastUpdatedSeconds);
    }
}
