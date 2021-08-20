package kg.megacom.FlatOrdering.HouseFlatApp.services;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ImageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputImageData;

import java.util.List;

public interface ImageService extends BaseCrudService<ImageDto, Long>{
   List<ImageDto>  saveWithInput(List<InputImageData> inputImageDataList);
    void saveWithUrlAndHouse(String url, HouseDto houseDto);
}
