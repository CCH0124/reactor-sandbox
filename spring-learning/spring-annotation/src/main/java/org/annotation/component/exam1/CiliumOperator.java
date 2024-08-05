package org.annotation.component.exam1;

import org.springframework.stereotype.Component;

@Component
public class CiliumOperator extends Operator {

    @Override
    public void process(String message) {
        System.out.println("Cilium CNI." + message);
    }
    
    
}
