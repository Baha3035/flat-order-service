package kg.megacom.FlatOrdering.HouseFlatApp.controllers;

import kg.megacom.FlatOrdering.HouseFlatApp.models.dto.ImageDto;
import kg.megacom.FlatOrdering.HouseFlatApp.models.inputs.InputImageData;
import kg.megacom.FlatOrdering.HouseFlatApp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/image")
public class ImageController implements BaseCrudController<ImageDto, Long>{
    @Autowired
    private ImageService imageService;

    @PostMapping("/save-image")
    public ResponseEntity<List<ImageDto>> save(@RequestBody List<InputImageData> inputImageDataList){
        return new ResponseEntity<>(imageService.saveWithInput(inputImageDataList), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ImageDto> save(ImageDto imageDto) {
        return null;
    }

    @Override
    public ResponseEntity<ImageDto> update(ImageDto imageDto) {
        return null;
    }

    @Override
    public ResponseEntity<ImageDto> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ImageDto>> findAll() {
        return null;
    }
}
