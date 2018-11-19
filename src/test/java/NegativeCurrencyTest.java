import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class NegativeCurrencyTest extends BaseTest {


    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "IncorrectCurrencies")
    public void incorrectCurrentAverageExchangeRateTest(String code, String table) {

        Response response = given().when().param("?format=json")
                .get(ENDPOINT + "/api/exchangerates/rates/"+table+"/"+code+"/");
        System.out.println("Response code: --> " + response.getStatusCode());
        System.out.println("Response body: --> " + response.body().asString());

        SoftAssert softAssertion= new SoftAssert();

        softAssertion.assertEquals(response.getStatusCode(), 400);
        softAssertion.assertTrue(response.body().asString().contains("400 BadRequest"));
        softAssertion.assertAll();
    }


}
