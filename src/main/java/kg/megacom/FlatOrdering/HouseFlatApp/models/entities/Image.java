package kg.megacom.FlatOrdering.HouseFlatApp.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "houses_id")
    private House house;
    private String url;
    private int orderNum;
}
