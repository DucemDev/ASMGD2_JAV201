package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Favorite", schema = "dbo")
public class Favorite {

    @Id
    @Column(name = "FavoriteId")
    private String favoriteId;

    @Column(name = "LikedAt")
    private LocalDateTime likedAt;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;
}
