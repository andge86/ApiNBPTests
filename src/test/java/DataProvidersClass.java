import org.testng.annotations.DataProvider;

public class DataProvidersClass {



    @DataProvider(name = "Currencies")
    public static Object[][] CurrenciesData() {

        return new Object[][]{
                {"chf", "frank szwajcarski"},
                {"usd", "dolar amerykański"},
                {"eur", "euro"}
        };
    }


    @DataProvider(name = "IncorrectCurrencies")
    public static Object[][] IncorrectCurrenciesData() {

        return new Object[][]{
                {"chg", "a"},
                {"eer", "c"},
                {"usb", "b"},
                {"eur", "r"},
                {"usd", "aa"},
                {"euro", "a"},
                {"eureur", "a"},
                {"usd1", "c"},
                {"1", "c"},
                {"", ""},
                {"rub", ""},
                {"", "a"},
                {"a", "usd"}
        };
    }


}