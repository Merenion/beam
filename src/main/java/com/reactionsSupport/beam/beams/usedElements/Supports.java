package com.reactionsSupport.beam.beams.usedElements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope("prototype")
public class Supports {
	// классссссс содержащий в себе опоры балки

	private List<Support> supports = new ArrayList<Support>();

	public List<Support> getSupports() {
		return supports;
	}

	public void setSupports(List<Support> supports) {
		this.supports = supports;
	}

	@Override
	public String toString() {
		return "Supports{" +
				"supports=" + supports +
				'}';
	}
}
