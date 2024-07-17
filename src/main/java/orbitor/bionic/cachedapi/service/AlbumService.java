package orbitor.bionic.cachedapi.service;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumService {

    AlbumDto getAlbumByTitle(String title);

    void addNewAlbum(AlbumDto albumDto);

    void updateNewAlbumByTitle(AlbumDto albumDto);

    void deleteAlbumByTitle(String title);

}
