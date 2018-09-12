package com.reactionsSupport.beam;

import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
import com.reactionsSupport.beam.calculationDiagrams.Sectionalizat;
import com.reactionsSupport.beam.start.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ImplSectionalizatTest {

    @Before
    public void setUp() throws Exception {
        System.out.println("START TEST");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("END TEST");

    }

    @Test
    public void sectionalization() {
        IBeam beam = Beam.getBeam1();
        beam.showOnDisplayAllInfo();
        Sectionalizat sectionalizat = (Sectionalizat) Main.getContext().getBean("implSectionalizat");
        HashSet<ISection> result = (HashSet) sectionalizat.sectionalization(beam);
        System.out.println(result);
        List<Float> distances = result.stream().map(ISection::getDistance).collect(Collectors.toList());
        assertEquals(result.size(),9);
        assertTrue(distances.contains(0f));
        assertTrue(distances.contains(30f));
    }


}