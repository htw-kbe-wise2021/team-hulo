package com.teamhulo.kbeex;

import org.json.*;

import java.io.*;
import java.util.*;

public class SongLibrary {
    private final List<Song> songList = new ArrayList<>();

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

        // Sorting the list for ascending id
        Collections.sort(songList, new SongComparator());
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
            // Prevent duplicate id in list
            return null;
        } else {
            songToSave.setId(searchForFreeId());
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

    public int searchForFreeId() {
        // TODO fix the possible gap between id 0 and index 1
        Collections.sort(songList, new SongComparator());

        for (int i = 0; i < songList.size()-1; i++) {
            if (songList.get(i).getId() != songList.get(i+1).getId()-1) {
                return songList.get(i).getId()+1;
            }
        }

        // Returning the next higher index of the last item in the list
        return songList.get(songList.size()-1).getId()+1;
    }
}

class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song o1, Song o2) {
        return Integer.compare(o1.getId(), o2.getId());
    }
}
