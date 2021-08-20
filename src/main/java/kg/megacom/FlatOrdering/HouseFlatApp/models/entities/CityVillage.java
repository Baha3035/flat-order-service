package kg.megacom.FlatOrdering.HouseFlatApp.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city_villages")
public class CityVillage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "regions_id")
    private Region region;
    private String name;

}
