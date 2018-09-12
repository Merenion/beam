package ru.vl.beam.mainFormaWindow.ElementsPaint;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;

public class ElementBeamShape {
    public enum Type {LENGTHBEAM, LOAD, SUPPORT, RATHMER}

    private Line mainLineBeam;
    private double lineFromX ;
    private double lineFromY ;
    private double lineToX ;
    private double lineToY ;
    private double lengthBeamReal;
    private double coefficient;



    public void setLengthBeamReal(double lengthBeamReal) {
        this.lengthBeamReal = lengthBeamReal;
        coefficient = (mainLineBeam.getEndX()-mainLineBeam.getStartX())/lengthBeamReal;
    }


    public ElementBeamShape(Line mainLineBeam, double length) {
        this.mainLineBeam = mainLineBeam;
        lineFromX = mainLineBeam.getStartX();
        lineFromY = mainLineBeam.getStartY();
        lineToX = mainLineBeam.getEndX();
        lineToY = mainLineBeam.getEndY();
        lengthBeamReal = length;
        coefficient = (mainLineBeam.getEndX()-mainLineBeam.getStartX())/length;
    }

    public ElementPaint supportZeskaiaThadelka (boolean left){
        Path path = new Path();
        MoveTo moveTo;
        LineTo lineTo;
        if (left) {
            moveTo = new MoveTo(lineFromX, lineFromY + 20);
            lineTo = new LineTo(lineFromX, lineFromY - 20);
        }else {
            moveTo = new MoveTo(lineToX, lineFromY + 20);
            lineTo = new LineTo(lineToX, lineFromY - 20);
        }
        for (int i=0; i<10; i++){
            if (left) {
                MoveTo moveTo1 = new MoveTo(lineFromX, lineFromY + 20 - 4 * i);
                LineTo lineTo1 = new LineTo(lineFromX - 4, lineFromY + 20 - 4 - 4 * i);
                path.getElements().addAll(moveTo1, lineTo1);
            }else{
                MoveTo moveTo1 = new MoveTo(lineToX, lineFromY + 20 - 4 * i);
                LineTo lineTo1 = new LineTo(lineToX + 4, lineFromY + 20 - 4 - 4 * i);
                path.getElements().addAll(moveTo1, lineTo1);
            }
        }
        path.getElements().addAll(moveTo,lineTo);
        path.setStrokeWidth(2);
        ElementPaint elementPaint = new ElementPaint(path,Type.SUPPORT,0);
        return elementPaint;
    }

