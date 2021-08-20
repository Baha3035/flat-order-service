package kg.megacom.FlatOrdering.HouseFlatApp.models.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rooms;
    private String descriptions;
    private int floor;
    private double area;
    private double price;
    @ManyToOne
    @JoinColumn(name ="types_id")
    private Type type;
    private boolean internet;
    private boolean furniture;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "city_villages_id")
    private CityVillage cityVillage;
    @ManyToOne
    @JoinColumn(name = "districts_id")
    private District district;
    @CreationTimestamp
    private LocalDate addDate;
    @UpdateTimestamp
    private LocalDate editDate;
    private double lat;
    private double lon;
    private String address;
}
