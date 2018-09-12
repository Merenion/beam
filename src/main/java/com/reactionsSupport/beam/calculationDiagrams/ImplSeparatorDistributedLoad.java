package com.reactionsSupport.beam.calculationDiagrams;

import com.reactionsSupport.beam.beamElements.*;
import com.reactionsSupport.beam.factory.EffectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Lazy
public class ImplSeparatorDistributedLoad implements SeparatorDistributedLoad {

    @Autowired
    private EffectFactory effectDefaultFactory;

    /**Метод предназначен для разбиения распределенных нагрузок в случае если секция
     * попадает под область (координаты) ее действия. Это нужно для того чтобы можно было применять
     * метод сечения и строить эпюры, а так же для удобства расчета реакций опор. Метод принимает коллекцию
     * всех воздействий на балку (нагрузки, силы, моменты) и коллекцию сечений балки, обрабатывает их,
     * и выдает коллекцию тех же воздействий только с  разделенными распределенными нагрузками*/
    public List<Effect> separatorLoads(Collection<Effect> effects, Set<ISection> sections) {
        List<EffectDistributed> effectsDistributed = filterNeadSeparator(effects, sections);
        List<Effect> returnEfects = new ArrayList<>(effects);
        for (EffectDistributed effect : effectsDistributed) {
            ISection section = fintPointSeparator(effect,sections);
            returnEfects.addAll(shedlerdistributedLoad(effect,section));
            returnEfects.remove(effect);
        }
        if (checkNeedSeparation(returnEfects,sections))
            returnEfects = separatorLoads(returnEfects, sections);
        return returnEfects;
    }

    /**Метод в зависимости от типа распределенной нагрузки (возрастающая, не возрастающая),
     * вызывает определенный метод для разбиения, и выдает его результат. Принимает
     * эфект (распределенную нагрузку) и секцию, по координатам которой происзодит разбиение эфекта.*/
    private List<EffectDistributed> shedlerdistributedLoad (EffectDistributed effect, ISection section) {
        if (effect instanceof EffectDistributedIncreasing) {
            return separatorDistributedLoadIncreasing((EffectDistributedIncreasing) effect,section);
        } else {
            return separatorDistributedLoad(effect,section);
        }
    }

