package com.reactionsSupport.beam.beamElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class Moment extends EffectMoment {
    // момент

    @Override
    public String toString() {
        return "Moment{" +
                "amountResult=" + amountResult +
                ", distanceToCenter=" + distanceToCenter +
                '}';
    }
}

