package io.github.rsshekhawat.Test_APIs;

import io.github.rsshekhawat.POJO.Request.BookingDatesPayload;
import io.github.rsshekhawat.POJO.Request.CreateTokenPayload;
import io.github.rsshekhawat.POJO.Request.BookingPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class BookingTests extends APITestUtilities{

    String authToken;
    @BeforeClass
    public void initialize() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void getAuthToken() {

        CreateTokenPayload createTokenPayload = new CreateTokenPayload("admin", "password123");
        String token_payload = serializeObject(createTokenPayload);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(token_payload)
                .when()
                .post("/auth")
                .then()
                .extract().response();

        authToken = getJsonString(response.asString(), "$.token");
        System.out.println("Authentication Token : "+authToken);
    }

    @Parameters({"firstname", "lastname", "totalPrice", "depositPaid", "additionalNeeds", "checkIn", "checkOut"})
    @Test
    public void createBooking(String firstname, String lastname, int totalPrice, boolean depositPaid, String additionalNeeds, String checkIn, String checkOut) {

        BookingDatesPayload datesPayload = new BookingDatesPayload(checkIn, checkOut);
        BookingPayload createBookingPayload = new BookingPayload(firstname, lastname, totalPrice, depositPaid, datesPayload, additionalNeeds);
        String create_booking_payload = serializeObject(createBookingPayload);

        Response response = given()
               .contentType(ContentType.JSON)
                .body(create_booking_payload)
                .when()
                .post("/booking")
                .then()
                .extract().response();

        System.out.println(response.asString());
    }

    @Parameters({"id", "firstname", "lastname", "totalPrice", "depositPaid", "additionalNeeds", "checkIn", "checkOut"})
    @Test
    public void updateBooking(int id, String firstname, String lastname, int totalPrice, boolean depositPaid, String additionalNeeds, String checkIn, String checkOut) {

        BookingDatesPayload datesPayload = new BookingDatesPayload(checkIn, checkOut);
        BookingPayload updateBookingPayload = new BookingPayload(firstname, lastname, totalPrice, depositPaid, datesPayload, additionalNeeds);
        String update_booking_payload = serializeObject(updateBookingPayload);

        Response response = given()
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .contentType(ContentType.JSON)
                .body(update_booking_payload)
                .when()
                .put("/booking/"+id)
                .then()
                .extract().response();

        System.out.println(response.asString());
    }
}
