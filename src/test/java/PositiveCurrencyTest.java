
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class PositiveCurrencyTest extends BaseTest{



// Current average CHF exchange rate
// http://api.nbp.pl/api/exchangerates/rates/a/chf/?format=json

@Test (dataProviderClass = DataProviderClass.class, dataProvider = "Currencies")
    public void currentAverageExchangeRateTest(String table, String code, String currency) throws IOException {


    Response response = given().when().param("?format=json")
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


}
