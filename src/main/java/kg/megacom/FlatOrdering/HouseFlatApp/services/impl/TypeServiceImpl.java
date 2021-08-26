package kg.megacom.FlatOrdering.HouseFlatApp.services.impl;

import kg.megacom.FlatOrdering.HouseFlatApp.dao.TypeRepo;
import kg.megacom.FlatOrdering.HouseFlatApp.exceptions.NotFoundByIdException;
import kg.megacom.FlatOrdering.HouseFlatApp.mappers.TypeMapper;
import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.TypeDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.entities.Type;
import kg.megacom.FlatOrdering.HouseFlatApp.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepo typeRepo;

    @Override
    public TypeDto save(TypeDto typeDto) {
        TypeMapper typeMapper = new TypeMapper();
        typeDto.setActive(true);
        return typeMapper.toDto(typeRepo.save(typeMapper.toEntity(typeDto)));
    }

    @Override
    public TypeDto update(TypeDto typeDto) {
        return null;
    }

    @Override
    public TypeDto findById(Long id) {
        TypeMapper typeMapper = new TypeMapper();
        Type type = typeRepo.findById(id).orElseThrow(()->new NotFoundByIdException("Type is not found!!!"));
        return typeMapper.toDto(type);
    }

    @Override
    public List<TypeDto> findAll() {
        return null;
    }
}
