package com.reactionsSupport.beam.calculationDiagrams;

public interface ISection {
    float getDistance ();
    float getValueForceVerticalDiagramLeft();
    void setValueForceVerticalDiagramLeft(float valueForceVerticalDiagramLeft);
    float getValueForceVerticalDiagramRight();
    void setValueForceVerticalDiagramRight(float valueForceVerticalDiagramRight);
    float getValueMomentDiagramLeft();
    void setValueMomentDiagramLeft(float valueMomentDiagramLeft);
    float getValueMomentDiagramRight();
    void setValueMomentDiagramRight(float valueMomentDiagramRight);
    float getValueForceHorizontalDiagramLeft();
    void setValueForceHorizontalDiagramLeft(float valueForceHorizontalDiagramLeft);
    float getValueForceHorizontalDiagramRight();
    void setValueForceHorizontalDiagramRight(float valueForceHorizontalDiagramRight);
}
