package com.reactionsSupport.beam.aspects;

import com.reactionsSupport.beam.beamElements.DistributedLoad;
import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.beamElements.typeSupport.Support;
import com.reactionsSupport.beam.beams.IBeam;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@Aspect
@Lazy
public class СheckForStatic {

    @Pointcut("execution(* *(..)) && within(com.reactionsSupport.beam.calculationSupportReaction.*)")
    private void allMethods (){};

    @Around(value = "allMethods() && execution(java.util.List<com.reactionsSupport.beam.beamElements.typeSupport.Support> *(com.reactionsSupport.beam.beams.IBeam))  && args(beam)")
    public Object checkNeedSeparator (ProceedingJoinPoint joinPoint, IBeam beam) {
        int unknownValues =0;
        for (Support support:beam.getSupports().getSupports())
            unknownValues += support.getReaction().size();
        System.out.println("INFO: Количесво неизвестных = " + unknownValues);
        Object output = new Object();
        if (unknownValues<=3 && unknownValues>1){
            System.out.println("INFO: Система статически определима");
            try {
                output = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }else {
            System.err.println("ERROR: Статически не определима. Решение не возможно данной программой");
        }
       return output;
    }

}
