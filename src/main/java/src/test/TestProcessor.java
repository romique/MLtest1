package src.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.strategy.ValuesBean;

/**
 * Created by Romique on 12.09.2015.
 */

@Service
public class TestProcessor {
    private boolean isInitialized = false;
    @Autowired
    ParamService paramService;


    public TestProcessor(){
        if (!isInitialized) {
            isInitialized = true;
        } else {
            System.out.println("TestProcessor is already initialized");
        }
    }

    public String process(TestInterface test, ValuesBean valuesBean){
        if (!isInitialized) {
            System.out.println("TestProcessor is not initialized");
            return null;
        } else {
            return test.value(valuesBean);
        }
    }
}
