package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.ImageRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.HouseMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.ImageMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ImageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputImageData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.HouseService;
import kg.megacom.FlatOrdering.HouseFlatApp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private HouseService houseService;

    @Override
    public List<ImageDto> saveWithInput(List<InputImageData> inputImageDataList) {
        HouseMapper houseMapper = new HouseMapper();
        ImageMapper imageMapper = new ImageMapper();

        return inputImageDataList.stream().map(x-> {
            ImageDto image = new ImageDto();
            image.setHouse(houseService.findById(x.getHouseId()));
            image.setOrderNum(x.getOrderNum());
            image.setUrl(x.getUrl());
            return save(image);
        }).collect(Collectors.toList());
    }

    @Override
    public void saveWithUrlAndHouse(String url, HouseDto houseDto) {
        ImageDto imageDto = new ImageDto();
        imageDto.setHouse(houseDto);
        imageDto.setUrl(url);
    }

    @Override
    public ImageDto save(ImageDto imageDto) {
        ImageMapper imageMapper = new ImageMapper();

        return imageMapper.toDto(imageRepo.save(imageMapper.toEntity(imageDto)));
    }

    @Override
    public ImageDto update(ImageDto imageDto) {
        return null;
    }

    @Override
    public ImageDto findById(Long id) {
        ImageMapper imageMapper = new ImageMapper();
        return imageMapper.toDto(imageRepo.findById(id).orElseThrow(()->new NotFoundByIdException("Image not found!!!")));
    }

    @Override
    public List<ImageDto> findAll() {
        return null;
    }
}
