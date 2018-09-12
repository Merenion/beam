package com.reactionsSupport.beam.beams;

import com.reactionsSupport.beam.beams.usedElements.ExternalInfluences;
import com.reactionsSupport.beam.beams.usedElements.GeometricCharacteristicsBeam;
import com.reactionsSupport.beam.beams.usedElements.Supports;

public interface IBeam {
	void showOnDisplayAllInfo();
	Supports getSupports();
	GeometricCharacteristicsBeam getGeometricCharacteristics();
	ExternalInfluences getExternalInfluences();
}
