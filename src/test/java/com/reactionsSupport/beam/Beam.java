package com.reactionsSupport.beam;

import com.reactionsSupport.beam.beamElements.DistributedLoad;
import com.reactionsSupport.beam.beamElements.DistributedLoadIncreasing;
import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.EffectDistributed;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.factory.AllElementsFactory;
import com.reactionsSupport.beam.factory.BasicDefaultFactory;
import com.reactionsSupport.beam.start.Main;
import org.springframework.context.ApplicationContext;

public class Beam {
    private static Beam ourInstance = new Beam();
    private static IBeam beam1;
    private static IBeam beam2;
    private static IBeam beam3;
    private static IBeam beam4;
    private static IBeam beam5;

    ApplicationContext context = Main.getContext();
    AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");

    public static Beam getInstance() {
        return ourInstance;
    }

    public static IBeam getBeam1() {
        return beam1;
    }

    public static IBeam getBeam2() {
        return beam2;
    }

    public static IBeam getBeam3() {
        return beam3;
    }

    public static IBeam getBeam4() {
        return beam4;
    }

    public static IBeam getBeam5() {
        return beam5;
    }

    public Beam() {
        createBeam1();
        createBeam4();
        createBeam2();
        createBeam3();
    }

    private  void createBeam1 () {
        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(30);

        Effect effect1 = factory.createMoment();
        effect1.setAmountResult(43);
        effect1.setDistanceToCenter(4);

        Effect effect2 = factory.createVerticalForce();
        effect2.setDistanceToCenter(7);
        effect2.setAmountResult(-10);

        Effect effect3 = factory.createDistributedLoad();
        ((EffectDistributed) effect3).setTo(22f);
        ((EffectDistributed) effect3).setFrom(10f);
        ((EffectDistributed) effect3).setAmountLoadDirect(-20);

        Effect effect4 = factory.createDistributedLoadIncreasing();
        ((EffectDistributed) effect4).setFrom(21f);
        ((EffectDistributed) effect4).setTo(25f);
        ((EffectDistributed) effect4).setAmountLoadDirect(50);


        Support support1 = factory.createSupportSharnirnoNepodviznaya();
        support1.setDistance(2);
        Support support2 = factory.createSupportSharnirnoPodviznaya();
        support2.setDistance(30);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);
        beam.getExternalInfluences().getEffects().add(effect4);

        beam1 = beam;
    }

    private void createBeam2 (){
        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(6);

        Support support = factory.createSupportZadelka();
        support.setDistance(0);

        Effect effect1 = factory.createDistributedLoad();
        ((DistributedLoad) effect1).setAmountLoadDirect(-10);
        ((DistributedLoad) effect1).setFrom(0f);
        ((DistributedLoad) effect1).setTo(6f);

        Effect effect2 = factory.createVerticalForce();
        effect2.setAmountResult(-30);
        effect2.setDistanceToCenter(3);

        beam.getSupports().getSupports().add(support);
        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam2 = beam;
    }

    private void createBeam3 () {
        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(10);

        Support support1 = factory.createSupportSharnirnoNepodviznaya();
        support1.setDistance(2);

        Support support2 = factory.createSupportSharnirnoPodviznaya();
        support2.setDistance(10);

        Effect effect1 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect1).setAmountLoadDirect(-30);
        ((DistributedLoadIncreasing) effect1).setFrom(2f);
        ((DistributedLoadIncreasing) effect1).setTo(6f);
        ((DistributedLoadIncreasing) effect1).setIncreasingLeft(false);

        Effect effect2 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect2).setAmountLoadDirect(30);
        ((DistributedLoadIncreasing) effect2).setFrom(6f);
        ((DistributedLoadIncreasing) effect2).setTo(10f);
        ((DistributedLoadIncreasing) effect2).setIncreasingLeft(true);

        Effect effect3 = factory.createVerticalForce();
        effect3.setAmountResult(-40);
        effect3.setDistanceToCenter(0);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam3 = beam;
    }

    private void createBeam4 () {
        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(40);

        Support support1 = factory.createSupportSharnirnoPodviznaya();
        support1.setDistance(1);
        Support support2 = factory.createSupportSharnirnoNepodviznaya();
        support2.setDistance(26);

        Effect effect1 = factory.createVerticalForce();
        effect1.setAmountResult(-15);
        effect1.setDistanceToCenter(10);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam.getExternalInfluences().getEffects().add(effect1);

        beam4 = beam;
    }

}
