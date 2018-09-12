package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.ISection;

import java.util.Set;

public interface Sectionalizat {
    Set<ISection> sectionalization (IBeam beam);
}
