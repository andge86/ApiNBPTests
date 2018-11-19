import org.testng.annotations.DataProvider;

public class DataProviderClass {



    @DataProvider(name = "Currencies")
    public static Object[][] CurrenciesData() {

        return new Object[][]{
                {"a", "chf", "frank szwajcarski"},
                {"a", "usd", "dolar amerykański"},
                {"a", "eur", "euro"},
                {"a", "rub", "rubel rosyjski"},
                {"a", "uah", "hrywna (Ukraina)"},

        };
    }


    @DataProvider(name = "IncorrectCurrencies")
    public static Object[][] IncorrectCurrenciesData() {

        return new Object[][]{
                {"chg", "a"},
                {"usb", "b"},
                {"eur", "r"},
                {"rub", "aa"},
                {"euro", "a"},
                {"rubrub", "a"},
                {"usd1", "euro"},
                {"1", "euro"},
                {"", ""},
                {"rub", ""},
                {"", "a"},
                {"a", "usd"}
        };
    }


}