package org.dimigo.gui.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Stage) throws IOException {
        Parent main = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Stage.setTitle("Bibliotheca (Made By 이우진)"); // 창 이름
        Stage.setScene(new Scene(main,600,400)); // 창 크기
        Stage.setResizable(false); // 창 크기 변경 불가
        Stage.show(); // 창 띄우기
    }


    @FXML Button btn_search;
    @FXML Button btn_info;
    @FXML Button btn_best;
    @FXML Button btn_dev;
    @FXML Button btn_exit;

    @FXML
    public void handleBtnSearch(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_search.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookSearch.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void handleBtnInfo(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_info.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckInfo.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void handleBtnBest(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_best.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BestSeller.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void handleBtnDev(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_dev.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("InfoDeveloper.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void handleBtnExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_exit.getScene().getWindow();
        stage.close();
    }
}
