package orbitor.bionic.cachedapi.service;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumService {

    AlbumDto getAlbumByTitle(String title);
}
