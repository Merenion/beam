package com.reactionsSupport.beam;

import com.reactionsSupport.beam.beamElements.DistributedLoad;
import com.reactionsSupport.beam.beamElements.DistributedLoadIncreasing;
import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.Diagram;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ImplDiagramTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("START TEST");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("END TEST");
    }

    @Test
    public void calculationDiagramBeam2() {
        IBeam beam = Beam.getBeam2();
        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        Diagram diagram = (Diagram) Main.getContext().getBean("implDiagram");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println(supports);
        Set<ISection> sections = diagram.calculationDiagram(beam, supports);
        System.out.println(sections);
        ISection section1 = sections.stream().filter(x -> x.getDistance() == 0).findAny().get();
        ISection section2 = sections.stream().filter(x -> x.getDistance() == 3).findAny().get();
        ISection section3 = sections.stream().filter(x -> x.getDistance() == 6).findAny().get();
        assertTrue(checkConformity(0, 90, 0, -270, section1));
        assertTrue(checkConformity(60, 30, -45, -45, section2));
        assertTrue(checkConformity(0, 0, 0, 0, section3));
    }

    /**
     * на вход занчения которые должны получаться, на выходе соответствие этим значениям
     */
    private boolean checkConformity(float forceLeft, float forceRight, float momentLeft, float momentRight, ISection section) {
        if ((forceLeft + 1 > section.getValueForceVerticalDiagramLeft() && section.getValueForceVerticalDiagramLeft() > forceLeft - 1) &&
                (forceRight + 1 > section.getValueForceVerticalDiagramRight() && section.getValueForceVerticalDiagramRight() > forceRight - 1) &&
                (momentLeft + 1 > section.getValueMomentDiagramLeft() && section.getValueMomentDiagramLeft() > momentLeft - 1) &&
                (momentRight + 1 > section.getValueMomentDiagramRight() && section.getValueMomentDiagramRight() > momentRight - 1))
            return true;
        return false;
    }

    @Test
    public void calculationDiagramBeam5() {
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

        Effect effect10 = factory.createHorizonForce();
        effect10.setAmountResult(-100);
        effect10.setDistanceToCenter(23);

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
        beam.getExternalInfluences().getEffects().add(effect10);

        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        Diagram diagram = (Diagram) Main.getContext().getBean("implDiagram");
        diagram.setExactness(6);
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println(supports);
        Set<ISection> sections = diagram.calculationDiagram(beam, supports);
        System.out.println(sections);

        ISection section1 = sections.stream().filter(x -> x.getDistance() == 0).findAny().get();
        ISection section2 = sections.stream().filter(x -> x.getDistance() == 2).findAny().get();
        ISection section3 = sections.stream().filter(x -> x.getDistance() == 3).findAny().get();
        ISection section4 = sections.stream().filter(x -> x.getDistance() == 5).findAny().get();
        ISection section5 = sections.stream().filter(x -> x.getDistance() == 7).findAny().get();
        ISection section6 = sections.stream().filter(x -> x.getDistance() == 10).findAny().get();
        ISection section7 = sections.stream().filter(x -> x.getDistance() == 12).findAny().get();
        ISection section8 = sections.stream().filter(x -> x.getDistance() == 15).findAny().get();
        ISection section9 = sections.stream().filter(x -> x.getDistance() == 17).findAny().get();
        ISection section10 = sections.stream().filter(x -> x.getDistance() == 20).findAny().get();
        ISection section11 = sections.stream().filter(x -> x.getDistance() == 21).findAny().get();
        ISection section12 = sections.stream().filter(x -> x.getDistance() == 23).findAny().get();
        ISection section13 = sections.stream().filter(x -> x.getDistance() == 24).findAny().get();
        ISection section14 = sections.stream().filter(x -> x.getDistance() == 25).findAny().get();

        assertTrue(checkConformity(0, -57, 0, 0, section1));
        assertTrue(checkConformity(-57, -57, -114, -134, section2));
        assertTrue(checkConformity(-57, -57, -191, -191, section3));
        assertTrue(checkConformity(-22, -27, -281.6f, -281.6f, section4));
        assertTrue(checkConformity(78, 78, -242.3f, -242.3f, section5));
        assertTrue(checkConformity(78, 67.4f, -8.3f, -8.3f, section6));
        assertTrue(checkConformity(67.4f, 67.4f, 126.4f, 126.4f, section7));
        assertTrue(checkConformity(-4.6f, -4.6f, 256.6f, 256.6f, section8));
        assertTrue(checkConformity(-63.8f, -63.8f, 204.6f, 204.6f, section9));
        assertTrue(checkConformity(-25.1f, -25.1f, 90.5f, 90.5f, section10));
        assertTrue(checkConformity(-25.1f, -25.1f, 65.4f, 43.4f, section11));
        assertTrue(checkConformity(-25.1f, -18.1f, -6.7f, -6.7f, section12));
        assertTrue(checkConformity(-18.1f, -18.1f, -24.8f, 18.1f, section13));
        assertTrue(checkConformity(-18.1f, 0, 0, 0, section14));
    }

    @Test
    public void calculationDiagramBeam6() {
        ApplicationContext context = Main.getContext();
        AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");

        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(25);

        Support support1 = factory.createSupportZadelka();
        support1.setDistance(25);

        DistributedLoadIncreasing effect1 = factory.createDistributedLoadIncreasing();
        effect1.setAmountLoadDirect(10);
        effect1.setFrom(12f);
        effect1.setTo(20f);
        effect1.setIncreasingLeft(false);

        DistributedLoadIncreasing effect2 = factory.createDistributedLoadIncreasing();
        effect2.setAmountLoadDirect(7);
        effect2.setFrom(10f);
        effect2.setTo(18f);
        effect2.setIncreasingLeft(true);

        DistributedLoadIncreasing effect3 = factory.createDistributedLoadIncreasing();
        effect3.setAmountLoadDirect(-15);
        effect3.setFrom(15f);
        effect3.setTo(25f);
        effect3.setIncreasingLeft(true);

        Effect effect4 = factory.createVerticalForce();
        effect4.setAmountResult(-18);
        effect4.setDistanceToCenter(16);

        Effect effect5 = factory.createMoment();
        effect5.setAmountResult(-40);
        effect5.setDistanceToCenter(0);

        beam.getSupports().getSupports().add(support1);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);
        beam.getExternalInfluences().getEffects().add(effect4);
        beam.getExternalInfluences().getEffects().add(effect5);

        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Diagram diagram = (Diagram) Main.getContext().getBean("implDiagram");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println(supports);
        Set<ISection> sections = diagram.calculationDiagram(beam, supports);
        System.out.println(sections);

        ISection section1 = sections.stream().filter(x -> x.getDistance() == 0).findAny().get();
        ISection section2 = sections.stream().filter(x -> x.getDistance() == 10).findAny().get();
        ISection section3 = sections.stream().filter(x -> x.getDistance() == 12).findAny().get();
        ISection section4 = sections.stream().filter(x -> x.getDistance() == 15).findAny().get();
        ISection section5 = sections.stream().filter(x -> x.getDistance() == 16).findAny().get();
        ISection section6 = sections.stream().filter(x -> x.getDistance() == 18).findAny().get();
        ISection section7 = sections.stream().filter(x -> x.getDistance() == 20).findAny().get();
        ISection section8 = sections.stream().filter(x -> x.getDistance() == 25).findAny().get();

        assertTrue(checkConformity(0, 0, 0, 40, section1));
        assertTrue(checkConformity(0, 0, 40, 40, section2));
        assertTrue(checkConformity(1.7f, 1.7f, 41.1f, 41.1f, section3));
        assertTrue(checkConformity(35.3f, 35.3f, 97.6f, 97.6f, section4));
        assertTrue(checkConformity(45, 27, 137.92f, 137.92f, section5));
        assertTrue(checkConformity(40.75f, 40.75f, 206.92f, 206.92f, section6));
        assertTrue(checkConformity(31.25f, 31.25f, 280.75f, 280.75f, section7));
        assertTrue(checkConformity(-25, 0, 312, 0, section8));
    }

    @Test
    public void calculationDiagramBeam7() {
        ApplicationContext context = Main.getContext();
        AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");

        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(25);

        Support support1 = factory.createSupportZadelka();
        support1.setDistance(0);

        DistributedLoadIncreasing effect1 = factory.createDistributedLoadIncreasing();
        effect1.setAmountLoadDirect(10);
        effect1.setFrom(12f);
        effect1.setTo(20f);
        effect1.setIncreasingLeft(false);

        DistributedLoadIncreasing effect2 = factory.createDistributedLoadIncreasing();
        effect2.setAmountLoadDirect(7);
        effect2.setFrom(10f);
        effect2.setTo(18f);
        effect2.setIncreasingLeft(true);

        DistributedLoadIncreasing effect3 = factory.createDistributedLoadIncreasing();
        effect3.setAmountLoadDirect(-15);
        effect3.setFrom(15f);
        effect3.setTo(25f);
        effect3.setIncreasingLeft(true);

        Effect effect4 = factory.createVerticalForce();
        effect4.setAmountResult(-18);
        effect4.setDistanceToCenter(16);

        Effect effect5 = factory.createMoment();
        effect5.setAmountResult(-40);
        effect5.setDistanceToCenter(25);

        beam.getSupports().getSupports().add(support1);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect2);
        beam.getExternalInfluences().getEffects().add(effect3);
        beam.getExternalInfluences().getEffects().add(effect4);
        beam.getExternalInfluences().getEffects().add(effect5);

        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Diagram diagram = (Diagram) Main.getContext().getBean("implDiagram");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println(supports);
        Set<ISection> sections = diagram.calculationDiagram(beam, supports);
        System.out.println(sections);

        ISection section1 = sections.stream().filter(x -> x.getDistance() == 0).findAny().get();
        ISection section5 = sections.stream().filter(x -> x.getDistance() == 16).findAny().get();
        ISection section8 = sections.stream().filter(x -> x.getDistance() == 25).findAny().get();

        assertTrue(checkConformity(0, 25, 0, -937, section1));
        assertTrue(checkConformity(70, 52, -439.08f, -439.08f, section5));
        assertTrue(checkConformity(0, 0, -40, 0, section8));
    }

    @Test
    public void calculationDiagramBeam8() {
        ApplicationContext context = Main.getContext();
        AllElementsFactory factory = (BasicDefaultFactory) context.getBean("basicDefaultFactory");

        IBeam beam = factory.createBeam();

        beam.getGeometricCharacteristics().setLength(10);

        Support support1 = factory.createSupportSharnirnoNepodviznaya();
        support1.setDistance(3);

        Support support2 = factory.createSupportSharnirnoPodviznaya();
        support2.setDistance(10);

        DistributedLoad effect1 = factory.createDistributedLoad();
        effect1.setAmountLoadDirect(-10);
        effect1.setFrom(1f);
        effect1.setTo(3.5f);

        Effect effect4 = factory.createVerticalForce();
        effect4.setAmountResult(-5);
        effect4.setDistanceToCenter(7.5f);

        Effect effect5 = factory.createMoment();
        effect5.setAmountResult(22);
        effect5.setDistanceToCenter(9);

        Effect effect6 = factory.createHorizonForce();
        effect6.setAmountResult(5);
        effect6.setDistanceToCenter(1);

        beam.getSupports().getSupports().add(support1);
        beam.getSupports().getSupports().add(support2);

        beam.getExternalInfluences().getEffects().add(effect1);
        beam.getExternalInfluences().getEffects().add(effect4);
        beam.getExternalInfluences().getEffects().add(effect5);
        beam.getExternalInfluences().getEffects().add(effect6);

        DefindReactionSupport defindReactionSupport = (ImplDifindReactionSupport) Main.getContext().getBean("implDifindReactionSupport");
        Diagram diagram = (Diagram) Main.getContext().getBean("implDiagram");
        List<Support> supports = defindReactionSupport.defindReactionSupport(beam);
        System.out.println(supports);
        Set<ISection> sections = diagram.calculationDiagram(beam, supports);
        System.out.println(sections);

        ISection section1 = sections.stream().filter(x -> x.getDistance() == 1).findAny().get();
        ISection section2 = sections.stream().filter(x -> x.getDistance() == 3).findAny().get();

        assertEquals(0, section1.getValueForceHorizontalDiagramLeft(), 1);
        assertEquals(-5, section1.getValueForceHorizontalDiagramRight(), 1);
        assertEquals(-5, section2.getValueForceHorizontalDiagramLeft(), 1);
        assertEquals(0, section2.getValueForceHorizontalDiagramRight(), 1);
    }
}