    /**Метод принимает коллекцию секций и воздейсвий на балку (силы, моменты, распределенные нагрузки)
     * и выдает только распределенные нагрузки которые необходимо разделить*/
    private List<EffectDistributed> filterNeadSeparator(Collection<Effect> effects, Set<ISection> sections) {
        return effects.stream().filter(x -> x instanceof EffectDistributed).map(x -> (EffectDistributed) x).filter(x -> {
            for (ISection section : sections) {
                if (x.getFrom() < section.getDistance() && x.getTo() > section.getDistance()) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**Метод принимает один эффект и коллекцию секций, и выдает только ту секцию из заданной коллекции,
     * по которой должно происходить разбиение заданного эффекта. Если таковой нет то выдает null.*/
    private ISection fintPointSeparator(EffectDistributed effect, Set<ISection> sections) {
        for (ISection section : sections) {
            if (effect.getFrom() < section.getDistance() && effect.getTo() > section.getDistance())
                return section;
        }
        return null;
    }

    /**Метод непосредственно разбивает заданную в параметрах распределенную, прямоугольную нагрузку на две,
     * по координатам заданной в параметрах секции и выдает коллекцию с этими эффектами*/
    private List<EffectDistributed> separatorDistributedLoad(EffectDistributed effect, ISection section) {
        EffectDistributed newEffect1 = createDistributedLoad(effect.getFrom(), section.getDistance(), effect.getAmountLoadDirect());
        EffectDistributed newEffect2 = createDistributedLoad(section.getDistance(), effect.getTo(), effect.getAmountLoadDirect());
        List<EffectDistributed> effects = new ArrayList<>();
        effects.add(newEffect1);
        effects.add(newEffect2);
        return effects;
    }

    /**Метод разбивает заданную в параметрах распределенную, возрастающую нагрузку на две,
     * по координатам заданной в параметрах секции и выдает коллекцию с этими эффектами*/
    private List<EffectDistributed> separatorDistributedLoadIncreasing(EffectDistributedIncreasing effect, ISection section) {
        List<EffectDistributed> effectsDistributed = new ArrayList<>();
        if (effect.isIncreasingLeft()) {
            effectsDistributed.addAll(swapDistributedLoadIncreasingLeft(effect, section.getDistance()));
        } else {
            effectsDistributed.addAll(swapDistributedLoadIncreasingRite(effect, section.getDistance()));
        }
        return effectsDistributed;
    }

    /**Метод создает распределенную прямоугольную нагрузку с помощью фабрики
     * и заполняет ее параметры входными параметрамми метода (конструктор)*/
    private EffectDistributed createDistributedLoad(float from, float to, float value) {
        EffectDistributed newEffect = effectDefaultFactory.createDistributedLoad();
        newEffect.setFrom(from);
        newEffect.setTo(to);
        newEffect.setAmountLoadDirect(value);
        return newEffect;
    }

    /**Метод создает распределенную возрастающую нагрузку с помощью фабрики
     * и заполняет ее параметры входными параметрамми метода (конструктор)*/
    private EffectDistributed createDistributedLoadIncreasing(float from, float to, float value, boolean isIncreasingLeft) {
        EffectDistributed newEffect = effectDefaultFactory.createDistributedLoadIncreasing();
        newEffect.setFrom(from);
        newEffect.setTo(to);
        newEffect.setAmountLoadDirect(value);
        ((EffectDistributedIncreasing) newEffect).setIncreasingLeft(isIncreasingLeft);
        return newEffect;
    }

    /**Метод разделяет заданную, возрастающую с левого края, распределенную нагрузку по заданным
     * координатам, и выдает коллекуию с распределенными нагрузками, которые были полученны после разбиения*/
    private List<EffectDistributed> swapDistributedLoadIncreasingLeft(EffectDistributedIncreasing effect, float coordinate) {
        float amountLoadInCoordinate = effect.getAmountLoadDirect() * definedConst(effect, coordinate);
        EffectDistributedIncreasing newEffect1 = (EffectDistributedIncreasing) createDistributedLoadIncreasing(effect.getFrom(),
                coordinate, amountLoadInCoordinate, true);
        EffectDistributedIncreasing newEffect2 = (EffectDistributedIncreasing) createDistributedLoadIncreasing(coordinate,
                effect.getTo(), effect.getAmountLoadDirect() - amountLoadInCoordinate,
                true);
        EffectDistributed newEffect3 = createDistributedLoad(coordinate, effect.getTo(), amountLoadInCoordinate);
        List<EffectDistributed> effects = new ArrayList<>();
        effects.add(newEffect1);
        effects.add(newEffect2);
        effects.add(newEffect3);
        return effects;
    }

    /**Метод разделяет заданную, возрастающую с правого края, распределенную нагрузку по заданным
     * координатам, и выдает коллекуию с распределенными нагрузками, которые были полученны после разбиения*/
    private List<EffectDistributed> swapDistributedLoadIncreasingRite(EffectDistributedIncreasing effect, float coordinate) {
        float amountLoadInCoordinate = effect.getAmountLoadDirect() * definedConst(effect, coordinate);
        EffectDistributedIncreasing newEffect1 = (EffectDistributedIncreasing) createDistributedLoadIncreasing(effect.getFrom(),
                coordinate, effect.getAmountLoadDirect() - amountLoadInCoordinate, false);
        EffectDistributedIncreasing newEffect2 = (EffectDistributedIncreasing) createDistributedLoadIncreasing(coordinate,
                effect.getTo(), amountLoadInCoordinate, false);
        EffectDistributed newEffect3 = createDistributedLoad(effect.getFrom(), coordinate, amountLoadInCoordinate);
        List<EffectDistributed> effects = new ArrayList<>();
        effects.add(newEffect1);
        effects.add(newEffect2);
        effects.add(newEffect3);
        return effects;
    }

    /**Метод определяет длину области действия распределенной нагрузки*/
    private float definedLengthLoad(EffectDistributed effect) {
        return effect.getTo() - effect.getFrom();
    }

    /**Метод определяет константу, по которой расчитывается сила действия на единицу длины РН
     * по заданным координатам*/
    private float definedConst(EffectDistributedIncreasing effect, Float coordinate) {
        float otnoshenieCoordinate = (coordinate - effect.getFrom()) / definedLengthLoad(effect);
        float output;
        if (effect.isIncreasingLeft()) {
            output = otnoshenieCoordinate;
        } else {
            output = 1 - otnoshenieCoordinate;
        }
        return output;
    }

    /**ПРоверяет нужно ли разделять распределенную нагрузку имеющуюсю в листе*/
    private boolean checkNeedSeparation (Collection<Effect> effects, Set<ISection> sections) {
        for (ISection section:sections){
            for (Effect effect: effects){
                if ((effect instanceof EffectDistributed)
                        && (((EffectDistributed) effect).getFrom()<section.getDistance())
                        && (((EffectDistributed) effect).getTo()>section.getDistance()))
                    return true;
            }
        }
        return false;
    }
}
