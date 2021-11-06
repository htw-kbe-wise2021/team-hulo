package com.teamhulo.kbeex;

import org.json.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SongLibrary {
    private final List<Song> songList = new LinkedList<>();

    public SongLibrary() {
        JSONArray array = readJSON("kbe-ex/src/main/resources/songs.json");
        for (int i = 0; i < array.length(); i++) {
            Song song = new Song();

            song.setId(array.getJSONObject(i).getInt("id"));
            song.setTitle(array.getJSONObject(i).getString("title"));
            song.setArtist(array.getJSONObject(i).getString("artist"));
            song.setLabel(array.getJSONObject(i).getString("label"));
            song.setReleased(array.getJSONObject(i).getInt("released"));

            songList.add(song);
        }
    }

    private JSONArray readJSON(String path) {
        StringBuilder jsonStringBuilder = new StringBuilder();

        try {
            File jsonFile = new File(path);
            Scanner scanner = new Scanner(jsonFile);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                jsonStringBuilder.append(data);
            }

            scanner.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        return new JSONArray(jsonStringBuilder.toString());
    }

    public List<Song> getSongList() {
        return songList;
    }

    public Song findAnIndex(int id) {

        for (Song song : songList) {
            if (song.getId() == id) {
                return song;
            }
        }

        return null;
    }

    public Song saveSong(Song songToSave) {

        if (findAnIndex(songToSave.getId()) != null) {
            return null;
        } else {
            songList.add(songToSave);
            return songToSave;
        }
    }

    public Song replaceSong(Song replacementSong, int id) {
        Song songToChange = findAnIndex(id);

        if (songToChange != null) {
            songToChange.setTitle(replacementSong.getTitle());
            songToChange.setArtist(replacementSong.getArtist());
            songToChange.setLabel(replacementSong.getLabel());
            songToChange.setReleased(replacementSong.getReleased());
        }

        return songToChange;
    }

    public void deleteSong(int id) {
        Song songToDelete = findAnIndex(id);

        if (songToDelete != null) {
            songList.remove(songToDelete);
        }
    }
}
