package ResponseMaps.CurrentBidAskExchangeRate;

import java.util.ArrayList;
import java.util.List;

public class CurrentBidAskExchangeRate {

   private String table;
   private String currency;
   private String code;
   private List<Rates> rates = new ArrayList<Rates>();

    public String getTable() {
        return table;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public List<Rates> getRates() {
        return rates;
    }

    @Override
    public String toString(){
        return getTable() + ", "+getCurrency()+", "+getCode()+", "+getRates();
    }


}
