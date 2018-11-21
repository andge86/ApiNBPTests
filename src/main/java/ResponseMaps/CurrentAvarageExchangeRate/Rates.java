package ResponseMaps.CurrentAvarageExchangeRate;

public class Rates {

   private String no;
   private String effectiveDate;
   private float mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public float getMid() {
        return mid;
    }

    @Override
    public String toString(){
        return getNo() + ", "+getEffectiveDate()+", "+getMid();
    }
}


