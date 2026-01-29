package entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String UserId;
    private String RestaurantId;
    private String ViewAt;

}
