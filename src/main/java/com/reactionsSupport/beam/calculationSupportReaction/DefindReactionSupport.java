package com.reactionsSupport.beam.calculationSupportReaction;

import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;

import java.util.List;

public interface DefindReactionSupport {
    List<Support> defindReactionSupport (IBeam beam);
    int getExactness();
    void setExactness(int exactness);
}
