package com.reactionsSupport.beam.beamElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class VerticalForce extends EffectVerticalForce {
	// вертикальная сила

    @Override
    public String toString() {
        return "VerticalForce{" +
                "amountResult=" + amountResult +
                ", distanceToCenter=" + distanceToCenter +
                '}';
    }
}
