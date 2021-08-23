package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.RequestDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputRequestData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/request")
public class RequestController implements BaseCrudController<RequestDto, Long>{
    @Autowired
    private RequestService requestService;

    @PostMapping("save-request")
    public ResponseEntity<RequestDto> save(@RequestBody InputRequestData inputRequestData){
        return new ResponseEntity<>(requestService.saveWithInput(inputRequestData), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RequestDto> save(RequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<RequestDto> update(RequestDto requestDto) {
        return null;
    }

    @Override
    public ResponseEntity<RequestDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<RequestDto>> findAll() {
        return null;
    }
}
