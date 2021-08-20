package kg.megacom.FlatOrdering.HouseFlatApp.models.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "pay_histories")
public class PayHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "reserve_histories_id")
    private ReserveHistory reserveHistory;
    private double cash;
    @CreationTimestamp
    private LocalDate addDate;

}
