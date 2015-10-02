package src.oanda;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.TreeMap;

/**
 * Created by Romique on 23.09.2015.
 */
public class OandaCurrencyMap extends TreeMap<String, OandaPriceBean>{
    @Autowired
    OandaDataMap oandaDataMap;

    public OandaPriceBean put(OandaPriceBean oandaPriceBean){
        this.put(oandaPriceBean.getTime(), oandaPriceBean);
        System.out.println("PriceBean added: "+oandaPriceBean.toString());
        return oandaPriceBean;
    }

}
