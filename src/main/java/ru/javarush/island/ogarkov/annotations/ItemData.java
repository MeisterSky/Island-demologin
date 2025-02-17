package ru.javarush.island.ogarkov.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ItemData {
    String name();

    String icon();

    double maxWeight() default 1;

    int maxCount() default 200;

    int maxSpeed() default 0;

    double maxFood() default 0;
}
