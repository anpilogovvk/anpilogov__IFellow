package hooks;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeEach;

public class WebHooks {

    @BeforeEach
    public void beforeEach() {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.BODY), new RequestLoggingFilter(LogDetail.URI), new ResponseLoggingFilter(LogDetail.STATUS));
    }
}
