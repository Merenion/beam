package com.reactionsSupport.beam.calculationDiagrams.factoryForCalculation;

import com.reactionsSupport.beam.calculationDiagrams.ISection;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public abstract class FactoryDefaultSection implements FactorySection{

    @Override
    @Lookup
    public abstract ISection createSection(Float distance);
}
