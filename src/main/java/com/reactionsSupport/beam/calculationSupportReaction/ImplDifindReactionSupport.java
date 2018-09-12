package com.reactionsSupport.beam.calculationSupportReaction;

import com.reactionsSupport.beam.annotations.Limitation;
import com.reactionsSupport.beam.beamElements.*;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoNepodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportSharnirnoPodviznaya;
import com.reactionsSupport.beam.beamElements.typeSupport.SupportZadelka;
import com.reactionsSupport.beam.beams.IBeam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ImplDifindReactionSupport implements DefindReactionSupport {

    private List<Effect> effects;
    private List<Support> supports;
    private float length;
    private int exactness =2;

    /**
     * Метод находит все реакции опоры балки
     */
    @Override
    public List<Support> defindReactionSupport(IBeam beam) {
        autoSetBrim(beam);
        List<Support> defindSupport = new ArrayList<>();
        if (beam.getSupports().getSupports().size() == 1)
            defindSupport.add(definReactionZadelka(effects, supports, length));
        if (beam.getSupports().getSupports().size() == 2)
            defindSupport.addAll(definReactionTwoSomeSupport(supports, effects));
        return defindSupport;
    }

    /**
     * Метод заполняет поля изначальными эффектами, геометрическими характеристиками и опорами балки
     */
    private void autoSetBrim(IBeam beam) {
        effects = beam.getExternalInfluences().getEffects();
        supports = beam.getSupports().getSupports();
        length = beam.getGeometricCharacteristics().getLength();
    }



    /**
     * Метод находит реакции Опоры типа "Заделка"
     */
    private Support definReactionZadelka(Collection<Effect> effects, Collection<Support> supports, float length) {
        Support support = supports.stream().filter(x -> x instanceof SupportZadelka).findAny().get();
        if (support.getDistance() == 0)
            support.getReaction().stream().filter(x -> x instanceof EffectMoment).findAny().get().
                    setAmountResult(definMomentZadelkaLeft(effects));
        if (support.getDistance() == length)
            support.getReaction().stream().filter(x -> x instanceof EffectMoment).findAny().get().
                    setAmountResult(reduction(definMomentZadelkaRight(effects, length)));
        support.getReaction().stream().filter(x -> x instanceof EffectVerticalForce).findAny().
                get().setAmountResult(reduction(-sumAllVerticalLoad(effects)));
        return support;
    }


    private List<Support> definReactionTwoSomeSupport(Collection<Support> inPutSupports, Collection<Effect> effects) {
    List<Support> supports = new ArrayList<>(inPutSupports);
        if ( supports.get(0) instanceof SupportSharnirnoPodviznaya &&
                supports.get(1) instanceof SupportSharnirnoPodviznaya) {
            return definReactionTwoSharnirPodviz(inPutSupports,effects);
        }
        return definReactionSharnirPodvizAndNepodviz(inPutSupports,effects);
    }

    /**
     * Метод выдает опоры былки с установленными реакциями опоры. Для двух подвижных шарных опор
     */
    private List<Support> definReactionTwoSharnirPodviz(Collection<Support> inPutSupports, Collection<Effect> effects) {
        List<Support> supports = new ArrayList<>(inPutSupports);
        Support supportLeft = supports.get(1);
        Support supportRight = supports.get(0);
        if (supportLeft.getDistance() > supportRight.getDistance()) {
            supportLeft = supports.get(0);
            supportRight = supports.get(1);
        }
        float momentLeft = sumMomentToAllEffectRespectiveSupport(supportLeft, effects);
        float momentRight = sumMomentToAllEffectRespectiveSupport(supportRight, effects);
        float supportReactionRigt = momentLeft / (supportRight.getDistance() - supportLeft.getDistance());
        float supportReactionLeft = -momentRight / (supportRight.getDistance() - supportLeft.getDistance());
        supportLeft.getReaction().stream().filter(x -> x instanceof EffectVerticalForce).findAny().get().setAmountResult(reduction(supportReactionLeft));
        supportRight.getReaction().stream().filter(x -> x instanceof EffectVerticalForce).findAny().get().setAmountResult(reduction(supportReactionRigt));
        return supports;
    }

    /**
     * Метод выдает опоры былки (шарнирно подвижной и не подвижной) с установленными реакциями опоры
     * отличие от метода definReactionTwoSharnirPodviz в том, что добавляет продольную реакцию в шарнирно
     * неподвижной опоре
     */
    private List<Support> definReactionSharnirPodvizAndNepodviz(Collection<Support> inPutSupports, Collection<Effect> effects) {
        inPutSupports.stream().filter(x -> x instanceof SupportSharnirnoNepodviznaya || x instanceof SupportZadelka).findAny().
                get().getReaction().stream().filter(x -> x instanceof EffectHorizontalForce).findAny().
                get().setAmountResult(-sumAllHorizontalForce(effects));
        return definReactionTwoSharnirPodviz(inPutSupports,effects);
    }



    /**
     * Подсчет всех приложенных сил по оси Y
     */
    private float sumAllVerticalLoad(Collection<Effect> effects) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect instanceof EffectDistributed || effect instanceof EffectVerticalForce)
                output += effect.getAmountResult();
        }
        return output;
    }

    /**
     * Расчет момента если заделка у левого конца)
     */
    private float definMomentZadelkaLeft(Collection<Effect> effects) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                output += effect.getAmountResult() * effect.getDistanceToCenter();
        }
        output += sumAllEffectMomentSupport(effects);
        return -output;
    }

    /**
     * Расчет момента если заделка у правого конца)
     */
    private float definMomentZadelkaRight(Collection<Effect> effects, float lengthBeam) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)
                output += effect.getAmountResult() * (lengthBeam - effect.getDistanceToCenter());
        }
        output -= sumAllEffectMomentSupport(effects);
        return output;
    }

    private float sumAllHorizontalForce(Collection<Effect> effects) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect instanceof EffectHorizontalForce)
                output += effect.getAmountResult();
        }
        return output;
    }

    /**
     * Метод суммирует все приложенные моменты
     */
    private float sumAllEffectMomentSupport(Collection<Effect> effects) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect instanceof EffectMoment)
                output += effect.getAmountResult();
        }
        return output;
    }

    /**
     * Метод выдает момент относительно опоры, создаваемый силами и расперделенной нагрузкой
     */
    private float sumMomentToForceRespectiveSupport(Support support, Collection<Effect> effects) {
        float output = 0;
        for (Effect effect : effects) {
            if (effect.getDistanceToCenter() < support.getDistance() && (effect instanceof EffectVerticalForce || effect instanceof EffectDistributed)) {
                output += effect.getAmountResult() * (support.getDistance() - effect.getDistanceToCenter());
            } else if (effect.getDistanceToCenter() >= support.getDistance() && (effect instanceof EffectVerticalForce || effect instanceof EffectDistributed))
                output -= effect.getAmountResult() * (effect.getDistanceToCenter() - support.getDistance());
        }
        return output;
    }

    /**
     * Метод выдает момент относительно опоры, создаваеммый всеми внешними эфектами
     */
    private float sumMomentToAllEffectRespectiveSupport(Support support, Collection<Effect> effects) {
        float output = 0;
        output -= sumAllEffectMomentSupport(effects);
        output += sumMomentToForceRespectiveSupport(support, effects);
        return output;
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