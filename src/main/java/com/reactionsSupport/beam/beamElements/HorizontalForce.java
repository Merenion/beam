package com.reactionsSupport.beam.beamElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class HorizontalForce extends EffectHorizontalForce{
    //горизонтальная сила

    @Override
    public String toString() {
        return "HorizontalForce{" +
                "amountResult=" + amountResult +
                ", distanceToCenter=" + distanceToCenter +
                '}';
    }
}
