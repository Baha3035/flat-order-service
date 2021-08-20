package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HouseDto {
    private Long id;
    private int rooms;
    private String descriptions;
    private int floor;
    private double area;
    private double price;
    private TypeDto type;
    private boolean internet;
    private boolean furniture;
    private UserDto user;
    private CityVillageDto cityVillage;
    private DistrictDto district;
    private LocalDate addDate;
    private LocalDate editDate;
    private double lat;
    private double lon;
    private String address;
}
