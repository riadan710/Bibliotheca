package org.dimigo.gui.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class BookSearch implements Initializable{
    @FXML
    public Button btn_back;
    public Button btn_search;
    public Button btn_link;
    public ComboBox<String> combo_box;
    public TextField getSearch;
    public Label label_caution;

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
        if(selectOption.equals("제목 + 작가")) {
            label_caution.setText("제목을 먼저 검색 후 작가를 검색해주세요");
        }
        else {
            label_caution.setText("");
        }
    }

    int check=0;
    String TitlegetText;

    public void handleBtnSearch(ActionEvent event) {
        label_caution.setText("");
        if(selectOption.equals("제목")) {
            check = 0;
            try {
                String getText = getSearch.getText();
                String text = URLEncoder.encode(getText, "UTF-8");
                String apiURL = "http://www.nl.go.kr/app/nl/search/openApi/search.jsp?key=78fb0bb0dd7a8847a2bb42e557ca630c&pageSize=10&pageNum=1&category=dan&kwd=" + text;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(selectOption.equals("작가")) {
            check = 0;
            try {
                String getText = getSearch.getText();
                String text = URLEncoder.encode(getText, "UTF-8");
                String apiURL = "http://www.nl.go.kr/app/nl/search/openApi/search.jsp?key=78fb0bb0dd7a8847a2bb42e557ca630c&pageSize=10&pageNum=1&category=dan&topF1=author&kwd=" + text;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                System.out.println(response.toString());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if(selectOption.equals("제목 + 작가")) {
            if(check == 0) {
                try {
                    TitlegetText = getSearch.getText();
                    String text = URLEncoder.encode(TitlegetText, "UTF-8");
                    String apiURL = "http://www.nl.go.kr/app/nl/search/openApi/search.jsp?key=78fb0bb0dd7a8847a2bb42e557ca630c&pageSize=10&pageNum=1&category=dan&kwd=" + text;
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();
                    System.out.println(response.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
                check++;
            }
            else if(check == 1) {
                try {
                    String getText = getSearch.getText();
                    String text = URLEncoder.encode(getText, "UTF-8");
                    String apiURL = "http://www.nl.go.kr/app/nl/search/openApi/search.jsp?key=78fb0bb0dd7a8847a2bb42e557ca630c&pageSize=10&category=dan&detailSearch=true&f1=title&v1=" + TitlegetText + "&f2=author&v2=" + text;
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    BufferedReader br;
                    if(responseCode==200) { // 정상 호출
                        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {  // 에러 발생
                        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br.readLine()) != null) {
                        response.append(inputLine);
                    }
                    br.close();
                    System.out.println(response.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
                check = 0;
            }
        }
    }

    public void handleBtnLink(ActionEvent event) {
        try {
            URI uri = new URI("https://reading.gglec.go.kr/r/reading/search/schoolSearchForm.jsp");
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_box.getItems().addAll("제목", "작가", "제목 + 작가");
    }
}