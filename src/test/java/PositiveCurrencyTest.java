
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import createRequest.Country;
import createRequest.CurrentAverageExchangeRateRequest;
import createRequest.RatesRequest;
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
public class PositiveCurrencyTest extends BaseTest{



// Current average CHF exchange rate
// http://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json

@Test (dataProviderClass = DataProviderClass.class, dataProvider = "Currencies", description = "User should achieve exchange rate for current date")
@Severity(SeverityLevel.CRITICAL)
@Link("http://api.nbp.pl/en.html")
    public void currentAverageExchangeRateTest(String table, String code, String currency) throws IOException {


    Response response = given()
                       .urlEncodingEnabled(false)
                       .filter(new AllureRestAssured())
                       .when()
                       .param("?format=json")
                       .get(ENDPOINT + "/api/exchangerates/rates/"+table+"/"+code+"/");

    System.out.println(response.body().asString());
    ObjectMapper objectMapper = new ObjectMapper();
    CurrentAverageExchangeRate currentAverageExchangeRate = objectMapper.readValue(response.body().asString(), CurrentAverageExchangeRate.class);

    Assert.assertEquals(response.getStatusCode(), 200);

    SoftAssert softAssertion= new SoftAssert();

    softAssertion.assertEquals(currentAverageExchangeRate.getTable(), table.toUpperCase());
    softAssertion.assertEquals(currentAverageExchangeRate.getCode(), code.toUpperCase());
    softAssertion.assertEquals(currentAverageExchangeRate.getCurrency(), currency);
    softAssertion.assertEquals(currentAverageExchangeRate.getRates().get(0).getEffectiveDate(), currentDate("yyyy-MM-dd"));
    softAssertion.assertAll();

}

@Test
    public void createJsonTest() throws JsonProcessingException {

    CurrentAverageExchangeRateRequest currentAverageExchangeRateRequest = new CurrentAverageExchangeRateRequest();

    RatesRequest ratesRequest = new RatesRequest();
    Country country = new Country();
    country.setCode(840);
    country.setName("USA");

    Country country2 = new Country();
    country2.setCode(980);
    country2.setName("Ukraine");

ratesRequest.setEffectiveDate("24.11.1986");
ratesRequest.setNo("25/NPB/123");
ratesRequest.setMid("2.345");
ratesRequest.setCountry(country);
ratesRequest.setCountry(country2);

currentAverageExchangeRateRequest.setTable("A");
currentAverageExchangeRateRequest.setCode("USD");
currentAverageExchangeRateRequest.setCurrency("american dollar");
currentAverageExchangeRateRequest.setRates(ratesRequest);


    ObjectMapper objectMapper = new ObjectMapper();

    Object json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentAverageExchangeRateRequest);
    System.out.println(json);
}


}
