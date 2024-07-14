package org.annotation.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
public class BeanTest {
    @Test
    public void test_bean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Vehicle v = context.getBean("vehicle", Vehicle.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
        assertEquals("volvo", v.brand().name());
        assertEquals(1844, v.brand().year());
        ((AbstractApplicationContext) context).close();
    }
}
