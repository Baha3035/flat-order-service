package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ReserveHistoryDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputReserveHistoryData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.ReserveHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/reserves")
public class ReserveHistoryController implements BaseCrudController<ReserveHistoryDto, Long>{
    @Autowired
    private ReserveHistoryService reserveHistoryService;

    @PostMapping("/save-reserve")
    public ResponseEntity<ReserveHistoryDto> saveWithInput(@RequestBody InputReserveHistoryData inputReserveHistoryData){
        return new ResponseEntity<>(reserveHistoryService.saveWithInput(inputReserveHistoryData), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ReserveHistoryDto> save(ReserveHistoryDto reserveHistoryDto) {
        return null;
    }

    @Override
    public ResponseEntity<ReserveHistoryDto> update(ReserveHistoryDto reserveHistoryDto) {
        return null;
    }

    @Override
    public ResponseEntity<ReserveHistoryDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ReserveHistoryDto>> findAll() {
        return null;
    }
}
