package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.oanda.OandaCurrencyMap;
import src.oanda.OandaDataMap;
import src.oanda.OandaDataService;
import src.oanda.OandaPriceBean;
import src.strategy.StrategyService;
import src.strategy.ValuesBean;
import src.test.DivPriceBeanTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    StrategyService strategyService;

    @Autowired
    OandaDataService oandaDataService;

    @Autowired
    OandaDataMap oandaDataMap;

    @Autowired
    DivPriceBeanTest divPriceBeanTest;

    @RequestMapping("/simple")
    public String simpleStrategy(@RequestParam(value="values", required=false) String values,
                                 @RequestParam(value="separator", required=true, defaultValue=",") String separator,
                                 @RequestParam(value="autoAssignKeys", required=true, defaultValue = "0") String autoAssignKeys) throws IOException{

        if ("0".equals(autoAssignKeys)) {
            ValuesBean valuesBean = new ValuesBean(values, separator);
            return strategyService.getStrategy("Simple").process(valuesBean);
        } else {
            ValuesBean valuesBean = new ValuesBean(values, separator, autoAssignKeys);
            return strategyService.getStrategy("Simple").process(valuesBean);
        }
    }
    @RequestMapping("/oanda")
    public String oandaTest(@RequestParam(value="values", required=true) String values,
                            @RequestParam(value="separator", required = true, defaultValue=",") String separator) throws IOException{
        ValuesBean valuesBean = new ValuesBean(values, separator);
        StringBuilder url = new StringBuilder();
        url.append("prices?");
        for (Map.Entry<String, String> entry : valuesBean.entrySet()){
            url.append(entry.getKey());
            url.append("=");
            url.append(entry.getValue());
        }
        return oandaDataService.parsePrices(oandaDataService.getMethodResult(url.toString())).toString();
    }

    @RequestMapping("/add")
    public String addValue(@RequestParam(value="instrument") String instrument) throws IOException{
        OandaPriceBean opb = oandaDataService.getCurrentPrice(instrument);
        oandaDataMap.getCurrencyMap(instrument).put(opb);
        return opb.toString();
    }

    @RequestMapping(value="/getdatamap", produces="application/json; charset=UTF-8")
    public OandaDataMap getDataMap(){
        return oandaDataMap;
    }

    @RequestMapping("/getcurrencymap")
    public OandaCurrencyMap getCurrencyMap(@RequestParam(value="instrument") String instrument){
        return oandaDataMap.getCurrencyMap(instrument);
    }

    @RequestMapping("/divstrategy")
    public String getDecision(@RequestParam(value="currency") String currency) throws ParseException{
        return divPriceBeanTest.decision(currency);
    }
}