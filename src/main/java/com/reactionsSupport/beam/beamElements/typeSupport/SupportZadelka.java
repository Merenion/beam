package com.reactionsSupport.beam.beamElements.typeSupport;

import com.reactionsSupport.beam.beamElements.Effect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SupportZadelka extends SupportAbstr {
    // класс заделка

    private List<Effect> reaction = new ArrayList<>(); // имеющиеся реакции опоры при этой опоре

    @Override
    public List<Effect> getReaction() {
        return reaction;
    }

    @Override
    public void setDistance(float distance) {
        super.setDistance(distance);
        for (Effect effect:reaction)
            effect.setDistanceToCenter(distance);
    }
}
