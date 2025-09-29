package com.syed.quizApplication.aspects;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {


    //--> Boiler Plate code (used in single mothode only)
    //@Before("execution(* com.syed.quizApplication.serviceInterfaceImpl.QuestionServiceImpl.*(..))") // Point Cut expression
    //public void fetchQuestionLogAspect(){
      //  System.out.println("Before fetching Question ");}

    //--> Reusable for many method using oldtransfer()

    @Pointcut("execution(* com.syed.quizApplication.serviceInterfaceImpl.QuestionServiceImpl.*(..))")
    private  void  oldtransfer(){}
    @Before("oldtransfer()") // Point Cut expression
    public void fetchQuestionLogAspect(){
        System.out.println("Before fetching Question ");
    }

}
