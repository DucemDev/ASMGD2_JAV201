package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users {
    @Id
    private String UserId;
    private String Username;
    private String Password;
    private String Email;
    private boolean Role;
    private String OtpCode;
    private boolean Status;

    @OneToMany(mappedBy = "user")
    private List<Shares> shares;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;
    @OneToMany(mappedBy = "user")
    private List<ViewHistory> viewHistory;

}
