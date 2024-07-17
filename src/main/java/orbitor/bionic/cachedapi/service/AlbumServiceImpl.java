package orbitor.bionic.cachedapi.service;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import orbitor.bionic.cachedapi.entity.Album;
import orbitor.bionic.cachedapi.exception.ResourceNotFoundException;
import orbitor.bionic.cachedapi.mapper.AlbumMapper;
import orbitor.bionic.cachedapi.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Cacheable(value = "AlbumCache", key = "#title")
    public AlbumDto getAlbumByTitle(String title) {
        System.out.println("Hitting database");
        Optional<Album> optionalAlbum = albumRepository.findByTitle(title);
        if (optionalAlbum.isEmpty())
            throw new ResourceNotFoundException("No albums found with the title: " + title);
        Album album = optionalAlbum.get();
        AlbumDto albumDto = AlbumMapper.mapToAlbumDto(album, new AlbumDto());

        return albumDto;
    }
}
