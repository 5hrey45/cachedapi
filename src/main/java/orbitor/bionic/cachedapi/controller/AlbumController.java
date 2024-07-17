package orbitor.bionic.cachedapi.controller;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import orbitor.bionic.cachedapi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
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
}
