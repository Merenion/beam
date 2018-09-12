package com.reactionsSupport.beam.beamElements;

import com.reactionsSupport.beam.annotations.Limitation;

import java.util.logging.Logger;

public abstract class EffectDistributed extends Effect {
	// абстрактный класс расширенный, для распределенной награзки

	protected float from; // координаты начала распределенной нагрузки
	protected float to; // координаты конца распределнной нагрузки
	protected float amountLoadDirect; // значение воздейсвия (на единицу длины)

	protected boolean avtoCalculation = true; // автоматическое определение расстояния до центра масс и результирующей
												// силы

	/** метод проверяет коректное введение переменных from,to */
	boolean isCorrectness() {
		boolean result = false;
		if (from > to) {
			to=from;
		}else {
			result = true;
		}
		return result;
	}

	public Float getFrom() {
		return from;
	}

	public Float getTo() {
		return to;
	}

	public float getAmountLoadDirect() {
		return amountLoadDirect;
	}

	public void setAmountLoadDirect(float amountLoadDirect) {
		this.amountLoadDirect = amountLoadDirect;
	}

	@Limitation
	public void setFrom(Float from) {
		this.from = from;
		isCorrectness();
	}

	@Limitation
	public void setTo(Float to) {
		this.to = to;
		isCorrectness();
	}

	public boolean isAvtoCalculation() {
		return avtoCalculation;
	}

	/** включить или выключить автоматическое определение различных переменных */
	public void setAvtoCalculation(boolean avtoCalculation) {
		this.avtoCalculation = avtoCalculation;
	}

}
