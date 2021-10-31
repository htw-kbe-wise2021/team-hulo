package com.teamhulo.kbeex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class KbeExApplication {
	public static void main(String[] args) {
		SpringApplication.run(KbeExApplication.class, args);

		SongLibrary songLibrary = new SongLibrary();

		List<Song> list = songLibrary.getSongList();
		for (Song song: list) {
			System.out.println(song.getTitle());
		}
	}
}
