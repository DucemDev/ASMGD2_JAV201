package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Restaurant", schema = "dbo")
public class Restaurant {

    @Id
    @Column(name = "RestaurantId")
    private String restaurantId;

    @Column(name = "Name")
    private String name;

    @Column(name = "PosterUrl")
    private String posterUrl;

    @Column(name = "VideoUrl")
    private String videoUrl;

    @Column(name = "ViewCount")
    private int viewCount;

    @OneToMany(mappedBy = "restaurant")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "restaurant")
    private List<Shares> shares;

    @OneToMany(mappedBy = "restaurant")
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant")
    private List<ViewHistory> viewHistory;
}
