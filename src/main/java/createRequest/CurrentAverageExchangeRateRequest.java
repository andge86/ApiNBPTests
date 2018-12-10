package createRequest;

import java.util.ArrayList;
import java.util.List;

public class CurrentAverageExchangeRateRequest {

   private String table;
   private String currency;
   private String code;
   private List<RatesRequest> rates = new ArrayList<RatesRequest>();


    public void setTable(String table) {
        this.table = table;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRates(RatesRequest rates) {
        this.rates.add(rates);
    }


    public String getTable() {
        return table;
    }
    public String getCurrency() {
        return currency;
    }
    public String getCode() {
        return code;
    }
    public List<RatesRequest> getRates() {
        return rates;
    }
/*
    @Override
    public String toString(){
        return getTable() + ", "+getCurrency()+", "+getCode()+", "+getRates();
    }
*/

}
