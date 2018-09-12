package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.EffectDistributed;
import com.reactionsSupport.beam.beamElements.EffectLocal;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.factoryForCalculation.FactorySection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImplSectionalizat implements Sectionalizat {

    private final FactorySection factorySection; //фабрика секций

    @Autowired
    public ImplSectionalizat(FactorySection factorySection) {
        this.factorySection = factorySection;
    }

    /**Метод достает все эффекты и опоры из балки, в зависимости от их расположения разбивает балку на секции
     * по которым в дальнейшем могут строиться эпюры. Метод возвращает коллекцию секций балки.*/
    @Override
    public Set<ISection> sectionalization(IBeam beam) {
        HashSet<Float> coordinateSections= new HashSet<Float>();
        coordinateSections.addAll(coordinatsForcesAndMoments(beam.getExternalInfluences().getEffects()));
        coordinateSections.addAll(coordinatsSupport(beam.getSupports().getSupports()));
        coordinateSections.addAll(coordinatsDistributedLoad(beam.getExternalInfluences().getEffects()));
        coordinateSections.add(0f);//добавление координат балки (левый конец)
        coordinateSections.add(beam.getGeometricCharacteristics().getLength()); //добавление координат балки (правый конец)
        return createSections(coordinateSections);
    }

    /**Метод возвращает множество координат, которые принадлежат моментам и силам, приложенным к балке.*/
    private Set<Float> coordinatsForcesAndMoments(Collection<Effect> effects){
        Set<Float> coordinatsEffect = new HashSet<>();
        for (Effect effect: effects){
            if ( effect instanceof EffectLocal)
                coordinatsEffect.add(effect.getDistanceToCenter());
        }
        return coordinatsEffect;
    }

    /**Метод возвращает множество координат, которые принадлежат опорам балки.*/
    private Set<Float> coordinatsSupport(Collection<Support> supports) {
        Set<Float> coordinatsSupport = new HashSet<>();
        for (Support support: supports){
            coordinatsSupport.add(support.getDistance());
        }
        return coordinatsSupport;
    }

    /**Метод возвращает множество координат, которые принадлежат каждой распределенной нагрузки балки
     * Координаты - начало и конец распределенной нагрузки.*/
    private Set<Float> coordinatsDistributedLoad(Collection<Effect> effects){
        Set<Float> coordinatsEffect = new HashSet<>();
        for (Effect effect: effects){
            if (effect instanceof EffectDistributed) {
                coordinatsEffect.add(((EffectDistributed) effect).getFrom());
                coordinatsEffect.add(((EffectDistributed) effect).getTo());
            }
        }
        return coordinatsEffect;
    }

    /**Метод принимает множество координат, создает с помощью фабрики множество секций,
     * соответствующее множеству координат.*/
    private Set<ISection> createSections (Set<Float> coordinatsSections) {
        Set<ISection> sections = new HashSet<>();
        for (Float distanceSection: coordinatsSections)
            sections.add(factorySection.createSection(distanceSection));
    return sections;
    }
}
