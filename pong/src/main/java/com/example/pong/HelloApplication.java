package com.example.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    private double ballX = 300;
    private double ballY = 200;
    private double ballSpeedX = 3;
    private double ballSpeedY = 3;
    private double paddle1Y = 175;
    private double paddle2Y = 175;
    private boolean moveUp1 = false;
    private boolean moveDown1 = false;
    private boolean moveUp2 = false;
    private boolean moveDown2 = false;

    private int playerScore1 = 0;
    private int playerScore2 = 0;

    @Override
    public void start(Stage primaryStage) {

        Circle ball = new Circle(ballX, ballY, 10);
        ball.setFill(Color.BLACK);

        Rectangle paddle1 = new Rectangle(25, paddle1Y, 10, 50);
        paddle1.setFill(Color.BLACK);

        Rectangle paddle2 = new Rectangle(565, paddle2Y, 10, 50);
        paddle2.setFill(Color.BLACK);
        Text score = new Text();
        score.setFont(Font.font(24));
        score.setX(300);
        score.setY(50);

        Pane root = new Pane();
        root.getChildren().addAll(ball, paddle1, paddle2);

        Scene scene = new Scene(root, 600, 400);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                moveUp1 = true;
            }
            if (e.getCode() == KeyCode.S) {
                moveDown1 = true;
            }
            if (e.getCode() == KeyCode.UP) {
                moveUp2 = true;
            }
            if (e.getCode() == KeyCode.DOWN) {
                moveDown2 = true;
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
                moveUp1 = false;
            }
            if (e.getCode() == KeyCode.S) {
                moveDown1 = false;
            }
            if (e.getCode() == KeyCode.UP) {
                moveUp2 = false;
            }
            if (e.getCode() == KeyCode.DOWN) {
                moveDown2 = false;
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if (moveUp1) {
                paddle1Y -= 7;
            }
            if (moveDown1) {
                paddle1Y += 7;
            }
            if (moveUp2) {
                paddle2Y -= 7;
            }
            if (moveDown2) {
                paddle2Y += 7;
            }
            ballX += ballSpeedX;
            ballY += ballSpeedY;

            if (ballX <= 35) {
                if (ballY > paddle1Y && ballY < paddle1Y + 50) {
                    ballSpeedX = -ballSpeedX;
                } else {
                    playerScore1 ++;
                    score.setText("Player 1: " + playerScore2 + "  Player 2: " + playerScore1);
                    ballX = 300;
                    ballY = 200;
                    ballSpeedX = 3;
                    ballSpeedY = 3;
                }
            }
            if (ballX >= 565) {
                if (ballY > paddle2Y && ballY < paddle2Y + 50) {
                    ballSpeedX = -ballSpeedX;
                } else {
                    playerScore2++;
                    score.setText("Player 1: " + playerScore2 + "  Player 2: " + playerScore1);

                    ballX = 300;
                    ballY = 200;
                    ballSpeedX = 3;
                    ballSpeedY = 3;
                }
            }
            if (ballY <= 10 || ballY >= 390) {
                ballSpeedY = -ballSpeedY;
            }
            if (paddle1Y <= 0) {
                paddle1Y = 0;
            } else if (paddle1Y >= 390) {
                paddle1Y = 390;
            }

            if (paddle2Y <= 0) {
                paddle2Y = 0;
            } else if (paddle2Y >= 390) {
                paddle2Y = 390;
            }
            paddle1.setY(paddle1Y);
            paddle2.setY(paddle2Y);
            ball.setCenterX(ballX);
            ball.setCenterY(ballY);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        score.setText("Player 1: " + playerScore2 + "  Player 2: " + playerScore1);
        root.getChildren().add(score);
        primaryStage.setTitle("Pong");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

