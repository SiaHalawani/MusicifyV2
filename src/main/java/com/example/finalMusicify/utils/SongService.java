package com.example.finalMusicify.utils;

import com.example.finalMusicify.model.Song;
import com.example.finalMusicify.repository.SongRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class SongService {
    private static final String BASE_URL = "http://localhost:8080/api/songs";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    // === FETCH ALL SONGS ===
    public static List<Map<String, String>> fetchSongs() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            List<Map<String, Object>> rawList = gson.fromJson(response.body(), listType);

            List<Map<String, String>> cleaned = new ArrayList<>();
            for (Map<String, Object> song : rawList) {
                Map<String, String> simple = new HashMap<>();
                simple.put("id", String.valueOf(song.get("id")));
                simple.put("title", String.valueOf(song.get("title")));
                simple.put("genre", String.valueOf(song.get("genre")));
                simple.put("mood", String.valueOf(song.get("mood")));

                cleaned.add(simple);
            }

            return cleaned;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    // === DELETE SONG ===
    public static boolean deleteSong(String id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // === UPDATE SONG ===
    public static boolean updateSong(String id, Map<String, String> songData) {
        try {
            String json = gson.toJson(songData);
            System.out.println("PUT /api/songs/" + id + " -> " + json); // ✅ log it

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response: " + response.statusCode() + " - " + response.body()); // ✅ log it

            return response.statusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // === ADD SONG ===
    public static boolean addSong(Map<String, String> songData) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(songData)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200 || response.statusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean addSongToPlaylist(Map<String, String> data) {
        try {
            String json = new Gson().toJson(data);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/api/playlistsongs"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200 || response.statusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // At the end of ApiService.java
    public static boolean postToPlaylist(String playlistId, Map<String, String> data) {
        try {
            String json = gson.toJson(data);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/playlists/" + playlistId + "/songs"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200 || response.statusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
