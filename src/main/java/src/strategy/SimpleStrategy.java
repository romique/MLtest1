package src.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import src.test.SimpleTest;
import src.test.TestInterface;
import src.test.TestService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 14.09.2015.
 *
 */
public class SimpleStrategy implements StrategyInterface {
    private String name = "Simple";
    private ValuesBean valuesBean;
    private Map<String, TestInterface> testsMap;
    @Autowired
    TestService testService;

    @Override
    public void init(){
        testsMap = new HashMap<String, TestInterface>();
        SimpleTest simpleTest = new SimpleTest();
        testsMap.put("Simple",simpleTest);
    }

    @Override
    public String decide(){
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, TestInterface> entry : testsMap.entrySet()){
            result.append(entry.getValue().value(valuesBean));
        }
        return result.toString();
    }

    @Override
    public String process(ValuesBean valuesBean){
        this.valuesBean = valuesBean;
        return decide();
    }

    public String getName() {
        return name;
    }
}
