package com.reactionsSupport.beam.aspects;

import com.reactionsSupport.beam.beams.IBeam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Aspect
@Lazy
public class AspectForSectionalization {

    @Pointcut("execution(* *(..)) && within(com.reactionsSupport.beam.calculationSupportReaction.*)")
    private void allMethods (){};

    @Around("allMethods() && execution(java.util.Set *(com.reactionsSupport.beam.beams.IBeam)) && args(beam)")
    public Object checkedBeam (ProceedingJoinPoint joinPoint,IBeam beam) {
        if (beam.getExternalInfluences() == null || beam.getSupports() == null
                || beam.getGeometricCharacteristics()==null){
            System.err.println("ERROR: Отсутствуют поля класса Beam. Разделение по секциям невозможно.");
            System.exit(7);
        }
        Object output = new Object();
        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("INFO: Разбиение на секции завершенно.");
        return output;
    }

}