    public ElementPaint supportNePodvithnaya (double distance){
        Path path = new Path();
        MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient,lineFromY);
        LineTo lineTo = new LineTo(lineFromX+distance*coefficient+10,lineFromY+15);
        LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient-10,lineFromY+15);
        LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient,lineFromY);
        path.getElements().addAll(moveTo,lineTo,lineTo1,lineTo2);
        path.setFill(Color.LIGHTGRAY) ;
        ElementPaint elementPaint = new ElementPaint(path,Type.SUPPORT,distance);
        return elementPaint;
    }

    public ElementPaint supportPodvithnaya (double distance){
    Path path = new Path();
    MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient,lineFromY);
    LineTo lineTo = new LineTo(lineFromX+distance*coefficient+10,lineFromY+15);
    LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient-10,lineFromY+15);
    LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient,lineFromY);
    MoveTo moveTo1 = new MoveTo(lineFromX+distance*coefficient+10,lineFromY+20);
    LineTo lineTo3 = new LineTo(lineFromX+distance*coefficient-10,lineFromY+20);
    path.getElements().addAll(moveTo,lineTo,lineTo1,lineTo2,moveTo1,lineTo3);
    path.setFill(Color.LIGHTGRAY) ;
    ElementPaint elementPaint = new ElementPaint(path,Type.SUPPORT,distance);
    return elementPaint;
    }

    public ElementPaint showLengthBeam(){
    MoveTo moveTo = new MoveTo(lineFromX,lineFromY);
    LineTo lineTo = new LineTo(lineFromX,lineFromY+35);
    LineTo lineTo3 = new LineTo(lineToX,lineToY+35);
    LineTo lineTo4 = new LineTo(lineToX,lineToY);
    MoveTo moveTo1 = new MoveTo(lineFromX,lineFromY+35);
    LineTo lineTo1 = new LineTo(lineFromX+5,lineFromY+31);
    MoveTo moveTo2 = new MoveTo(lineFromX,lineFromY+35);
    LineTo lineTo2 = new LineTo(lineFromX+5,lineFromY+39);
        MoveTo moveTo5 = new MoveTo(lineToX,lineFromY+35);
        LineTo lineTo5 = new LineTo(lineToX-5,lineFromY+31);
        MoveTo moveTo6 = new MoveTo(lineToX,lineFromY+35);
        LineTo lineTo6 = new LineTo(lineToX-5,lineFromY+39);
        Path path = new Path();
        path.getElements().addAll(moveTo,lineTo,lineTo3,lineTo4,moveTo1,lineTo1,
                moveTo2,lineTo2,moveTo5,lineTo5,moveTo6, lineTo6);
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        text.setText(String.valueOf(lengthBeamReal));
        text.setLayoutX(lineFromX+lengthBeamReal*coefficient/2);
        text.setLayoutY(lineFromY+32);
        return new ElementPaint(path,text, Type.LENGTHBEAM,0);
    }

    public ElementPaint showDistanceElement(int nomer, double distance){
        MoveTo moveTo = new MoveTo(lineFromX,lineFromY);
        LineTo lineTo = new LineTo(lineFromX,lineFromY-15-16*nomer);
        LineTo lineTo3 = new LineTo(lineFromX+distance*coefficient,lineToY-15-16*nomer);
        LineTo lineTo4 = new LineTo(lineFromX+distance*coefficient,lineToY);
        MoveTo moveTo1 = new MoveTo(lineFromX,lineFromY-15-16*nomer);
        LineTo lineTo1 = new LineTo(lineFromX+5,lineFromY-11-16*nomer);
        MoveTo moveTo2 = new MoveTo(lineFromX,lineFromY-15-16*nomer);
        LineTo lineTo2 = new LineTo(lineFromX+5,lineFromY-19-16*nomer);
        MoveTo moveTo5 = new MoveTo(lineFromX+distance*coefficient,lineFromY-15-16*nomer);
        LineTo lineTo5 = new LineTo(lineFromX+distance*coefficient-5,lineFromY-11-16*nomer);
        MoveTo moveTo6 = new MoveTo(lineFromX+distance*coefficient,lineFromY-15-16*nomer);
        LineTo lineTo6 = new LineTo(lineFromX+distance*coefficient-5,lineFromY-19-16*nomer);
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        text.setText(String.valueOf(distance));
        text.setLayoutX(lineFromX+distance*coefficient/2);
        text.setLayoutY(lineFromY-18-16*nomer);
        Path path = new Path();
        path.getElements().addAll(moveTo,lineTo,lineTo3,lineTo4,moveTo1,lineTo1,
                moveTo2,lineTo2,moveTo5,lineTo5,moveTo6, lineTo6);
        return new ElementPaint(path,text,Type.RATHMER,0);
    }

    public ElementPaint showForce (double distance, double value ) {
        Path path = new Path();
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        if (value>0){
            MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient,lineFromY+20);
            LineTo lineTo = new LineTo(lineFromX+distance*coefficient,lineFromY+2);
            LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient+4,lineFromY+6);
            MoveTo moveTo1 = new MoveTo(lineFromX+distance*coefficient,lineFromY+2);
            LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient-4,lineFromY+6);
            text.setLayoutX(lineFromX+distance*coefficient+5);
            text.setLayoutY(lineFromY+18);
            path.getElements().addAll(moveTo,lineTo,lineTo1,moveTo1,lineTo2);
            text.setText(String.valueOf(value));
        }else if (value<=0){
            MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient,lineFromY-20);
            LineTo lineTo = new LineTo(lineFromX+distance*coefficient,lineFromY-2);
            LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient+4,lineFromY-6);
            MoveTo moveTo1 = new MoveTo(lineFromX+distance*coefficient,lineFromY-2);
            LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient-4,lineFromY-6);
            text.setLayoutX(lineFromX+distance*coefficient+3);
            text.setLayoutY(lineFromY-13);
            text.setText(String.valueOf(-value));
            path.getElements().addAll(moveTo,lineTo,lineTo1,moveTo1,lineTo2);
        }
        path.setStrokeWidth(2);
        return new ElementPaint(path,text,Type.LOAD,distance);
    }

    public ElementPaint showMoment (double distance, double value){
        Path path = new Path();
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        if (value>0){
            MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient+10,lineFromY-20);
            LineTo lineTo = new LineTo(lineFromX+distance*coefficient,lineFromY-20);
            LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient,lineFromY+20);
            LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient-10,lineFromY+20);
            LineTo lineTo3 = new LineTo(lineFromX+distance*coefficient-6,lineFromY+16);
            MoveTo moveTo1 = new MoveTo(lineFromX+distance*coefficient-10,lineFromY+20);
            LineTo lineTo4 = new LineTo(lineFromX+distance*coefficient-6,lineFromY+24);
            MoveTo moveTo2 = new MoveTo(lineFromX+distance*coefficient+10,lineFromY-20);
            LineTo lineTo5 = new LineTo(lineFromX+distance*coefficient+6,lineFromY-24);
            MoveTo moveTo3 = new MoveTo(lineFromX+distance*coefficient+10,lineFromY-20);
            LineTo lineTo6 = new LineTo(lineFromX+distance*coefficient+6,lineFromY-16);
            text.setLayoutX(lineFromX+distance*coefficient+3);
            text.setLayoutY(lineFromY+18);
            text.setText(String.valueOf(value));
            path.getElements().addAll(moveTo,lineTo,lineTo1,lineTo2,lineTo3,moveTo1,lineTo4,moveTo2,lineTo5,
                    moveTo3,lineTo6);
        }else {
            MoveTo moveTo = new MoveTo(lineFromX+distance*coefficient-10,lineFromY-20);
            LineTo lineTo = new LineTo(lineFromX+distance*coefficient,lineFromY-20);
            LineTo lineTo1 = new LineTo(lineFromX+distance*coefficient,lineFromY+20);
            LineTo lineTo2 = new LineTo(lineFromX+distance*coefficient+10,lineFromY+20);
            LineTo lineTo3 = new LineTo(lineFromX+distance*coefficient+6,lineFromY+16);
            MoveTo moveTo1 = new MoveTo(lineFromX+distance*coefficient+10,lineFromY+20);
            LineTo lineTo4 = new LineTo(lineFromX+distance*coefficient+6,lineFromY+24);
            MoveTo moveTo2 = new MoveTo(lineFromX+distance*coefficient-10,lineFromY-20);
            LineTo lineTo5 = new LineTo(lineFromX+distance*coefficient-6,lineFromY-24);
            MoveTo moveTo3 = new MoveTo(lineFromX+distance*coefficient-10,lineFromY-20);
            LineTo lineTo6 = new LineTo(lineFromX+distance*coefficient-6,lineFromY-16);
            text.setLayoutX(lineFromX+distance*coefficient+3);
            text.setLayoutY(lineFromY-13);
            text.setText(String.valueOf(-value));
            path.getElements().addAll(moveTo,lineTo,lineTo1,lineTo2,lineTo3,moveTo1,lineTo4,moveTo2,lineTo5,
                    moveTo3,lineTo6);
        }
        path.setStrokeWidth(2);
        return new ElementPaint(path,text,Type.LOAD,distance);
    }

    public ElementPaint showDistributedLoad (double from, double to, double value, boolean vozrastaushaya){
        if (vozrastaushaya){
            return distributedLoadVozrastaushaya(from,to,value);
        } else {
            return distributedLoadNeVozrastaushaya(from,to,value);
        }
    }

    private ElementPaint distributedLoadNeVozrastaushaya (double from, double to, double value){
        Path path = new Path();
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        if (value > 0) {
            MoveTo moveTo = new MoveTo(lineFromX + from * coefficient, lineFromY+2);
            LineTo lineTo = new LineTo(lineFromX + from * coefficient, lineFromY + 19);
            LineTo lineTo1 = new LineTo(lineFromX + to * coefficient, lineFromY + 19);
            LineTo lineTo2 = new LineTo(lineFromX + to * coefficient, lineFromY+2);
            path.getElements().addAll(moveTo,lineTo,lineTo1,lineTo2);
            text.setLayoutX(lineFromX + to * coefficient + 3);
            text.setLayoutY(lineFromY + 18);
            text.setText(String.valueOf(value));
        } else {
            MoveTo moveTo = new MoveTo(lineFromX + from * coefficient, lineFromY - 2);
            LineTo lineTo = new LineTo(lineFromX + from * coefficient, lineFromY - 19);
            LineTo lineTo1 = new LineTo(lineFromX + to * coefficient, lineFromY - 19);
            LineTo lineTo2 = new LineTo(lineFromX + to * coefficient, lineFromY - 2);
            path.getElements().addAll(moveTo, lineTo, lineTo1,lineTo2);
            text.setLayoutX(lineFromX + to * coefficient + 3);
            text.setLayoutY(lineFromY - 10);
            text.setText(String.valueOf(value));
        }
        path.setStrokeWidth(2);
        return new ElementPaint(path,text,Type.LOAD,from);
        }

    private ElementPaint distributedLoadVozrastaushaya (double from, double to, double value) {
        Path path = new Path();
        javafx.scene.text.Text text = new javafx.scene.text.Text();
        if (value > 0) {
            MoveTo moveTo = new MoveTo(lineFromX + from * coefficient, lineFromY+2);
            LineTo lineTo = new LineTo(lineFromX + to * coefficient, lineFromY + 19);
            LineTo lineTo1 = new LineTo(lineFromX + to * coefficient, lineFromY+2);
            path.getElements().addAll(moveTo,lineTo,lineTo1);
            if (from < to) {
                text.setLayoutX(lineFromX + to * coefficient + 3);
                text.setLayoutY(lineFromY + 18);
                text.setText(String.valueOf(value));
            } else {
                text.setLayoutX(lineFromX + to * coefficient - 25);
                text.setLayoutY(lineFromY + 18);
                text.setText(String.valueOf(Math.rint(10.0 * value) / 10.0));
            }
        } else {
            MoveTo moveTo = new MoveTo(lineFromX + from * coefficient, lineFromY-2);
            LineTo lineTo = new LineTo(lineFromX + to * coefficient, lineFromY - 19);
            LineTo lineTo1 = new LineTo(lineFromX + to * coefficient, lineFromY-2);
            path.getElements().addAll(moveTo,lineTo,lineTo1);
            if (from < to) {
                text.setLayoutX(lineFromX + to * coefficient + 3);
                text.setLayoutY(lineFromY + -10);
                text.setText(String.valueOf(-value));
            } else {
                text.setLayoutX(lineFromX + to * coefficient - 25);
                text.setLayoutY(lineFromY - 10);
                text.setText(String.valueOf(-(Math.rint(10.0 * value)/10)));
            }
        }
        path.setStrokeWidth(2);
        return new ElementPaint(path,text,Type.LOAD,from);
    }
}
