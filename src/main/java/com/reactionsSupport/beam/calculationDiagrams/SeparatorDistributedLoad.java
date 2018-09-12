package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.beamElements.Effect;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SeparatorDistributedLoad {

    List<Effect> separatorLoads(Collection<Effect> effects, Set<ISection> sections);
}
