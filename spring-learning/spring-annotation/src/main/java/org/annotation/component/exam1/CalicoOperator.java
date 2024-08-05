package org.annotation.component.exam1;

import org.springframework.stereotype.Component;

@Component
public class CalicoOperator extends Operator {

    @Override
    public void process(String message) {
        System.out.println("Calico CNI." + message);
        
    }
    
}
