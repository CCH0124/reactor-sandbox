package org.annotation.component.exam1;

import org.springframework.stereotype.Component;

@Component
public class FlannelOperator extends Operator {

    @Override
    public void process(String message) {
        System.out.println("Flannel CNI." + message);
    }
    
}
