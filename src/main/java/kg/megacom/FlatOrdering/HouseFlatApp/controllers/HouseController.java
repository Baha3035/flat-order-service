package kg.megacom.FlatOrdering.HouseFlatApp.controllers;


import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.HouseDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputHouseData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/house")
public class HouseController implements BaseCrudController<HouseDto, Long> {
    @Autowired
    private HouseService houseService;

    @PostMapping("/save-with-input")
    public ResponseEntity<HouseDto> save(@RequestBody InputHouseData inputHouseData){
        return new ResponseEntity<>(houseService.saveWithInput(inputHouseData), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HouseDto> save(HouseDto houseDto) {
        return null;
    }

    @Override
    public ResponseEntity<HouseDto> update(HouseDto houseDto) {
        return null;
    }

    @Override
    public ResponseEntity<HouseDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<HouseDto>> findAll() {
        return null;
    }

    @GetMapping("/find-by-district")
    public ResponseEntity<List<HouseDto>> findByDistrictId(@RequestParam Long id){
        return new ResponseEntity<>(houseService.findByDistrictId(id), HttpStatus.OK);
    }

    @GetMapping("find-by-city")
    public ResponseEntity<List<HouseDto>> findByCityId(@RequestParam Long id){
        return new ResponseEntity<>(houseService.findByCityId(id), HttpStatus.OK);
    }

    @GetMapping("find-by-type")
    public ResponseEntity<List<HouseDto>> findByTypeId(@RequestParam Long id){
     return new ResponseEntity<>(houseService.findByTypeId(id), HttpStatus.OK);
    }


    @GetMapping("find-by-floor")
    public ResponseEntity<List<HouseDto>> findByFloor(@RequestParam int floor) {
        return new ResponseEntity<>(houseService.findByFloor(floor), HttpStatus.OK);
    }

    @GetMapping("find-by-room")
    public ResponseEntity<List<HouseDto>> findByRoom(@RequestParam int room) {
        return new ResponseEntity<>(houseService.findByRoom(room), HttpStatus.OK);
    }

    @GetMapping("find-by-internet")
    public ResponseEntity<List<HouseDto>> findByInternet(@RequestParam boolean internet) {
        return new ResponseEntity<>(houseService.findByInternet(internet), HttpStatus.OK);
    }


    @GetMapping("find-by-furniture")
    public ResponseEntity<List<HouseDto>> findByFurniture(@RequestParam boolean furniture) {
        return new ResponseEntity<>(houseService.findByFurniture(furniture), HttpStatus.OK);
    }
}
