package ru.vl.beam.mainFormaWindow.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import ru.vl.beam.ActionInProgram.Impl.ImplAddBeam;
import ru.vl.beam.ActionInProgram.Impl.ImplDiagrams;
import ru.vl.beam.ActionInProgram.Impl.ImplSupportReaction;
import ru.vl.beam.ActionInProgram.InterfaceCharacteristicsBeam;
import ru.vl.beam.ActionInProgram.InterfaceConstructionOfDiagrams;
import ru.vl.beam.ActionInProgram.InterfaceSupportReaction;
import ru.vl.beam.mainFormaWindow.ElementsPaint.ElementBeamShape;
import ru.vl.beam.mainFormaWindow.ElementsPaint.ElementPaint;
import ru.vl.beam.mainFormaWindow.ErrorWindow;

import java.awt.*;
import java.util.ArrayList;

public class ContrMain {
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    private static InterfaceCharacteristicsBeam beamCharacteristics = new ImplAddBeam();
    private static InterfaceSupportReaction interfaceSupportReaction = new ImplSupportReaction();
    private static InterfaceConstructionOfDiagrams interfaceDiagrams = new ImplDiagrams();
    private ElementBeamShape elementBeamShape;
    private static ObservableList<ElementPaint> listElementsBeam = FXCollections.observableArrayList() ;
    private float lengthBeamReal;



    @FXML
    public TableColumn columnObjectName;
    @FXML
    public TableColumn columnDistance;
    @FXML
    public AnchorPane formaBeam;
    @FXML
    public TextField textLength;
    @FXML
    public TextField textValumLoad;
    @FXML
    public TableView tableElement;
    @FXML
    public RadioButton radioMoment;
    @FXML
    public ToggleGroup radioForceAndMoment;
    @FXML
    public RadioButton radioForce;
    @FXML
    public RadioButton radioVothrastNo;
    @FXML
    public ToggleGroup radioLoadVothrast;
    @FXML
    public RadioButton radioVothrast;
    @FXML
    public TextField textTo;
    @FXML
    public TextField textLoadFrom;
    @FXML
    public RadioButton radioZadelka;
    @FXML
    public RadioButton radioNepodvithnaya;
    @FXML
    public ToggleGroup radioTypeSupport;
    @FXML
    public RadioButton radioSharnir;
    @FXML
    public TextField textSupport;
    @FXML
    public TextField textValumForceMoment;
    @FXML
    public TextField textDistForceMoment;
    @FXML
    public Line mainLineBeam;
    @FXML
    public AnchorPane forma;
    @FXML
    public AnchorPane pane;



    @FXML
    public void initialize (){
        beamCharacteristics.createBeam();
    elementBeamShape = new ElementBeamShape(mainLineBeam,10);
        columnObjectName.setCellValueFactory(new PropertyValueFactory<ElementPaint,String>("name"));
        columnDistance.setCellValueFactory(new PropertyValueFactory<ElementPaint,Double>("distance"));

        listElementsBeam.addListener(new ListChangeListener<ElementPaint>() {
            @Override
            public void onChanged(Change<? extends ElementPaint> c) {
                refreshImageBeam();
            }
        });
        tableElement.setItems(listElementsBeam);
        listElementsBeam.add(elementBeamShape.showLengthBeam());
        beamCharacteristics.setLength(10);
        lengthBeamReal = 10;
        refreshImageBeam();
    }


    public void onAddMomentForce(ActionEvent actionEvent) {
        if (Float.parseFloat(textDistForceMoment.getText())<0 || Float.parseFloat(textDistForceMoment.getText())>lengthBeamReal) {
            new ErrorWindow("Задайте правильной расстояние!");
            return;
        }

        if (radioForceAndMoment.getSelectedToggle()==radioForce){
            beamCharacteristics.addForce(Float.parseFloat(textValumForceMoment.getText()),
                    Float.parseFloat(textDistForceMoment.getText()));
            listElementsBeam.add (elementBeamShape.showForce(Float.parseFloat(textDistForceMoment.getText()),
                    Float.parseFloat(textValumForceMoment.getText())));
        }else {
            beamCharacteristics.addMoment(Float.parseFloat(textValumForceMoment.getText()),
                    Float.parseFloat(textDistForceMoment.getText()));
            listElementsBeam.add(elementBeamShape.showMoment(Float.parseFloat(textDistForceMoment.getText()),
                    Float.parseFloat(textValumForceMoment.getText())));
        }
    refreshImageBeam();
    }

