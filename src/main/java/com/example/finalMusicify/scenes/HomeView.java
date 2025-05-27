////package com.example.finalMusicify.scenes;
////
////import javafx.geometry.Pos;
////import javafx.scene.control.Label;
////import javafx.scene.image.Image;
////import javafx.scene.image.ImageView;
////import javafx.scene.layout.*;
////import javafx.scene.text.Font;
////
////public class HomeView {
////
////    public static Pane create() {
////        StackPane root = new StackPane();
////
////        try {
////            ImageView imageView = new ImageView(
////                    new Image(HomeView.class.getResource("/images/background1.jpg").toExternalForm())
////            );
////            imageView.setFitWidth(1100); // match your scene size
////            imageView.setFitHeight(700);
////            imageView.setPreserveRatio(false);
////            imageView.setOpacity(0.85);
////
////            root.getChildren().add(imageView);
////        } catch (Exception e) {
////            System.out.println("❌ Could not load background: " + e.getMessage());
////        }
////
////        Label title = new Label("🎶 Welcome to Musicify");
////        title.setFont(new Font("Arial", 32));
////        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 4, 0, 2, 2);");
////
////        root.getChildren().add(title);
////        StackPane.setAlignment(title, Pos.CENTER);
////
////        return root;
////    }
////}
//
//package com.example.finalMusicify.scenes;
//
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.layout.*;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaView;
//import javafx.scene.text.Font;
//
//public class HomeView {
//
//    public static Pane create() {
//        StackPane root = new StackPane();
//
//        try {
//            var videoURL = HomeView.class.getResource("/videos/vidconv.mp4");
//            if (videoURL == null) {
//                System.out.println("❌ Video not found at /videos/vidconv.mp4");
//            } else {
//                Media media = new Media(videoURL.toExternalForm());
//                MediaPlayer player = new MediaPlayer(media);
//                player.setAutoPlay(true);
//                player.setCycleCount(MediaPlayer.INDEFINITE);
//                player.setMute(true); // No sound for background
//
//                MediaView mediaView = new MediaView(player);
//                mediaView.setPreserveRatio(false);
//                mediaView.setFitWidth(1100);
//                mediaView.setFitHeight(700);
//
//                root.getChildren().add(mediaView);
//            }
//        } catch (Exception e) {
//            System.out.println("❌ Failed to load video: " + e.getMessage());
//        }
//
//        Label title = new Label("🎶 Welcome to Musicify");
//        title.setFont(new Font("Arial", 32));
//        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 4, 0, 2, 2);");
//
//        root.getChildren().add(title);
//        StackPane.setAlignment(title, Pos.CENTER);
//        root.setStyle("-fx-background-color: black;"); // fallback background
//
//        return root;
//    }
//}
package com.example.finalMusicify.scenes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;

public class HomeView {

    public static Pane create() {
        StackPane root = new StackPane();

        try {
            var videoURL = HomeView.class.getResource("/videos/vidconv.mp4");
            if (videoURL == null) {
                System.out.println("❌ Video not found at /videos/vidconv.mp4");
            } else {
                Media media = new Media(videoURL.toExternalForm());
                MediaPlayer player = new MediaPlayer(media);
                player.setAutoPlay(true);
                player.setCycleCount(MediaPlayer.INDEFINITE);
                player.setMute(true);

                MediaView mediaView = new MediaView(player);
                mediaView.setPreserveRatio(false);

                // 📌 Bind video to available space (avoids cut-off)
                mediaView.fitWidthProperty().bind(root.widthProperty());
                mediaView.fitHeightProperty().bind(root.heightProperty());

                root.getChildren().add(mediaView);
                StackPane.setAlignment(mediaView, Pos.CENTER);
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to load video: " + e.getMessage());
        }

        Label title = new Label("🎶 Welcome to Musicify");
        title.setFont(new Font("Arial", 32));
        title.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-effect: dropshadow(gaussian, black, 4, 0, 2, 2);");

        root.getChildren().add(title);
        StackPane.setAlignment(title, Pos.CENTER);
        root.setStyle("-fx-background-color: black;");

        return root;
    }
}
