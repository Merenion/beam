package com.reactionsSupport.beam.beamElements.typeSupport;

import com.reactionsSupport.beam.annotations.Limitation;
import com.reactionsSupport.beam.beamElements.Effect;

import java.util.Collection;
import java.util.List;

public abstract class SupportAbstr implements Support {

    private float distance;

    @Override
    public abstract List<Effect> getReaction();

    @Override
    public float getDistance() {
        return distance;
    }

    @Limitation
    @Override
    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+" - Distance ["+ distance + "] [" +
                "reaction=" + getReaction() +
                ']';
    }
}
