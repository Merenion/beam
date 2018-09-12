package ru.vl.beam.mainFormaWindow.ElementsPaint;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.text.Text;

import javax.naming.Name;

public class ElementPaint {
    static int i=0;
    {
        i++;
    }

    private SimpleStringProperty name =new SimpleStringProperty();
    private SimpleDoubleProperty distance = new SimpleDoubleProperty();
    private javafx.scene.shape.Path path = null;
    private Text text = null;
    private ElementBeamShape.Type type;

    @Override
    public String toString(){
        return name.toString();
    }

    ElementPaint(javafx.scene.shape.Path path, Text text, ElementBeamShape.Type type,double distance) {
        this.path = path;
        this.text = text;
        this.type = type;
        this.distance.set(distance);
        name.set(type.toString()+"-"+i);
    }
    ElementPaint(javafx.scene.shape.Path path, ElementBeamShape.Type type,double distance) {
        this.path = path;
        this.type = type;
        this.distance.set(distance);
        name.set(type.toString()+"-"+i);
    }

    public ElementBeamShape.Type getType() {
        return type;
    }

    public javafx.scene.shape.Path getPath() {
        return path;
    }

    public void setPath(javafx.scene.shape.Path path) {
        this.path = path;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }
}
