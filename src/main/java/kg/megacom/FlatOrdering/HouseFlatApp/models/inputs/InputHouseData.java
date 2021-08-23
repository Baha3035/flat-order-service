package kg.megacom.FlatOrdering.HouseFlatApp.models.inputs;

import lombok.Data;

import java.util.List;

@Data
public class InputHouseData {
    private int room;
    private String description;
    private int floor;
    private double area;
    private double price;
    private Long typeId;
    private boolean internet;
    private boolean furniture;
    private Long ownerUserId;
    private Long cityVillageId;
    private Long districtId;
    private double lat;
    private double lon;
    private String address;
}
