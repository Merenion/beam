package com.reactionsSupport.beam.beamElements.typeSupport;

import com.reactionsSupport.beam.beamElements.Effect;

import java.util.Collection;
import java.util.List;
//абсрактный класс любой опоры

/*с помощью этого метода можно получить возникающие реакции опоры**/
public interface Support extends Cloneable {
	List<Effect> getReaction();
	float getDistance();
	void setDistance(float distance);
}
