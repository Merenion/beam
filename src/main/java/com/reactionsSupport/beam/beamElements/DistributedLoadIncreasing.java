package com.reactionsSupport.beam.beamElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Lazy
public class DistributedLoadIncreasing extends EffectDistributedIncreasing {
	// прямолинейно возрастающая распределенная нагрузка


	/* Определение результирующей силы, используется при вызове гетера поля **/
	private void defindResultantForce() {
		if (!isCorrectness() || !avtoCalculation)
			return;
		amountResult = (to - from) * getAmountLoadDirect() / 2;
	}

	/* Определение дистанции до центра, используется при вызове гетера поля **/
	private void defindDistanceToCenter() {
		if (!isCorrectness() || !avtoCalculation)
			return;
		if (isIncreasingLeft) {
			distanceToCenter = (to - from) * 2 / 3 + from;
		} else {
			distanceToCenter = (to - from) / 3 + from;
		}
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
		return "DistributedLoadIncreasing{" +
				"isIncreasingLeft=" + isIncreasingLeft +
				", from=" + from +
				", to=" + to +
				", amountResult=" + amountLoadDirect +
				'}';
	}
}
