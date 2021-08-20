package kg.megacom.FlatOrdering.HouseFlatApp.models.entities;

import kg.megacom.FlatOrdering.HouseFlatApp.enums.ReserveStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "reserve_histories")
public class ReserveHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "houses_id")
    private House house;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;
    private double totalPrice;
    @CreationTimestamp
    private LocalDate addDate;
    @UpdateTimestamp
    private LocalDate editDate;

}
