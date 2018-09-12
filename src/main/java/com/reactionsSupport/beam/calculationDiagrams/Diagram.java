package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;

import java.util.List;
import java.util.Set;

public interface Diagram {

        Set<ISection> calculationDiagram(IBeam beam, List<Support> defindSupport);
        int getExactness();
        void setExactness(int exactness);
}
