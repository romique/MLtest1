package src.oanda;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.*;
import org.jsoup.Connection;
import org.springframework.stereotype.Service;
import src.strategy.ValuesBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Romique on 20.09.2015.
 *
 * login = g10947899
 * email = g10947899@trbvm.com
 * password = 123qwe123
 * url = https://fxtrade.oanda.com/demo-account/login
 * key = f1c91b3dc39a9a9f35c597243de02959-4e87058b0c4d4ccad4a8a14bde8d3db2
 */
@Service
public class OandaDataService {

    private final static String sandboxURL = "http://api-sandbox.oanda.com/v1/";
    private final static String practiceURL = "https://api-fxpractice.oanda.com/v1/";
    private final static String login = "g10947899";
    private final static String password = "123qwe123";
    private final static String email = "g10947899@trbvm.com";
    private final static String APIkey = "f1c91b3dc39a9a9f35c597243de02959-4e87058b0c4d4ccad4a8a14bde8d3db2";
    private static ObjectMapper mapper = new ObjectMapper();

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject getMethodResult(String method) throws IOException {
        URL url = new URL(sandboxURL + method);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsontext = readAll(reader);
        JSONObject json = new JSONObject(jsontext);
        return json;
    }
    public static Map<String, ValuesBean> parsePrices(JSONObject jsonObject){
        JSONArray prices = jsonObject.getJSONArray("prices");
        Map<String, ValuesBean> m = new HashMap<String, ValuesBean>();
        String name;
        for (int i=0; i<prices.length(); i++){
            name = prices.getJSONObject(i).getString("instrument");
            StringBuilder values = new StringBuilder();
            values.append("time=");
            values.append(prices.getJSONObject(i).getString("time"));
            values.append(",ask=");
            values.append(prices.getJSONObject(i).getDouble("ask"));
            values.append(",bid=");
            values.append(prices.getJSONObject(i).getDouble("bid"));
            m.put(name,new ValuesBean(values.toString(),","));
        }
        return m;
    }

    public OandaPriceBean getCurrentPrice(String instrument) throws IOException{
//        URL url = new URL(sandboxURL+"prices?instruments="+instrument);
        URL url = new URL(practiceURL+"prices?instruments="+instrument);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer "+APIkey);
        InputStream is = connection.getInputStream();
        JsonNode arrayNode = mapper.readTree(is).get("prices");
        //Можно читать несколько instruments и считывать сразу Map цен, распихивая по соответствующим ключам OandaCurrencyMap
        OandaPriceBean oandaPriceBean = mapper.readValue(arrayNode.get(0), OandaPriceBean.class);
        return oandaPriceBean;
    }
/*
    public static void main(String[] args) throws IOException{
        System.out.println(getCurrentPrice("EUR_USD").toString());
    }
*/

}
