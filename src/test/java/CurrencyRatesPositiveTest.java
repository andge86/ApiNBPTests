import ResponceMaps.CurrentAvarageExchangeRate.CurrentAverageExchangeRate;
import ResponceMaps.CurrentBidAskExchangeRate.CurrentBidAskExchangeRate;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static io.restassured.RestAssured.*;

@Epic("Currency APIs")
@Feature("Current exchange rates")
public class CurrencyRatesPositiveTest extends BaseTest {


@Test (dataProviderClass = DataProvidersClass.class, dataProvider = "Currencies",
       description = "User should achieve exchange rate for current date for passed currency with table = a" +
               " (table A of middle exchange rates of foreign currencies)")
@Story("User should be able to achieve average price of passed currency")
@Severity(SeverityLevel.CRITICAL)
@Link("http://api.nbp.pl/en.html")
    public void currentAverageExchangeRateTest(String code, String currency) throws IOException {

    final String TABLE = "a"; //table A - middle exchange rates of foreign currencies

    Response response = given()
                       .urlEncodingEnabled(false)
                       .filter(new AllureRestAssured())
                       .when()
                       .param("?format=json")
                       .get(ENDPOINT + "/api/exchangerates/rates/"+TABLE+"/"+code+"/");

    ObjectMapper objectMapper = new ObjectMapper();
    CurrentAverageExchangeRate currentAverageExchangeRate =
            objectMapper.readValue(response.body().asString(), CurrentAverageExchangeRate.class);

    Assert.assertEquals(response.getStatusCode(), 200);

    SoftAssert softAssertion= new SoftAssert();
    softAssertion.assertEquals(currentAverageExchangeRate.getTable(), TABLE.toUpperCase());
    softAssertion.assertEquals(currentAverageExchangeRate.getCode(), code.toUpperCase());
    softAssertion.assertEquals(currentAverageExchangeRate.getCurrency(), currency);
    softAssertion.assertEquals(currentAverageExchangeRate.getRates().get(0).getEffectiveDate(), currentDate("yyyy-MM-dd"));

    softAssertion.assertTrue(currentAverageExchangeRate.getRates().get(0).getMid() > 0);

    softAssertion.assertAll();
}



    @Test (dataProviderClass = DataProvidersClass.class, dataProvider = "Currencies",
            description = "User should achieve exchange rate for current date for passed currency with table = c" +
                    " (table C of buy and sell prices of foreign currencies)")
    @Story("User should be able to achieve buy and sell prices of passed currency")
    @Severity(SeverityLevel.CRITICAL)
    @Link("http://api.nbp.pl/en.html")
    public void currentBidAskExchangeRateTest(String code, String currency) throws IOException {

        final String TABLE = "c"; //table C - buy and sell prices of foreign currencies

        Response response = given()
                .urlEncodingEnabled(false)
                .filter(new AllureRestAssured())
                .when()
                .param("?format=json")
                .get(ENDPOINT + "/api/exchangerates/rates/"+TABLE+"/"+code+"/");

        ObjectMapper objectMapper = new ObjectMapper();
        CurrentBidAskExchangeRate currentBidAskExchangeRate =
                objectMapper.readValue(response.body().asString(), CurrentBidAskExchangeRate.class);

        Assert.assertEquals(response.getStatusCode(), 200);

        SoftAssert softAssertion= new SoftAssert();
        softAssertion.assertEquals(currentBidAskExchangeRate.getTable(), TABLE.toUpperCase());
        softAssertion.assertEquals(currentBidAskExchangeRate.getCode(), code.toUpperCase());
        softAssertion.assertEquals(currentBidAskExchangeRate.getCurrency(), currency);
        softAssertion.assertEquals(currentBidAskExchangeRate.getRates().get(0).getEffectiveDate(), currentDate("yyyy-MM-dd"));

        softAssertion.assertTrue(currentBidAskExchangeRate.getRates().get(0).getBid() > 0);
        softAssertion.assertTrue(currentBidAskExchangeRate.getRates().get(0).getAsk() > 0);

        softAssertion.assertAll();
    }

}
