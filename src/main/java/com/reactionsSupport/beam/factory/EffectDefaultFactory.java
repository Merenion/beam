package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beamElements.*;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public abstract class EffectDefaultFactory implements EffectFactory {

    @Lookup
    @Override
    public abstract Moment createMoment();

    @Lookup
    @Override
    public abstract VerticalForce createVerticalForce();

    @Lookup
    @Override
    public abstract HorizontalForce createHorizonForce();

    @Lookup
    @Override
    public abstract DistributedLoad createDistributedLoad();

    @Lookup
    @Override
    public abstract DistributedLoadIncreasing createDistributedLoadIncreasing();
}
