package orbitor.bionic.cachedapi.mapper;

import orbitor.bionic.cachedapi.dto.AlbumDto;
import orbitor.bionic.cachedapi.entity.Album;

public class AlbumMapper {

    public static AlbumDto mapToAlbumDto(Album album, AlbumDto albumDto) {
        albumDto.setArtist(album.getArtist());
        albumDto.setGenre(album.getGenre());
        albumDto.setTitle(album.getTitle());
        albumDto.setTotalTracks(album.getTotalTracks());
        albumDto.setDurationMinutes(album.getDurationMinutes());

        return albumDto;
    }

    public static Album mapToAlbum(AlbumDto albumDto, Album album) {
        album.setArtist(albumDto.getArtist());
        album.setGenre(albumDto.getGenre());
        album.setTitle(albumDto.getTitle());
        album.setTotalTracks(albumDto.getTotalTracks());
        album.setDurationMinutes(albumDto.getDurationMinutes());

        return album;
    }
}
