package orbitor.bionic.cachedapi.service;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import orbitor.bionic.cachedapi.entity.Album;
import orbitor.bionic.cachedapi.exception.ResourceAlreadyExistsException;
import orbitor.bionic.cachedapi.exception.ResourceNotFoundException;
import orbitor.bionic.cachedapi.mapper.AlbumMapper;
import orbitor.bionic.cachedapi.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @Cacheable(value = "albumCache", key = "#title")
    public AlbumDto getAlbumByTitle(String title) {
        System.out.println("Hitting database");
        Optional<Album> optionalAlbum = albumRepository.findByTitle(title);
        if (optionalAlbum.isEmpty())
            throw new ResourceNotFoundException("No albums found with the title: " + title);
        Album album = optionalAlbum.get();
        AlbumDto albumDto = AlbumMapper.mapToAlbumDto(album, new AlbumDto());

        return albumDto;
    }

    @Override
    @CachePut(value = "albumCache", key = "#albumDto.title")
    public void addNewAlbum(AlbumDto albumDto) {
        System.out.println("Hitting database");
        Optional<Album> optionalAlbum = albumRepository.findByTitle(albumDto.getTitle());
        if (optionalAlbum.isPresent())
            throw new ResourceAlreadyExistsException("Album already exists for the title: " + albumDto.getTitle());
        Album album = AlbumMapper.mapToAlbum(albumDto, new Album());
        albumRepository.save(album);
    }

    @Override
    @CachePut(value = "albumCache", key = "#albumDto.title")
    public void updateNewAlbumByTitle(AlbumDto albumDto) {
        System.out.println("Hitting database");
        Optional<Album> optionalAlbum = albumRepository.findByTitle(albumDto.getTitle());
        if (optionalAlbum.isEmpty())
            throw new ResourceNotFoundException("No existing album found for title: " + albumDto.getTitle());
        Album album = AlbumMapper.mapToAlbum(albumDto, optionalAlbum.get());
        albumRepository.save(album);
    }

    @Override
    @CacheEvict(value = "albumCache", key = "#title")
    public void deleteAlbumByTitle(String title) {
        System.out.println("Hitting database");
        Optional<Album> optionalAlbum = albumRepository.findByTitle(title);
        if (optionalAlbum.isEmpty())
            throw new ResourceNotFoundException("No existing album found for title: " + title);
        albumRepository.deleteById(optionalAlbum.get().getId());
    }
}
