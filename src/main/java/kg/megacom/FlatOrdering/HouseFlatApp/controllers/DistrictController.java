package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.DistrictDto;
import kg.megacom.FlatOrdering.HouseFlatApp.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/district")
public class DistrictController implements BaseCrudController<DistrictDto, Long> {
    @Autowired
    private DistrictService districtService;

    @PostMapping("/save-district")
    public ResponseEntity<DistrictDto> save(@RequestBody DistrictDto districtDto){
        return new ResponseEntity<>(districtService.save(districtDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DistrictDto> update(DistrictDto districtDto) {
        return null;
    }

    @Override
    public ResponseEntity<DistrictDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<DistrictDto>> findAll() {
        return null;
    }
}
