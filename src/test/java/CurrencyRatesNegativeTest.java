import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Link;

import static io.restassured.RestAssured.given;


@Epic("Currency APIs")
@Feature("Current exchange rates")
public class CurrencyRatesNegativeTest extends BaseTest {


    @Test(dataProviderClass = DataProvidersClass.class, dataProvider = "IncorrectCurrencies",
          description = "User sends incorrect request and achieves 400 status code")
    @Story("For incorrect request user achieves 400 status code")
    @Description("User sends incorrect request and should achieve 400 status code and '400 Bad Request' body")
    @Severity(SeverityLevel.NORMAL)
    @Link("http://api.nbp.pl/en.html")
    public void incorrectCurrentAverageExchangeRateTest(String code, String table) {

        Response response = given()
                           .urlEncodingEnabled(false)
                           .filter(new AllureRestAssured())
                           .when()
                           .param("?format=json")
                           .get(ENDPOINT + "/api/exchangerates/rates/"+table+"/"+code+"/");

        SoftAssert softAssertion= new SoftAssert();

            softAssertion.assertEquals(response.getStatusCode(), 400);
            softAssertion.assertEquals(response.body().asString(), ("400 Bad Request"));
            softAssertion.assertAll();
    }
}
