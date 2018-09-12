package com.reactionsSupport.beam.beamElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class DistributedLoad extends EffectDistributed {
    // класс распределенная нагрузка

    /* Определение результирующей силы, используется при вызове гетера поля **/
    private void defindResultantForce() {
        if (!isCorrectness() || !avtoCalculation)
            return;
        amountResult = (to - from) * getAmountLoadDirect();
    }

    /* Определение дистанции до центра, используется при вызове гетера поля **/
    private void defindDistanceToCenter() {
        if (!isCorrectness() || !avtoCalculation)
            return;
        distanceToCenter = (to - from) / 2 + from;
    }

    @Override
    public float getAmountResult() {
        defindResultantForce();
        return super.getAmountResult();
    }

    @Override
    public float getDistanceToCenter() {
        defindDistanceToCenter();
        return super.getDistanceToCenter();
    }

    @Override
    public String toString() {
        return "DistributedLoad{" +
                "from=" + from +
                ", to=" + to +
                ", amountResult=" + amountLoadDirect +
                '}';
    }
}
