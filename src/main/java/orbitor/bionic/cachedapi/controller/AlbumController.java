package orbitor.bionic.cachedapi.controller;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import orbitor.bionic.cachedapi.dto.ResponseDto;
import orbitor.bionic.cachedapi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/getAlbumsByTitle")
    public ResponseEntity<AlbumDto> getAlbumsByTitle(@RequestParam String title) {
        AlbumDto albumDto = albumService.getAlbumByTitle(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(albumDto);
    }

    @PostMapping("/addNewAlbum")
    public ResponseEntity<ResponseDto> addNewAlbum(@RequestBody AlbumDto albumDto) {
        albumService.addNewAlbum(albumDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Resource created!"));
    }

    @PutMapping("/updateAlbumByTitle")
    public ResponseEntity<ResponseDto> updateAlbumByTitle(@RequestBody AlbumDto albumDto) {
        albumService.updateNewAlbumByTitle(albumDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Resource updated!"));
    }

    @DeleteMapping("/deleteAlbumByTitle")
    public ResponseEntity<ResponseDto> deleteAlbumByTitle(@RequestParam String title) {
        albumService.deleteAlbumByTitle(title);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK, "Resource deleted!"));
    }
}
