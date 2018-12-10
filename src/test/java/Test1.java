import java.util.LinkedHashMap;

public class Test1 {


    public static void main(String[] args) {

        String rule = "Rule 1.1";

        LinkedHashMap<String, LinkedHashMap<String, String>> excel = Test.createMap();
      //  System.out.println(excel);
        Parser parser = new Parser(excel, rule);
        LinkedHashMap<String, LinkedHashMap<String, String>> excelParsed = parser.getExcelParsed();
        System.out.println(excelParsed);
    }


}

