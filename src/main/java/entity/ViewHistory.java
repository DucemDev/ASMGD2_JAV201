package entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ViewHistory {
    @Id
    private String HistoryId;
//    private String UserId;
//    private String RestaurantId;
    private String ViewAt;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;
}
