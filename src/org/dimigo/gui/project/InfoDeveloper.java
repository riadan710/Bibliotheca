package org.dimigo.gui.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class InfoDeveloper {
    @FXML
    public Button btn_back;
    public Button btn_github;
    public Button btn_blog;

    @FXML
    public void handleBtnBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void handleBtnGithub(ActionEvent event) {
        try {
            URI uri = new URI("https://github.com/riadan710");
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleBtnBlog(ActionEvent event) {
        try {
            URI uri = new URI("https://blog.naver.com/riadan710");
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
