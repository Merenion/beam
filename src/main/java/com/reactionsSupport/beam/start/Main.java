package com.reactionsSupport.beam.start;

import
        com.reactionsSupport.beam.beamElements.DistributedLoad;
import com.reactionsSupport.beam.beamElements.DistributedLoadIncreasing;
import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.*;
import com.reactionsSupport.beam.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan(basePackages = "com.reactionsSupport.beam")
public class Main {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("AnnotationsOn_Context.xml");;

    public static ApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) throws InterruptedException {


//        AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");
//        IBeam beam = createBeam(factory);
//        Thread.sleep(1000);
//        System.out.println(beam);
//        Thread.sleep(2000);
//        Sectionalizat sectionalization = (Sectionalizat) context.getBean("implSectionalization");
//        Set<ISection> sections = sectionalization.sectionalization(beam);
//        System.out.println(sections);
//        SeparatorDistributedLoad separatorDistributedLoad = (SeparatorDistributedLoad) context.getBean("implSeparatorDistributedLoad");
//        System.out.println("next separator!");
//        Thread.sleep(2000);
//        System.out.println(separatorDistributedLoad.separatorLoads(beam.getExternalInfluences().getEffects(),sections));
//        System.out.println(beam.getExternalInfluences().getEffects());
    }

    private static IBeam createBeam(AllElementsFactory factory) {
        IBeam beam = factory.createBeam();
        beam.getGeometricCharacteristics().setLength(30);
        Effect effect1 = factory.createMoment();
        effect1.setAmountResult(43);
        effect1.setDistanceToCenter(4);
        Effect effect2 = factory.createVerticalForce();
        effect2.setDistanceToCenter(7);
        effect2.setAmountResult(-10);
        Effect effect3 = factory.createDistributedLoad();
        ((DistributedLoad) effect3).setTo(22f);
        ((DistributedLoad) effect3).setFrom(10f);
        effect3.setAmountResult(-20);
        Effect effect4 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect4).setFrom(21f);
        ((DistributedLoadIncreasing) effect4).setTo(25f);
        effect4.setAmountResult(50);
        Support support1 = factory.createSupportSharnirnoNepodviznaya();
        support1.setDistance(2);
        Support support2 = factory.createSupportZadelka();
        support2.setDistance(30);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);
        beam.getExternalInfluences().getEffects().add(effect4);

        return beam;
    }
}


