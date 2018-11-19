public class Rates {

   private String no;
   private String effectiveDate;
   private String mid;

    public String getNo() {
        return no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public String getMid() {
        return mid;
    }

    @Override
    public String toString(){
        return getNo() + ", "+getEffectiveDate()+", "+getMid();
    }
}


