package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Restaurant {
    @Id
    private String RestaurantId;
    private String Name;
    private String PosterUrl;
    private String VideoUrl;
    private int ViewCount;
    @OneToMany(mappedBy = "restaurant")
    private List<Favorite> favorites;
    @OneToMany(mappedBy = "restaurant")
    private List<Shares> shares;

}
