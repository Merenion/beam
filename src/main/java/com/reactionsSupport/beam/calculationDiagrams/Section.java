package com.reactionsSupport.beam.calculationDiagrams;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("prototype")
public class Section implements ISection{

    private float distance;
    private float valueForceVerticalDiagramLeft;
    private float valueForceVerticalDiagramRight;
    private float valueMomentDiagramLeft;
    private float valueMomentDiagramRight;
    private float valueForceHorizontalDiagramLeft;
    private float valueForceHorizontalDiagramRight;

    public Section(float distance) {
        this.distance = distance;
    }

    public Section() {
    }

    @Override
    public float getDistance() {
        return distance;
    }

    @Override
    public float getValueForceVerticalDiagramLeft() {
        return valueForceVerticalDiagramLeft;
    }

    @Override
    public void setValueForceVerticalDiagramLeft(float valueForceVerticalDiagramLeft) {
        this.valueForceVerticalDiagramLeft = valueForceVerticalDiagramLeft;
    }

    @Override
    public float getValueForceVerticalDiagramRight() {
        return valueForceVerticalDiagramRight;
    }

    @Override
    public void setValueForceVerticalDiagramRight(float valueForceVerticalDiagramRight) {
        this.valueForceVerticalDiagramRight = valueForceVerticalDiagramRight;
    }

    @Override
    public float getValueMomentDiagramLeft() {
        return valueMomentDiagramLeft;
    }

    @Override
    public void setValueMomentDiagramLeft(float valueMomentDiagramLeft) {
        this.valueMomentDiagramLeft = valueMomentDiagramLeft;
    }

    @Override
    public float getValueMomentDiagramRight() {
        return valueMomentDiagramRight;
    }

    @Override
    public void setValueMomentDiagramRight(float valueMomentDiagramRight) {
        this.valueMomentDiagramRight = valueMomentDiagramRight;
    }

    @Override
    public float getValueForceHorizontalDiagramLeft() {
        return valueForceHorizontalDiagramLeft;
    }

    @Override
    public void setValueForceHorizontalDiagramLeft(float valueForceHorizontalDiagramLeft) {
        this.valueForceHorizontalDiagramLeft = valueForceHorizontalDiagramLeft;
    }

    @Override
    public float getValueForceHorizontalDiagramRight() {
        return valueForceHorizontalDiagramRight;
    }

    @Override
    public void setValueForceHorizontalDiagramRight(float valueForceHorizontalDiagramRight) {
        this.valueForceHorizontalDiagramRight = valueForceHorizontalDiagramRight;
    }

    @Override
    public String toString() {
        return "\nSection{" +
                "\ndistance=" + distance +
                "\n, valueForceVerticalDiagramLeft=" + valueForceVerticalDiagramLeft +
                "\n, valueForceVerticalDiagramRight=" + valueForceVerticalDiagramRight +
                "\n, valueMomentDiagramLeft=" + valueMomentDiagramLeft +
                "\n, valueMomentDiagramRight=" + valueMomentDiagramRight +
                "\n, valueForceHorizontalDiagramLeft=" + valueForceHorizontalDiagramLeft +
                "\n, valueForceHorizontalDiagramRight=" + valueForceHorizontalDiagramRight +
                "\n}";
    }
}
