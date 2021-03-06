package nsbm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nsbm.models.postgraduate;
import nsbm.models.undergraduate;

import java.io.IOException;
import java.sql.SQLException;

public class findstudent {

    private static String student_id;

    @FXML
    private JFXTextField undergrad;

    @FXML
    private JFXTextField postgrad;

    @FXML
    private JFXButton undersearch;

    @FXML
    private JFXButton postsearch;

    @FXML
    void searchpostgrad(ActionEvent event) throws IOException, SQLException {
        String typed=postgrad.getText();
        if(!typed.equals("")){
            student_id=typed;
        }
        postgraduate postgradu = postgraduate.findPostgraduate(student_id);
        if(postgradu.getCourse_id()!=null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/update/editpostgrad.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Postgraduate Update");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Error ");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    @FXML
    void searchundergrad(ActionEvent event) throws IOException, SQLException {
        String typed=undergrad.getText();
        if(!typed.equals("")){
            student_id=typed;
        }
        undergraduate under = undergraduate.findUndergraduate(student_id);
        if(under.getCourse_id()!=null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/student/update/editundergrad.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Undergraduate Update");
            stage.setScene(new Scene(root1));
            stage.show();
        }else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/view/alertbox/searchnotfound.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(new Scene(root1));
            stage.show();
        }
    }

    public static String getStudent_id(){
        return student_id;
    }

}
