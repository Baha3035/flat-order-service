package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RegionDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/region")
public class RegionController implements BaseCrudController<RegionDto, Long>{
    @Autowired
    private RegionService regionService;

    @PostMapping("save-region")
    public ResponseEntity<RegionDto> save(@RequestBody RegionDto regionDto){
        return new ResponseEntity<>(regionService.save(regionDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RegionDto> update(RegionDto regionDto) {
        return null;
    }

    @Override
    public ResponseEntity<RegionDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<RegionDto>> findAll() {
        return null;
    }
}
