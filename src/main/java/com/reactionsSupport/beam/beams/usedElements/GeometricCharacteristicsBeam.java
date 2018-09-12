package com.reactionsSupport.beam.beams.usedElements;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("prototype")
public class GeometricCharacteristicsBeam {
	private float length;

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "GeometricCharacteristicsBeam{" +
				"length=" + length +
				'}';
	}
}
