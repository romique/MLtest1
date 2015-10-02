package src.strategy;

import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by Romique on 14.09.2015.
 */
public class ValuesBean extends HashMap<String, String> {

    public ValuesBean(String values, String separator){
        if (values.contains(separator)){
            String[] valueArray = values.split(separator);
            for (String value : valueArray){
                this.put(value.split("=")[0], value.split("=")[1]);
            }
        } else if (values.contains("=")) {
            this.put(values.split("=")[0], values.split("=")[1]);
        } else {
            this.put("1", values);
        }
    }

    public ValuesBean(String values, String separator, String autoAssignKeys){
        Integer i = 1;
        if (values.contains(separator)){
            String[] valueArray = values.split(separator);
            for (String value : valueArray){
                this.put(i.toString(), value);
                i++;
            }
        } else {
            this.put(i.toString(), values);
        }
    }
}
