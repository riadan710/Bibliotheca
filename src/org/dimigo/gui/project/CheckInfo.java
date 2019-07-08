package org.dimigo.gui.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CheckInfo {
    @FXML
    public Button btn_back;
    public Button btn_check;
    public TextField txtSearch;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Button btn_stid;

    private  static void copyFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt")))  {
            String str;
            while((str = br.readLine()) != null) {
                str = br.readLine();
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void handleBtnStID(ActionEvent event) {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("학생증 바코드 밑에 작게 적힌 5자리 코드");
    }

    Calendar cal = Calendar.getInstance();

    public void handleBtnCheck(ActionEvent event) {
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        String text = txtSearch.getText(); // 텍스트 필드에 입력한 값을 가져옴
        int i = 0;

        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader br = null;
        List<String> tmpList = new ArrayList<String>();

        try{
            br = Files.newBufferedReader(Paths.get("data.csv"));
            Charset.forName("UTF-8");
            String line = "";

            while((line = br.readLine()) != null){
                //CSV 1행을 저장하는 리스트
                String array[] = line.split(",");
                //배열에서 리스트 반환
                tmpList = Arrays.asList(array);
                if(text.equals(tmpList.get(0))) {
                    label1.setText("이름 : "+ tmpList.get(1) + ", 학번 : " + tmpList.get(2));
                    i++;
                    if(tmpList.get(3).equals("X")) {
                        label2.setText("대출여부 : X");
                        label3.setText("빌린 책이 없습니다.");
                    }
                    else if(tmpList.get(3).equals("O")) {
                        int date = (Integer.parseInt(tmpList.get(4)))%100; // 반납일
                        int back = Integer.parseInt(tmpList.get(4));
                        label2.setText("대출여부 : O, 반납일 : " + String.format("%04d.%02d.%02d", back/10000, (back%1000)/100, back%100));
                        int today = (cal.get(cal.DATE)); // 오늘 날짜
                        if(date > today) {
                            label3.setText("예정 반납일이 " + (date - today) + "일 남았습니다.");
                        }
                        else if(date == today) {
                            label3.setText("예정 반납일이 오늘입니다.");
                        }
                        else {
                            label3.setText("예정 반납일이 " + (today - date) + "일 지났습니다.");
                        }
                    }
                }
                ret.add(tmpList);
            }
            if(i == 0) {
                label1.setText("데이터가 없습니다");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(br != null){
                    br.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
