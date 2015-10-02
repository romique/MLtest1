package src.oanda;

/**
 * Created by Romique on 20.09.2015.
 */
public class OandaPriceBean {
    private String instrument;
    private String time;
    private String ask;
    private String bid;

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String toString(){
        return "[Time = "+time+"; instrument = "+instrument+"; ask = "+ask+"; bid = "+bid+"]";
    }
}
