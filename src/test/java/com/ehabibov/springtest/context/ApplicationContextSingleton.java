package com.ehabibov.springtest.context;

import com.ehabibov.springtest.config.TestConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;;

public abstract class ApplicationContextSingleton {

    private static AbstractApplicationContext appContext;

    public static AbstractApplicationContext getContext(){
        if (appContext == null){
            appContext = new AnnotationConfigApplicationContext(TestConfig.class);
        }
        return appContext;
    }
}
