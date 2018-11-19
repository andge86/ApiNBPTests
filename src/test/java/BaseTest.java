import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {


    final static String ENDPOINT = "http://api.nbp.pl";




    @Step("Achieved date in required format")
    public static String currentDate(String formatPattern){

        DateFormat dateFormat = new SimpleDateFormat(formatPattern); //"yyyy-MM-dd"
        Date date = new Date();

        return dateFormat.format(date);
    }

}
