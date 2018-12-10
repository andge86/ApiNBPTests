package createRequest;

import java.util.ArrayList;
import java.util.List;

public class RatesRequest {

   private String no;
   private String effectiveDate;
   private String mid;
    private List<Country> country = new ArrayList<Country>();

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country.add(country);
    }



    public String getNo() {
        return no;
    }
    public String getEffectiveDate() {
        return effectiveDate;
    }
    public String getMid() {
        return mid;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }

/*    @Override
    public String toString(){
        return getNo() + ", "+getEffectiveDate()+", "+getMid();
    }
    */
}


