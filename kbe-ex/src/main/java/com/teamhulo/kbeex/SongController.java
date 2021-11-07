package com.teamhulo.kbeex;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SongController {

    private static final SongLibrary songLibrary = new SongLibrary();

    @GetMapping(path = "team-hulo/songs")
    public List<Song> provideAllSongs() {
        return songLibrary.getSongList();
    }

    @GetMapping(path = "team-hulo/songs/{id}")
    public Song provideSingleSong(@PathVariable int id) {
        return songLibrary.findAnIndex(id);
    }

    @PostMapping(path = "team-hulo/songs")
    public Song createSong(@RequestBody Song newSong) {
        return songLibrary.saveSong(newSong);
    }

    @PutMapping(path = "team-hulo/songs/{id}")
    public Song replaceSong(@RequestBody Song updatedSong, @PathVariable int id) {
        return songLibrary.replaceSong(updatedSong, id);
    }

    @DeleteMapping(path = "team-hulo/songs/{id}")
    public void deleteSong(@PathVariable int id) {
        songLibrary.deleteSong(id);
    }
}
