package com.reactionsSupport.beam;

import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
import com.reactionsSupport.beam.calculationDiagrams.Sectionalizat;
import com.reactionsSupport.beam.calculationDiagrams.SeparatorDistributedLoad;
import com.reactionsSupport.beam.start.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ImplSeparatorLoadTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void separatorLoads() {
        SeparatorDistributedLoad separtor = (SeparatorDistributedLoad) Main.getContext().getBean("implSeparatorDistributedLoad");
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");

        Collection<Effect> effectsEmpty = new ArrayList<>();
        Collection<Effect> effects = Beam.getBeam1().getExternalInfluences().getEffects();
        Set<ISection> sectionsEmpty = new HashSet<>();
        Set<ISection> sections = sectionalizat.sectionalization(Beam.getBeam1());

        List<Effect> returnEffects1 = separtor.separatorLoads(effectsEmpty,sectionsEmpty);
        List<Effect> returnEffects2 = separtor.separatorLoads(effects,sectionsEmpty);
        List<Effect> returnEffects3 = separtor.separatorLoads(effectsEmpty,sections);
        List<Effect> returnEffects4 = separtor.separatorLoads(effects,sections);

        assertEquals(0, returnEffects1.size());
        assertEquals(4, returnEffects2.size());
        assertEquals(0, returnEffects3.size());
        assertEquals(7, returnEffects4.size());
    }
}