    public void onAddSupport(ActionEvent actionEvent) {
        if (Float.parseFloat(textSupport.getText())<0 || Float.parseFloat(textSupport.getText())>lengthBeamReal) {
            new ErrorWindow("Задайте правильной расстояние!");
            return;
        }

        if (radioTypeSupport.getSelectedToggle()==radioNepodvithnaya) {
            beamCharacteristics.addSupport(1, Float.parseFloat(textSupport.getText()));
            listElementsBeam.add(elementBeamShape.supportNePodvithnaya(Double.parseDouble(textSupport.getText())));
        } if (radioTypeSupport.getSelectedToggle()==radioSharnir) {
            beamCharacteristics.addSupport(2, Float.parseFloat(textSupport.getText()));
            listElementsBeam.add(elementBeamShape.supportPodvithnaya(Double.parseDouble(textSupport.getText())));
        } if (radioTypeSupport.getSelectedToggle()==radioZadelka){
            beamCharacteristics.addSupport(3, Float.parseFloat(textSupport.getText()));
            if ( Float.parseFloat(textSupport.getText())==0) {
                listElementsBeam.add(elementBeamShape.supportZeskaiaThadelka(true));
            } if (Float.parseFloat(textSupport.getText())==lengthBeamReal){
                listElementsBeam.add(elementBeamShape.supportZeskaiaThadelka(false));
            }
            if (Float.parseFloat(textSupport.getText())!=0&&Float.parseFloat(textSupport.getText())!=lengthBeamReal){
                new ErrorWindow("Ошибка! Заделка может быть только на концах.");
            }
            }
            refreshImageBeam();
    }

    public void onAddLoad(ActionEvent actionEvent) {
        if (Float.parseFloat(textLoadFrom.getText())<0 || Float.parseFloat(textLoadFrom.getText())>lengthBeamReal ||
                Float.parseFloat(textTo.getText())<0 || Float.parseFloat(textTo.getText())>lengthBeamReal) {
            new ErrorWindow("Задайте правильной расстояние!");
            return;
        }

    if (radioLoadVothrast.getSelectedToggle()==radioVothrast) {
        if (Float.parseFloat(textLoadFrom.getText())<Float.parseFloat(textTo.getText())) {
            beamCharacteristics.addDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),
                    Float.parseFloat(textValumLoad.getText()),
                    true,true);
            listElementsBeam.add(elementBeamShape.showDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),Float.parseFloat(textValumLoad.getText()),true));
        } if (Float.parseFloat(textLoadFrom.getText())>Float.parseFloat(textTo.getText())) {
            beamCharacteristics.addDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),
                    Float.parseFloat(textValumLoad.getText()),
                    true,false);
            listElementsBeam.add(elementBeamShape.showDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),Float.parseFloat(textValumLoad.getText()),true));
        }if (Float.parseFloat(textLoadFrom.getText())==Float.parseFloat(textTo.getText())) {
            new ErrorWindow("Задайте правильной расстояние!");
        }
    } if (radioLoadVothrast.getSelectedToggle()==radioVothrastNo){
        if (Float.parseFloat(textLoadFrom.getText())!=Float.parseFloat(textTo.getText())){
            beamCharacteristics.addDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),
                    Float.parseFloat(textValumLoad.getText()),
                    false,false);
            listElementsBeam.add(elementBeamShape.showDistributedLoad(Float.parseFloat(textLoadFrom.getText()),
                    Float.parseFloat(textTo.getText()),Float.parseFloat(textValumLoad.getText()),false));
        } else {
            new ErrorWindow("Задайте правильной расстояние!");
        }
    }
        refreshImageBeam();
    }

    public void onDelete(ActionEvent actionEvent) {
    }

    public void onStart(ActionEvent actionEvent) {
    }





    public void refreshImageBeam () {
    formaBeam.getChildren().clear();
    formaBeam.getChildren().add(mainLineBeam);
    for (ElementPaint elementPaint:listElementsBeam) {
        formaBeam.getChildren().add(elementPaint.getPath());
        if (elementPaint.getText() == null) {} else {
            formaBeam.getChildren().add(elementPaint.getText());
        }
    }
    }


    public void onKeyText(KeyEvent keyEvent) {
        if (textLength.getText().equals("")){}else {
            ElementPaint elementPaint2 = null;
            for (ElementPaint elementPaint : listElementsBeam) {
                if (elementPaint.getType() == ElementBeamShape.Type.LENGTHBEAM) {
                    elementPaint2 = elementPaint;
                }
            }
            listElementsBeam.remove(elementPaint2);
            beamCharacteristics.setLength(Float.parseFloat(textLength.getText()));
            elementBeamShape.setLengthBeamReal(Double.parseDouble(textLength.getText()));
            listElementsBeam.add(elementBeamShape.showLengthBeam());
            lengthBeamReal = Float.parseFloat(textLength.getText());
            refreshImageBeam();
        }
    }
}
/**
    MoveTo moveTo= new MoveTo(0,0);
    LineTo lineTo = new LineTo(50,50);
    LineTo lineTo1 = new LineTo(100,50);
    MoveTo moveTo1 = new MoveTo(120, 50);
    LineTo lineTo2 = new LineTo(140,50);
    Path path = new Path();
        path.getElements().addAll(moveTo,lineTo,lineTo1,moveTo1,lineTo2);
                Path path1 = new Path();
                MoveTo moveTo3 = new MoveTo(150, 50);
                LineTo lineTo3 = new LineTo(180,50);
                path.getElements().addAll(moveTo3,lineTo3);
                forma.getChildren().addAll(path,path1);
                */
