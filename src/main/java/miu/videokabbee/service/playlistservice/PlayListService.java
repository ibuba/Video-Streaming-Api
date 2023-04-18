package miu.videokabbee.service.playlistservice;

import miu.videokabbee.domain.PlayList;

import java.util.List;

public interface PlayListService {
    PlayList createPlaylist(PlayList playlist);
    PlayList updatePlaylist(Long playlistId, PlayList playlist);
    void deletePlaylist(Long playlistId);
    PlayList getPlaylistById(Long playlistId);
    List<PlayList> getAllPlaylists();
    PlayList addVideoToPlaylist(Long playlistId, Long videoId);
    PlayList removeVideoFromPlaylist(Long playlistId, Long videoId);
}
