package com.reactionsSupport.beam.aspects;

import com.reactionsSupport.beam.beamElements.DistributedLoad;
import com.reactionsSupport.beam.beamElements.Effect;
import com.reactionsSupport.beam.calculationDiagrams.ISection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
@Aspect
public class AspectForSeparatorDistributedLoad {

    @Pointcut("execution(* *(..)) && within(com.reactionsSupport.beam.calculationSupportReaction.*)")
    private void allMethods (){};

    @Around(value = "allMethods() && execution(java.util.List<com.reactionsSupport.beam.beamElements.Effect> *(java.util.Collection, java.util.Set))  && args(effects,section)")
    public Object checkNeedSeparator (ProceedingJoinPoint joinPoint, Collection<Effect> effects, Set<ISection> section) {
        if (section.size()==0) {
            System.out.println("WARNING: Секции отсутствуют.");
        }
        if (effects.stream().noneMatch(x-> x instanceof DistributedLoad)|| section.size()<3){
            System.out.println("INFO: Разбиение распределенных нагрузок не требуется.");
            return effects;
        }
        Object output = new Object();
        try {
            output = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("INFO: Разбиение распределенной нагрузки завершенно.");
        return output;
    }


}
