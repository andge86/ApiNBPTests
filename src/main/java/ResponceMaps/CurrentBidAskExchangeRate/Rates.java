package ResponceMaps.CurrentBidAskExchangeRate;

public class Rates {

   private String no;
   private String effectiveDate;
   private float bid;
   private float ask;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public float getBid() {
        return bid;
    }
    public float getAsk() {
        return ask;
    }

    @Override
    public String toString(){
        return getNo() + ", "+getEffectiveDate()+", "+getBid()+", "+getAsk();
    }
}


