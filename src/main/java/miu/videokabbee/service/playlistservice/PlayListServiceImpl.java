package miu.videokabbee.service.playlistservice;

import miu.videokabbee.domain.PlayList;
import miu.videokabbee.repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PlayListServiceImpl implements PlayListService {


    private final PlayListRepository playListRepository;

    @Autowired
    public PlayListServiceImpl(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }


    @Override
    public PlayList createPlaylist(PlayList playlist) {
        return playListRepository.save(playlist);
    }

    @Override
    public PlayList updatePlaylist(Long playlistId, PlayList playlist) {
        PlayList existingPlaylist = getPlaylistById(playlistId);
        existingPlaylist.setTitle(playlist.getTitle());
        existingPlaylist.setDescription(playlist.getDescription());
        // existingPlaylist.setVideos(playlist.getVideos());
        return playListRepository.save(existingPlaylist);
    }

    @Override
    public void deletePlaylist(Long playlistId) {
        playListRepository.deleteById(playlistId);

    }

    @Override
    public PlayList getPlaylistById(Long playlistId) {
        return playListRepository.findById(playlistId)
                .orElseThrow(() -> new Error("Playlist not found with id: " + playlistId));
    }

    @Override
    public List<PlayList> getAllPlaylists() {
        return playListRepository.findAll();
    }

    @Override
    public PlayList addVideoToPlaylist(Long playlistId, Long videoId) {
        PlayList existingPlaylist = getPlaylistById(playlistId);
        existingPlaylist.getVideoIds().add(videoId);
        return playListRepository.save(existingPlaylist);
    }

    @Override
    public PlayList removeVideoFromPlaylist(Long playlistId, Long videoId) {
        PlayList existingPlaylist = getPlaylistById(playlistId);
        existingPlaylist.getVideoIds().remove(playlistId);
        return playListRepository.save(existingPlaylist);
    }
}
