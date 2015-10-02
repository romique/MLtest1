package src.test;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 12.09.2015.
 */
@Service
public class TestService {
    private Map<String, TestInterface> tests;
    private boolean isInitialized = false;
    private Integer testCounter = 0;

    public TestService(){
        if (!isInitialized) {
            tests = new HashMap<String, TestInterface>();
            isInitialized = true;
            SimpleTest simpleTest = new SimpleTest();
            this.add("Simple", simpleTest);
            testCounter=1;
        } else {
            System.out.println("TestService already initialized");
        }
    }

    public String add(String key, TestInterface test){
        if (!isInitialized){
            System.out.println("TestService is not initialized");
            return null;
        } else {
            tests.put(key, test);
            testCounter++;
            return key;
        }
    }

    public String add(TestInterface test){
        return add(testCounter.toString(), test);
    }

    public TestInterface get(String key){
        if (!isInitialized){
            System.out.println("Test Service is not initialized");
            return null;
        } else {
            return tests.get(key);
        }
    }

    public Map<String, TestInterface> getTests() {
        return tests;
    }

}
