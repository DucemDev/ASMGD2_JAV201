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
@Table(name = "ViewHistory", schema = "dbo")
public class ViewHistory {

    @Id
    @Column(name = "HistoryId")
    private String historyId;

    @Column(name = "ViewedAt")
    private LocalDateTime viewedAt;

    @ManyToOne
    @JoinColumn(name = "UserId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "RestaurantId")
    private Restaurant restaurant;
    @Transient
    private String viewedAtFormatted;

}
