package miu.videokabbee.controller;

import miu.videokabbee.domain.PlayList;
import miu.videokabbee.service.playlistservice.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlayListController {

    private final PlayListService playlistService;
    @Autowired
    public PlayListController(PlayListService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<PlayList>> getAllPlaylists() {
        List<PlayList> playlists = playlistService.getAllPlaylists();
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> getPlaylistById(@PathVariable("id") Long id) {
        PlayList playlist = playlistService.getPlaylistById(id);
        if (playlist != null) {
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PlayList> createPlaylist(@RequestBody PlayList playlist) {
        PlayList createdPlaylist = playlistService.createPlaylist(playlist);
        return new ResponseEntity<>(createdPlaylist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable("id") Long id, @RequestBody PlayList playlist) {
       // playlist.setId(id);
        PlayList updatedPlaylist = playlistService.updatePlaylist(id,playlist);
        if (updatedPlaylist != null) {
            return new ResponseEntity<>(updatedPlaylist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id){
        playlistService.deletePlaylist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
