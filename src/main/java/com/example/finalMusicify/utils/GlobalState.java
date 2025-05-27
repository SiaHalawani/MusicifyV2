package com.example.finalMusicify.utils;

public class GlobalState {
    private static int userId;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int id) {
        userId = id;
    }
}
