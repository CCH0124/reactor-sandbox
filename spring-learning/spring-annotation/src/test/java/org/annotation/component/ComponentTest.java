package org.annotation.component;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


public class ComponentTest {

    @Test
    public void get_all_bean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        AzureClient azureClient = applicationContext.getBean(AzureClient.class);
        ControllerTest controllerTest = applicationContext.getBean(ControllerTest.class);
        ServiceTest serviceTest = applicationContext.getBean(ServiceTest.class);
        RepositoryTest repositoryTest = applicationContext.getBean(RepositoryTest.class);
        assertNotNull(azureClient);
        assertNotNull(controllerTest);
        assertNotNull(serviceTest);
        assertNotNull(repositoryTest);

        System.out.println((azureClient instanceof AzureClient));
        System.out.println((controllerTest instanceof ControllerTest));
        System.out.println((serviceTest instanceof ServiceTest));
        System.out.println((repositoryTest instanceof RepositoryTest));
        ((AbstractApplicationContext) applicationContext).close();
    }    
}
