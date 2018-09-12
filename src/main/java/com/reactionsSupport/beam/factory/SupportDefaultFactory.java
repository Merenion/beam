package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoNepodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoPodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportZadelka;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public abstract class SupportDefaultFactory implements SupportFactory{

    @Lookup
    @Override
    public abstract SupportSharnirnoPodviznaya createSupportSharnirnoPodviznaya();

    @Lookup
    @Override
    public abstract SupportSharnirnoNepodviznaya createSupportSharnirnoNepodviznaya();

    @Lookup
    @Override
    public abstract SupportZadelka createSupportZadelka();

}
