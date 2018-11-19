import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.qameta.allure.Link;

import static io.restassured.RestAssured.given;


@Epic("Currency APIs")
@Feature("Current exchange rates")
public class NegativeCurrencyTest extends BaseTest {


    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "IncorrectCurrencies", description = "User sends incorrect request and achieves 400 status code")
    @Story("For incorrect request user achieves 400 status code")
    @Description("User sends incorrect request and should achieve 400 status code and '400 Bad Request' body")
    @Severity(SeverityLevel.NORMAL)
    @Link("http://api.nbp.pl/en.html")
    public void incorrectCurrentAverageExchangeRateTest(String code, String table) {


        Response response = given().when().param("?format=json")
                .get(ENDPOINT + "/api/exchangerates/rates/"+table+"/"+code+"/");
    //    System.out.println("Response code: --> " + response.getStatusCode());
    //    System.out.println("Response body: --> " + response.body().asString());

        SoftAssert softAssertion= new SoftAssert();

            softAssertion.assertEquals(response.getStatusCode(), 400, "Returned incorrect status code: ");
            softAssertion.assertEquals(response.body().asString(), ("400 Bad Request"), "Returned incorrect response body: ");
            softAssertion.assertAll();

    }


}
