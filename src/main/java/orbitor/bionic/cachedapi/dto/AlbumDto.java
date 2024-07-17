package orbitor.bionic.cachedapi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {
    private String title;

    private String artist;

    private String genre;

    private Integer totalTracks;

    private Integer durationMinutes;
}
