package com.reactionsSupport.beam;

import com.reactionsSupport.beam.beamElements.*;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.Sectionalizat;
import com.reactionsSupport.beam.calculationSupportReaction.DefindReactionSupport;
import com.reactionsSupport.beam.calculationSupportReaction.ImplDifindReactionSupport;
import com.reactionsSupport.beam.factory.AllElementsFactory;
import com.reactionsSupport.beam.factory.BasicDefaultFactory;
import com.reactionsSupport.beam.start.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DifindReactionTestFunctional {

    @Before
    public void setUp() throws Exception {
        System.out.println("START TEST");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("END TEST");
    }

    @Test
    public void defindReactionSupport() {
        IBeam beam = Beam.getBeam1();
        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println( supports);

    }

    @Test
    public void defindReactionSupport2() {
        IBeam beam = Beam.getBeam2();
        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println( supports);
        assertEquals(1, supports.size());
        float amountMoment = supports.get(0).getReaction().stream().filter(x -> x instanceof EffectMoment).findAny().get().getAmountResult();
        float amountForce = supports.get(0).getReaction().stream().filter(x -> x instanceof EffectVerticalForce).findAny().get().getAmountResult();
        assertTrue(275 > amountMoment && amountMoment > 265);
        assertTrue(95 > amountForce && amountForce > 85);
        assertTrue(beam.getSupports().getSupports().get(0).getReaction().get(0).getAmountResult() != 0);
    }

    @Test
    public void defindReactionSupport3() {
        IBeam beam = Beam.getBeam3();
        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println( supports);
        assertEquals(2, supports.size());
        float amountForce1 = supports.stream().filter(x-> x.getDistance() ==2).findAny().get().getReaction().stream().filter(x-> x instanceof EffectVerticalForce).findAny().get().getAmountResult();
        float amountForce2 = supports.stream().filter(x-> x.getDistance() ==10).findAny().get().getReaction().stream().filter(x-> x instanceof EffectVerticalForce).findAny().get().getAmountResult();
        assertTrue(95 > amountForce1 && amountForce1 > 85);
        assertTrue(-45 > amountForce2 && amountForce2 > -55);
        assertTrue(beam.getSupports().getSupports().get(0).getReaction().get(0).getAmountResult() != 0);
    }

    @Test
    public void defindReactionSupport4() {
        IBeam beam = Beam.getBeam4();
        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println( supports);
    }

    @Test
    public void defindReactionSupport5() {
        ApplicationContext context = Main.getContext();
        AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");

        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(25);

        Support support1 = factory.createSupportSharnirnoNepodviznaya();
        support1.setDistance(10);
        Support support2 = factory.createSupportSharnirnoPodviznaya();
        support2.setDistance(25);

        Effect effect1 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect1).setAmountLoadDirect(70);
        ((DistributedLoadIncreasing) effect1).setFrom(3f);
        ((DistributedLoadIncreasing) effect1).setTo(7f);
        ((DistributedLoadIncreasing) effect1).setIncreasingLeft(true);

        Effect effect2 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect2).setAmountLoadDirect(-80);
        ((DistributedLoadIncreasing) effect2).setFrom(12f);
        ((DistributedLoadIncreasing) effect2).setTo(17f);
        ((DistributedLoadIncreasing) effect2).setIncreasingLeft(true);

        Effect effect3 = factory.createDistributedLoadIncreasing();
        ((DistributedLoadIncreasing) effect3).setAmountLoadDirect(43);
        ((DistributedLoadIncreasing) effect3).setFrom(15f);
        ((DistributedLoadIncreasing) effect3).setTo(20f);
        ((DistributedLoadIncreasing) effect3).setIncreasingLeft(false);

        Effect effect4 = factory.createVerticalForce();
        effect4.setAmountResult(-57);
        effect4.setDistanceToCenter(0);

        Effect effect5 = factory.createVerticalForce();
        effect5.setAmountResult(-5);
        effect5.setDistanceToCenter(5);

        Effect effect6 = factory.createVerticalForce();
        effect6.setAmountResult(7);
        effect6.setDistanceToCenter(23);

        Effect effect7 = factory.createMoment();
        effect7.setAmountResult(20);
        effect7.setDistanceToCenter(2);

        Effect effect8 = factory.createMoment();
        effect8.setAmountResult(22);
        effect8.setDistanceToCenter(21);

        Effect effect9 = factory.createMoment();
        effect9.setAmountResult(-43);
        effect9.setDistanceToCenter(24);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);
        beam.getExternalInfluences().getEffects().add(effect4);
        beam.getExternalInfluences().getEffects().add(effect5);
        beam.getExternalInfluences().getEffects().add(effect6);
        beam.getExternalInfluences().getEffects().add(effect7);
        beam.getExternalInfluences().getEffects().add(effect8);
        beam.getExternalInfluences().getEffects().add(effect9);

        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println( supports);
        float amountForce1 = supports.stream().filter(x-> x.getDistance() ==10).findAny().get().getReaction().stream().filter(x-> x instanceof EffectVerticalForce).findAny().get().getAmountResult();
        float amountForce2 = supports.stream().filter(x-> x.getDistance() ==25).findAny().get().getReaction().stream().filter(x-> x instanceof EffectVerticalForce).findAny().get().getAmountResult();
        assertTrue(-8.61 > amountForce1 && amountForce1 > -12.61);
        assertTrue(19 > amountForce2 && amountForce2 > 17);
        assertTrue(beam.getSupports().getSupports().get(0).getReaction().get(0).getAmountResult() != 0);
    }
}