package nsbm.controllers;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import nsbm.models.undergraduate;

import java.sql.SQLException;
import java.util.ArrayList;

public class Regundergrad {

    @FXML
    public JFXButton tosecond1;
    public JFXDatePicker regdate;
    public JFXTextField intake;
    public JFXComboBox course;
    public JFXComboBox faculty;
    public JFXTextField alindex;
    public JFXTextField zscore;
    public JFXTextField rank;
    public JFXComboBox stream;
    public JFXComboBox subject1;
    public JFXComboBox subject2;
    public JFXComboBox subject3;
    public JFXTextField score1;
    public JFXTextField score2;
    public JFXTextField score3;
    public JFXTextField firstname;
    public JFXTextField lastname;
    public JFXTextField nic;
    public JFXTextField email;
    public JFXRadioButton male;
    public JFXRadioButton female;
    public JFXDatePicker dob;
    public JFXTextField mobile;
    public JFXTextArea address;
    public JFXTextField studentid;
    public JFXTextField regnumber;

    @FXML
    private AnchorPane third;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton tosecond;

    @FXML
    private JFXButton cancel3;

    @FXML
    private AnchorPane second;

    @FXML
    private JFXButton tothird;

    @FXML
    private JFXButton tofirst;

    @FXML
    private JFXButton cancel2;

    @FXML
    private AnchorPane first;

    @FXML
    private JFXButton cancel1;

    public void initialize() throws SQLException {
        setfaculty();
        setStream();
    }

    @FXML
    void changepane(ActionEvent event) {
        if(event.getSource()==tofirst){
            first.toFront();
        }
        else if(event.getSource()==tosecond){
            second.toFront();
        }
        else if(event.getSource()==tothird){
            third.toFront();
        }
        else if(event.getSource()==tosecond1){
            second.toFront();
        }
    }

    @FXML
    void closewindow(ActionEvent event) {
        Stage thisstage= (Stage) save.getScene().getWindow();
        thisstage.close();
    }

    @FXML
    void save(ActionEvent event) throws SQLException {
        int intakenum=Integer.parseInt(intake.getText());
        int ranknum=Integer.parseInt(rank.getText());
//        String streamid=stream.getSelectionModel().getSelectedItem().toString();
//        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        undergraduate undergrad=new undergraduate(
                regnumber.getText(),
                firstname.getText(),
                lastname.getText(),
                "M",
                "UCSC",
                nic.getText(),
                email.getText(),
                dob.getValue().toString(),
                address.getText(),
                mobile.getText(),
                regdate.getValue().toString(),
                intakenum,studentid.getText(),
                "Maths",
                "cs",
                ranknum
        );
        undergrad.save();
    }

    private void setfaculty() throws SQLException {
        ArrayList<String> all= nsbm.models.faculty.getall();
        for(String code:all){
            faculty.getItems().add(code);
        }
    }

    public void setCourses(ActionEvent inputMethodEvent) throws SQLException {
        course.getItems().clear();
        String fac=faculty.getSelectionModel().getSelectedItem().toString();
        ArrayList<String> all=nsbm.models.course.getall(fac);
        for(String code:all){
            course.getItems().add(code);
        }
    }
    private void setStream(){
        stream.getItems().addAll(
          "Science",
          "Commerce",
          "Art",
          "Technology"
        );
    }
    @FXML
    private void setSubjects(){
        subject1.getItems().clear();
        subject2.getItems().clear();
        subject3.getItems().clear();

        String[] science={"Combined Maths", "Biology", "Physics", "Chemistry","ICT","Agriculture"};
        String[] arts={"ICT", "Geography", "Law","Agriculture"};
        String[] tech={"Science for Technology","Agriculture","Engineering Science","Biology for Technology"};
        String[] commerce={"Buisness Studies","Accounting","Managment","ICT"};

        String st=stream.getSelectionModel().getSelectedItem().toString();
        switch (st) {
            case "Science":
                for (String subject : science) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Commerce":
                for (String subject : commerce) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Art":
                for (String subject : arts) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
            case "Technology":
                for (String subject : tech) {
                    subject1.getItems().add(subject);
                    subject2.getItems().add(subject);
                    subject3.getItems().add(subject);
                }
                break;
        }
    }

    public void removeSub(ActionEvent actionEvent) {
        if(actionEvent.getSource()==subject1){
            int removesub=subject1.getSelectionModel().getSelectedIndex();
            subject2.getItems().remove(removesub);
            subject3.getItems().remove(removesub);
        }else if(actionEvent.getSource()==subject2){
            int removesub=subject2.getSelectionModel().getSelectedIndex();
            subject1.getItems().remove(removesub);
            subject3.getItems().remove(removesub);
        }else if(actionEvent.getSource()==subject3){
            int removesub=subject3.getSelectionModel().getSelectedIndex();
            subject1.getItems().remove(removesub);
            subject2.getItems().remove(removesub);
        }
    }
}
