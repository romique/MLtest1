package src.test;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 12.09.2015.
 */
@Service
public class ParamService {
    private Map<String, ParamBean> paramList;
    private boolean isInitialized = false;

    public ParamService(){
        if (!isInitialized) {
            paramList = new HashMap<String, ParamBean>();
            isInitialized = true;
        } else {
            System.out.println("ParamService already is initialized");
        }
    }

    public void add(String paramName, ParamBean paramBean){
        if (!isInitialized) {
            System.out.println("ParamService is not initialized");
        } else {
            if (paramList.containsKey(paramName)){
                System.out.println("Already exists param with key" + paramName);
            } else {
                paramList.put(paramName, paramBean);
            }
        }
    }

    public ParamBean get(String paramName){
        if (!isInitialized){
            System.out.println("ParamService is not initialized");
            return null;
        } else {
            if (paramList.get(paramName) != null){
                return paramList.get(paramName);
            } else {
                System.out.println("Param " + paramName + " is not found");
                return null;
            }
        }
    }

    public Map<String, ParamBean> getParamList() {
        return paramList;
    }
}
