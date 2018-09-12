package com.reactionsSupport.beam.beamElements;

import com.reactionsSupport.beam.annotations.Limitation;

public abstract class Effect {
	// главнный абстрактный класс всех воздейсвий на балку, в том числе и реакций
	// опор (силы моменты распределенные нагрузки)

	protected float amountResult; // результирующая сила
	protected float distanceToCenter; // дистанция до центра воздействия (центра масс)

	public float getAmountResult() {
		return amountResult;
	}

	public float getDistanceToCenter() {
		return distanceToCenter;
	}

	public void setAmountResult(float amountResult) {
		this.amountResult = amountResult;
	}

	@Limitation
	public void setDistanceToCenter(float distanceToCenter) {
		this.distanceToCenter = distanceToCenter;
	}

}
