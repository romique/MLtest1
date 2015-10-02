package src.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.oanda.OandaCurrencyMap;
import src.oanda.OandaDataMap;
import src.oanda.OandaPriceBean;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Romique on 28.09.2015.
 */
@Service
public class DivPriceBeanTest {
    private double threshold;
    final static String dateformat = "yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'";
    @Autowired
    OandaDataMap oandaDataMap;

    public DivPriceBeanTest(){
        threshold = 0;
    }

    private double value(double[] x, double[] y){
        int size = x.length - 1;
        try {
            return (y[size] - y[size-1])/(x[size]-x[size-1]);
        } catch (Throwable t) {
            System.out.println("Can't evaluate value");
            return 0;
        }
    }

    public String decision(String currency) throws ParseException{
        OandaCurrencyMap oandaCurrencyMap = oandaDataMap.getCurrencyMap(currency);
        double[] x = new double[oandaCurrencyMap.size()];
        double[] y = new double[oandaCurrencyMap.size()];
        int i = 0;
        String key;
        OandaPriceBean oandaPriceBean;
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        for(Map.Entry<String, OandaPriceBean> entry : oandaCurrencyMap.entrySet()){
            key = entry.getKey();
            oandaPriceBean = entry.getValue();
            x[i] = sdf.parse(key).getTime();
            y[i] = Double.parseDouble(oandaPriceBean.getAsk());
            i++;
        }
        if (value(x,y) > threshold){
            return "BUY";
        } else {
            return "SELL";
        }
    }
}
