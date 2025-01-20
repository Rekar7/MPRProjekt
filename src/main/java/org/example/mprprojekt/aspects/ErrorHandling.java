package org.example.mprprojekt.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandling {

    @AfterThrowing(pointcut = "execution(* org.example.mprprojekt.services.*.*(..))", throwing = "ex")
    public void handleServiceLayerException(Exception ex) {
        System.err.println("Przechwycono wyjątek w serwisie: " + ex.getMessage());

        throw new RuntimeException("Błąd w serwisie: " + ex.getMessage(), ex);
    }

    @AfterThrowing(pointcut = "execution(* org.example.mprprojekt.controllers.*.*(..))", throwing = "ex")
    public void handleControllerLayerException(Exception ex) {
        System.err.println("Przechwycono wyjątek w kontrolerze: " + ex.getMessage());

        throw new RuntimeException("Błąd w kontrolerze: " + ex.getMessage(), ex);
    }
}
