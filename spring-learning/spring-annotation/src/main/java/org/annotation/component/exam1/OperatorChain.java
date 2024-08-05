package org.annotation.component.exam1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorChain {
    // IoC 容器需要找到所有 Operator 的實現並將它們注入到這個列表中
    @Autowired
    List<Operator> operatorList;

    public void process(String message) {
        operatorList.stream().forEach(
            x -> x.process(message)
        );
    }
}
