package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beams.IBeam;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Lazy
public abstract class BeamDefaultFactory implements BeamFactory{

    @Override
    @Lookup
    public abstract IBeam createBeam();
}
