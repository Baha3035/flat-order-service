package kg.megacom.FlatOrdering.HouseFlatApp.mappers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ImageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Image;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import java.util.List;
import java.util.stream.Collectors;
public class ImageMapper implements BaseMapper<ImageDto, Image> {
    private MapperFacade mapperFacade;

    public ImageMapper() {
        MapperFactory mapperFactory = new DefaultMapperFactory
                .Builder().build();

        mapperFactory.classMap(ImageDto.class, Image.class)
                .byDefault();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Image toEntity(ImageDto dto) {
        return mapperFacade.map(dto, Image.class);
    }

    @Override
    public ImageDto toDto(Image entity) {
        return mapperFacade.map(entity, ImageDto.class);
    }

    @Override
    public List<Image> toEntities(List<ImageDto> dtoList) {
        return dtoList.stream().map(x->mapperFacade.map(x, Image.class)).collect(Collectors.toList());
    }

    @Override
    public List<ImageDto> toDtoList(List<Image> entities) {
        return entities.stream().map(x->mapperFacade.map(x, ImageDto.class)).collect(Collectors.toList());
    }
}
