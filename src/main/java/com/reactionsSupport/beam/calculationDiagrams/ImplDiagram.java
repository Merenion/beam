package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.annotations.Limitation;
import com.reactionsSupport.beam.beamElements.*;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ImplDiagram implements Diagram {

    @Autowired
    private Sectionalizat sectionalizat;

    @Autowired
    private SeparatorDistributedLoad separator;

    private int exactness =2;

    @Override
    public Set<ISection> calculationDiagram(IBeam beam, List<Support> defindSupport) {
        Set<ISection> sections = sectionalizat.sectionalization(beam);
        List<Effect> effects = separator.separatorLoads(beam.getExternalInfluences().getEffects(),sections);
        List<Effect> allEffects = createListContainingAllEffectBeam(effects,defindSupport);
        for (ISection section:sections)
            definValueForceAndMomentInSection(section,allEffects);
        return sections;
    }

    private void definValueForceAndMomentInSection(ISection section, List<Effect>allEffectsBeam){
        sumForceLeft(section,allEffectsBeam);
        sumForceRight(section,allEffectsBeam);
        sumMomentLeft(section,allEffectsBeam);
        sumMomentRight(section,allEffectsBeam);
        sumHorizontalForceLeft(section,allEffectsBeam);
        sumHorizontalForceRight(section,allEffectsBeam);
    }

    /**Метод создает лист и помещает суда все дейсвующие силы и моменты на балку (Распр.нагрузку тоже)
     * а так же помещает туда силы и моменты созданные реакциями опор. в сумме все эти эфекты дают
     * уравновешенную систему.*/
    private List<Effect> createListContainingAllEffectBeam(List<Effect> effects,List<Support> defindSupport){
        List<Effect> allEffects = new ArrayList<>(effects);
        for (Support support:defindSupport)
            allEffects.addAll(support.getReaction());
        return allEffects;
    }

    /**Метод находит и задает в секции значение эфюры сил с левой стороны*/
    private void sumForceLeft (ISection section, List<Effect>allEffectsBeam) {
        float sumForce =0;
        for (Effect effect:allEffectsBeam){
            if ((effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                    && effect.getDistanceToCenter()<section.getDistance())
                sumForce += effect.getAmountResult();
        }
        section.setValueForceVerticalDiagramLeft(reduction(sumForce));
    }

    /**Метод находит и задает в секции значение эфюры сил с правой стороны*/
    private void sumForceRight (ISection section, List<Effect>allEffectsBeam) {
        float sumForce =0;
        for (Effect effect:allEffectsBeam){
            if ((effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                    && effect.getDistanceToCenter()>section.getDistance())
                sumForce -= effect.getAmountResult();
        }
        section.setValueForceVerticalDiagramRight(reduction(sumForce));
    }

    /**Метод находит и задает в секции значение эфюры моментов с левой стороны*/
    private void sumMomentLeft (ISection section, List<Effect>allEffectsBeam) {
        float sumMoment =0;
        for (Effect effect:allEffectsBeam){
            if (effect instanceof EffectMoment && effect.getDistanceToCenter()<section.getDistance())
                sumMoment -= effect.getAmountResult();
            if ((effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                    && effect.getDistanceToCenter()<section.getDistance()) {
                sumMoment += (section.getDistance() - effect.getDistanceToCenter()) * effect.getAmountResult();
            }
        }
        section.setValueMomentDiagramLeft(reduction(sumMoment));
    }

    /**Метод находит и задает в секции значение эфюры моментов с правой стороны*/
    private void sumMomentRight (ISection section, List<Effect>allEffectsBeam) {
        float sumMoment =0;
        for (Effect effect:allEffectsBeam){
            if (effect instanceof EffectMoment && effect.getDistanceToCenter()>section.getDistance())
                sumMoment += effect.getAmountResult();
            if ((effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                    && effect.getDistanceToCenter()>section.getDistance()) {
                sumMoment += (effect.getDistanceToCenter() - section.getDistance()) * effect.getAmountResult();
            }
        }
        section.setValueMomentDiagramRight(reduction(sumMoment));
    }

    /**Метод находит и задает в секции значение эпюры продольных сил с левой стороны*/
    private void sumHorizontalForceLeft (ISection section, List<Effect>allEffectsBeam) {
        float sumForce =0;
        for (Effect effect:allEffectsBeam){
            if (effect instanceof EffectHorizontalForce && effect.getDistanceToCenter()<section.getDistance())
                sumForce -= effect.getAmountResult();
        }
        section.setValueForceHorizontalDiagramLeft(reduction(sumForce));
    }

    /**Метод находит и задает в секции значение эпюры продольных сил с левой стороны*/
    private void sumHorizontalForceRight (ISection section, List<Effect>allEffectsBeam) {
        float sumForce =0;
        for (Effect effect:allEffectsBeam){
            if (effect instanceof EffectHorizontalForce && effect.getDistanceToCenter()>section.getDistance())
                sumForce += effect.getAmountResult();
        }
        section.setValueForceHorizontalDiagramRight(reduction(sumForce));
    }


    /**Метод округляет дробные числа до 2-го знака после запятой*/
    private float reduction (float value){
        return (float) ((float)(Math.rint(value*Math.pow(10,exactness)))/Math.pow(10,exactness));
    }

    @Override
    public int getExactness() {
        return exactness;
    }

    @Limitation
    @Override
    public void setExactness(int exactness) {
        this.exactness = exactness;
    }
}
