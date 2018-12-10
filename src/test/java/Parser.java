import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Parser {

    private String rule;
    private LinkedHashMap<String, LinkedHashMap<String, String>> excelParsed;
    private LinkedHashMap<String, String> oneRuleMap;

    private ArrayList<String> keys = new ArrayList<String>();


    Parser (LinkedHashMap<String, LinkedHashMap<String, String>> excel, String rule) {
        this.rule = rule;
        oneRuleMap = excel.get(rule);
        excelParsed = (LinkedHashMap<String, LinkedHashMap<String, String>>) excel.clone();
        keys.add(rule);
    }

    public LinkedHashMap<String, LinkedHashMap<String, String>> getExcelParsed() {
        finalParser();
        return excelParsed;
    }


    private void finalParser(){


        if (oneRuleMap.get("type").contains("All buys") || oneRuleMap.get("type").contains("All exchanges") ||
                oneRuleMap.get("type").contains("All sells")) {

            typeParser();
        }

        if (oneRuleMap.get("accountType").contains("Not")) {

            accountTypeNotParser();
        }

    }



    private void typeParser(){

        String [] allBuysArray = {"Buy1", "Buy2"};
        String [] allExchangesArray = {"Exchange1", "Exchange2", "Exchange3"};

        LinkedHashMap<String, LinkedHashMap<String, String>> excelParsedByType =
                new LinkedHashMap<String, LinkedHashMap<String, String>>();

        String [] typesArray = oneRuleMap.get("type").split(",");

        ArrayList<String> finalTypesArray = new ArrayList<String>();

        for (String type : typesArray){

            if(type.equals("All buys")){for (String buy : allBuysArray) finalTypesArray.add(buy);}
            else if(type.equals("All exchanges")){for (String exchange : allExchangesArray) finalTypesArray.add(exchange);}
            //....
            else { System.out.println("Unknown type");}
        }

        ArrayList<String> keysNew = new ArrayList<String>();
        for (String key : keys) {
            for (String finalType : finalTypesArray) {

                LinkedHashMap<String, String> oneTypeMap = (LinkedHashMap<String, String>) oneRuleMap.clone();
                oneTypeMap.put("type", finalType);
                excelParsedByType.put(key + " " + finalType, oneTypeMap);
                keysNew.add(key + " " + finalType);
            }
        }
        keys = keysNew;
        excelParsed = excelParsedByType;
    }



    private void accountTypeNotParser(){

        String [] accountTypesArray = {"VAR","IAD","ZED","WWW"};

        LinkedHashMap<String, LinkedHashMap<String, String>> excelParsedByAccountType =
                new LinkedHashMap<String, LinkedHashMap<String, String>>();

        String [] accountNotTypesArray = oneRuleMap.get("accountType").split(",");

       ArrayList<String> finalAccountTypesArray = new ArrayList<String>();

            for (String b : accountTypesArray)
                for (String a : accountNotTypesArray){
            if(!a.contains(b)){finalAccountTypesArray.add(b);}
        }

        ArrayList<String> keysNew = new ArrayList<String>();
        for (String key : keys){
               for (String a : finalAccountTypesArray){
                   LinkedHashMap<String, String> oneTypeMap = (LinkedHashMap<String, String>) excelParsed.get(key).clone();
                   oneTypeMap.put("accountType",a);
                   excelParsedByAccountType.put(key + " " + a, oneTypeMap);
                   keysNew.add(key + " " + a);
               }
        }

        keys = keysNew;
        excelParsed = excelParsedByAccountType;
    }


}
