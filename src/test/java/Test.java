import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

   static LinkedHashMap<String, LinkedHashMap<String, String>> excel;


    public static LinkedHashMap<String, LinkedHashMap<String, String>> createMap() {

        excel = new LinkedHashMap<String, LinkedHashMap<String, String>>();

        LinkedHashMap<String, String> smallMap = new LinkedHashMap<String, String>();
        smallMap.put("value", "5");
        smallMap.put("type", "All buys,All exchanges");
        smallMap.put("amount", "1000");
        smallMap.put("accountType", "Not IAD");
        excel.put("Rule 1.1", smallMap);

        LinkedHashMap<String, String> smallMap1 = new LinkedHashMap<String, String>();
        smallMap1.put("value", "6");
        smallMap1.put("type", "All sells");
        smallMap1.put("amount", "2000");
        smallMap.put("accountType", "Not IAD");
        excel.put("Rule 1.2", smallMap1);

return excel;
    }

    public static void main(String[] args) {

        String [] buys = {"INITIAL_PURCHASE", "POST_PURCHASE", "UNDER_PURCHASE"};

        Test test = new Test();
        test.createMap();
        System.out.println(test.excel);

   //     LinkedHashMap<String, String> smallMap = test.excel.get("Rule 1.1");
    //    System.out.println(smallMap);

        LinkedHashMap<String, LinkedHashMap<String, String>> excel1 = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        LinkedHashMap<String, LinkedHashMap<String, String>> excel2 = new LinkedHashMap<String, LinkedHashMap<String, String>>();
        LinkedHashMap<String, String> small2 = new  LinkedHashMap<String, String>();

        if (test.excel.get("Rule 1.1").get("type") == "All buys"){

            for (String buy : buys){
                small2 = (LinkedHashMap<String, String>) test.excel.get("Rule 1.1").clone();
                small2.put("type", buy);
                excel1.put("Rule 1.1 " + buy, small2);
            }
        }


        if (test.excel.get("Rule 1.1").get("accountType").contains("Not")){

            String [] types = test.excel.get("Rule 1.1").get("accountType").split(",");

            for (String type : types){

                for (Map.Entry<String, String> entry : excel1.get("Rule 1.1").entrySet()) {

                   if (entry.getKey() == "accountType") {

                       LinkedHashMap<String, String> small3 = (LinkedHashMap<String, String>) excel1.get("Rule 1.1").clone();
                       small3.put("accountType", type);
           //            excel2.put(contains("Rule 1.1") + buy, small3);
                   }
                }

            }


        }



        System.out.println(excel1);

    }


}