package com.reactionsSupport.beam.factory;

import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoNepodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoPodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportZadelka;

public interface SupportFactory {
    SupportSharnirnoPodviznaya createSupportSharnirnoPodviznaya();
    SupportSharnirnoNepodviznaya createSupportSharnirnoNepodviznaya();
    SupportZadelka createSupportZadelka ();
}
