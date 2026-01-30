package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shares {
    @Id
    private String ShareId;
//    private String UserId;
//    private String RestaurantId;
    private String RecipientEmail;
    private LocalDateTime ShareAt;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;
}
