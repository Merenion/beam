package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beamElements.*;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoNepodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoPodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportZadelka;
import com.reactionsSupport.beam.beams.IBeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class BasicDefaultFactory  implements AllElementsFactory{

    @Autowired
    private BeamFactory beamDefaultFactory;
    @Autowired
    private SupportFactory supportDefaultFactory;
    @Autowired
    private EffectFactory effectDefaultFactory;

    @Override
    public IBeam createBeam() {
        return beamDefaultFactory.createBeam();
    }

    @Override
    public Moment createMoment() {
        return effectDefaultFactory.createMoment();
    }

    @Override
    public VerticalForce createVerticalForce() {
        return effectDefaultFactory.createVerticalForce();
    }

    @Override
    public DistributedLoad createDistributedLoad() {
        return effectDefaultFactory.createDistributedLoad();
    }

    @Override
    public DistributedLoadIncreasing createDistributedLoadIncreasing() {
        return effectDefaultFactory.createDistributedLoadIncreasing();
    }

    @Override
    public SupportSharnirnoPodviznaya createSupportSharnirnoPodviznaya() {
        return supportDefaultFactory.createSupportSharnirnoPodviznaya();
    }

    @Override
    public SupportSharnirnoNepodviznaya createSupportSharnirnoNepodviznaya() {
        return supportDefaultFactory.createSupportSharnirnoNepodviznaya();
    }

    @Override
    public SupportZadelka createSupportZadelka() {
        return supportDefaultFactory.createSupportZadelka();
    }

    @Override
    public HorizontalForce createHorizonForce() {
        return effectDefaultFactory.createHorizonForce();
    }
}
