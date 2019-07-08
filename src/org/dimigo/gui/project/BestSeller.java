package org.dimigo.gui.project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BestSeller implements Initializable {
    @FXML
    public ComboBox<String> combo_box;
    public Button btn_back;
    public ListView list_view;
    public Label txt_warning;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_box.getItems().addAll("주간", "월간");
    }

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

    String selectOption = "";
    @FXML
    public void comboChanged(ActionEvent event) {
        selectOption = combo_box.getValue().toString();
    }

    @FXML
    public void handleBtnLoad(ActionEvent event) {
        try {
            if(selectOption == "월간") {
                Document doc = Jsoup.connect("https://www.aladin.co.kr/shop/common/wbest.aspx?BranchType=1&BestType=MonthlyBest").get();
                Elements name = doc.select("div.ss_book_list ul li a b");
                List<String> nameList = name.eachText();
                ObservableList<String> BookName = FXCollections.observableArrayList(nameList);
                list_view.setItems(BookName);
            }
            else if(selectOption == "주간") {
                Document doc = Jsoup.connect("https://www.aladin.co.kr/shop/common/wbest.aspx?BranchType=1&BestType=Bestseller").get();
                Elements name = doc.select("div.ss_book_list ul li a b");
                List<String> nameList = name.eachText();
                ObservableList<String> BookName = FXCollections.observableArrayList(nameList);
                list_view.setItems(BookName);
            }
            else {
                txt_warning.setText("옵션을 선택하지 않았습니다!");
            }
        } catch(Exception e) {
            e.printStackTrace(); // 예외처리
        }
    }
}
