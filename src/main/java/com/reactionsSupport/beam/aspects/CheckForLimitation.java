package com.reactionsSupport.beam.aspects;

import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

import static java.util.logging.Level.INFO;

@Component
@Aspect
@Lazy
public class CheckForLimitation {

    @Pointcut("execution(* *(..)) && @annotation(com.reactionsSupport.beam.annotations.Limitation)")
    private void forAnnotations() {

    }

    @Pointcut("forAnnotations() && within(com.reactionsSupport.beam.beamElements.*)")
    private void distanceElementsForBeam() {
    }

    @Pointcut("forAnnotations() && within(com.reactionsSupport.beam.calculationDiagrams.*)")
    private void accurateСalculations() {
    }

    @Around(value = "distanceElementsForBeam() && execution(* *(..))  && args(distance)")
    public void limitationForlengthBeam(ProceedingJoinPoint joinPoint, float distance) {
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Around(value = "accurateСalculations() && execution(* *(..))  && args(distance)")
    public void limitationAccurateСalculations(ProceedingJoinPoint joinPoint, float distance) {
        if (distance<1 || distance>5){
            System.out.println("WARNING: Установленна недопустимая точность! Точность должна быть 0>р<6.");
            return;
        } else {
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            System.out.println("INFO: Точность ("+distance+") установленна." );
        }
    }
}
