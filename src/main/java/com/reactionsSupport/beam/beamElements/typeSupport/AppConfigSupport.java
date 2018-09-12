package com.reactionsSupport.beam.beamElements.typeSupport;

import com.reactionsSupport.beam.factory.EffectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
@Scope("prototype")
@Lazy
public class AppConfigSupport {

    @Autowired
    private EffectFactory effectDefaultFactory;


    @Bean
    public SupportSharnirnoPodviznaya supportSP() {
        SupportSharnirnoPodviznaya support = new SupportSharnirnoPodviznaya();
        support.getReaction().add(effectDefaultFactory.createVerticalForce());
        return support;
    }

    @Bean
    public SupportSharnirnoNepodviznaya supportSN() {
        SupportSharnirnoNepodviznaya support = new SupportSharnirnoNepodviznaya();
        support.getReaction().add(effectDefaultFactory.createVerticalForce());
        support.getReaction().add(effectDefaultFactory.createHorizonForce());
        return support;
    }

    @Bean
    public SupportZadelka supportZ() {
        SupportZadelka support = new SupportZadelka();
        support.getReaction().add(effectDefaultFactory.createVerticalForce());
        support.getReaction().add(effectDefaultFactory.createMoment());
        support.getReaction().add(effectDefaultFactory.createHorizonForce());
        return support;
    }

}
