package com.reactionsSupport.beam.beams;

import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.usedElements.ExternalInfluences;
import com.reactionsSupport.beam.beams.usedElements.GeometricCharacteristicsBeam;
import com.reactionsSupport.beam.beams.usedElements.Supports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@Scope("prototype")
public class BeamDefault implements IBeam {
    //главный класс стандартной балки где проводятся все расчеты и содержится вся информация.

    private Logger log = Logger.getLogger("BeamDefault");

    @Autowired
	private Supports supports;                                          //содержит все опоры
	@Autowired
	private GeometricCharacteristicsBeam geometricCharacteristics;      //хранилище геометрических сил
	@Autowired
	private ExternalInfluences externalInfluences;                      //хранилище внешних воздействий

	/**Вывести все характеристики в консоль*/
	@Override
	public void showOnDisplayAllInfo() {
	    log.info("view characteristics");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Supports:");
        for(Support support : supports.getSupports()) {
            System.out.println("    " + support);
        }
        System.out.println("External Influences:");
        for (Effect effect : externalInfluences.getEffects())
            System.out.println("    "+effect);
        System.out.println("Geometric characteristics:");
        System.out.println("    "+geometricCharacteristics.getLength());
	}

	void init () {
	    log.info("\n** repository support - " +supports+
                "\n** repository geometricCharacteristics - "+geometricCharacteristics+
                "\n** repository externalInfluences - " +externalInfluences);

    }

	@Override
	public String toString() {
		return "BeamDefault{" +
				"\n    supports=" + supports +
				", \n    geometricCharacteristics=" + geometricCharacteristics +
				", \n    externalInfluences=" + externalInfluences +
				'}';
	}

	public Supports getSupports() {
		return supports;
	}

	public GeometricCharacteristicsBeam getGeometricCharacteristics() {
		return geometricCharacteristics;
	}

	public void setSupports(Supports supports) {
		this.supports = supports;
	}

	public void setGeometricCharacteristics(GeometricCharacteristicsBeam geometricCharacteristics) {
		this.geometricCharacteristics = geometricCharacteristics;
	}

	public ExternalInfluences getExternalInfluences() {
		return externalInfluences;
	}

	public void setExternalInfluences(ExternalInfluences externalInfluences) {
		this.externalInfluences = externalInfluences;
	}
}
