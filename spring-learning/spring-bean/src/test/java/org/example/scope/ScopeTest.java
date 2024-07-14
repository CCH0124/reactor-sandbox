package org.example.scope;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class ScopeTest {
    @Test
    public void givenSingletonScope_whenSetName_thenEqualNames() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopeConfig.class);

        Cat catSingletonA = (Cat) applicationContext.getBean("catSingleton");
        Cat catSingletonB = (Cat) applicationContext.getBean("catSingleton");
        catSingletonA.setName("set");
        assertEquals(catSingletonA.getName(), catSingletonB.getName());
        System.out.println(catSingletonA.hashCode());
        System.out.println(catSingletonB.hashCode());
        ((AbstractApplicationContext) applicationContext).close();
    }

    @Test
    public void givenPrototypeScope_whenSetNames_thenDifferentNames() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopeConfig.class);

        Cat catPrototypeA = (Cat) applicationContext.getBean("catPrototype");
        Cat catPrototypeB = (Cat) applicationContext.getBean("catPrototype");

        catPrototypeA.setName("Ragdoll");
        catPrototypeB.setName("American Shorthair");

        assertEquals("Ragdoll", catPrototypeA.getName());
        assertEquals("American Shorthair", catPrototypeB.getName());

        ((AbstractApplicationContext) applicationContext).close();
    }
}
