package src.strategy;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 14.09.2015.
 */
@Service
public class StrategyService {
    private Map<String, StrategyInterface> strategyInterfaceMap;
    private boolean isInitialized = false;

    public StrategyService() {
        if (!isInitialized) {
            strategyInterfaceMap = new HashMap<String, StrategyInterface>();
            isInitialized = true;
            SimpleStrategy simpleStrategy = new SimpleStrategy();
            simpleStrategy.init();
            addStrategy("Simple", simpleStrategy);
        } else {
            System.out.println("StrategyService already initialized");
        }
    }

    public String addStrategy(String name, StrategyInterface strategy){
        if (isInitialized){
            strategyInterfaceMap.put(name,strategy);
            strategyInterfaceMap.get(name).init();
            return "Strategy " + name + " added";
        } else {
            return "StrategyService is not initialized";
        }
    }

    public StrategyInterface getStrategy(String name){
        if (isInitialized) {
            if (strategyInterfaceMap.containsKey(name)) {
                return strategyInterfaceMap.get(name);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
