package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beamElements.*;

public interface EffectFactory {
    Moment createMoment();

    VerticalForce createVerticalForce();

    HorizontalForce createHorizonForce();

    DistributedLoad createDistributedLoad();

    DistributedLoadIncreasing createDistributedLoadIncreasing();
}
