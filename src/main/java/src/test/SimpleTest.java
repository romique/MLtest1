package src.test;

import src.strategy.ValuesBean;

/**
 * Created by Romique on 12.09.2015.
 */
public class SimpleTest implements TestInterface {

    @Override
    public String value(ValuesBean valuesBean){
        return "1";
    }
}
