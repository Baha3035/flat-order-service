package kg.megacom.FlatOrdering.HouseFlatApp.models.dto;

import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private HouseDto house;
    private String url;
    private int orderNum;
}
