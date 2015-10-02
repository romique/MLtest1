package src.oanda;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 23.09.2015.
 */
@Service
public class OandaDataMap{
    Map<String, OandaCurrencyMap> dataMap = new HashMap<String, OandaCurrencyMap>();

    public OandaDataMap(){

    }

    public OandaCurrencyMap getCurrencyMap(String key){
        //Если запрошеной валюты уже есть в карте, то возвращаю её карту. Иначе - создаю новую пустую карту.
        if (dataMap.containsKey(key)){
            return dataMap.get(key);
        } else {
            OandaCurrencyMap oandaCurrencyMap = new OandaCurrencyMap();
            dataMap.put(key, oandaCurrencyMap);
            return oandaCurrencyMap;
        }
    }

    public Map<String, OandaCurrencyMap> getDataMap() {
        return dataMap;
    }
}
