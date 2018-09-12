package com.reactionsSupport.beam.beams.usedElements;

import com.reactionsSupport.beam.beamElements.Effect;
import javafx.print.Collation;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class ExternalInfluences {
    //этот класс хранит все приложенные силы и моменты

    private List<Effect> effects = new ArrayList<>();

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    @Override
    public String toString() {
        return "ExternalInfluences{" +
                "effects=" + effects +
                '}';
    }
}
