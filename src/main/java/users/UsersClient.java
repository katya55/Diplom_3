package users;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import static POM.EnvConfig.BASE_URL;
import static users.Client.spec;

public class UsersClient {

    @Step("Регистрация пользователя")
    public ValidatableResponse createUser(Users users) {
        return spec()
                .baseUri(BASE_URL)
                .body(users)
                .when()
                .post("api/auth/register")
                .then().log().all();
    }

    @Step("Пользователь создан")
    public String checkCreated(ValidatableResponse response) {
        return response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("accessToken");
    }

    @Step("Получение данных")
    public ValidatableResponse getDataOfUser(String accessToken) {
        return spec()
                .header("Authorization", accessToken)
                .baseUri(BASE_URL)
                .when()
                .get("api/auth/user")
                .then().log().all();

    }


    @Step("Авторизация пользователя")
    public ValidatableResponse loginCourier(Creds creds) {
        return spec()
                .body(creds)
                .when()
                .post("api/auth/login")
                .then().log().all();
    }

    @Step("Пользователь авторизовался")
    public String checkLogin(ValidatableResponse loginResponse, Users user) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", equalTo(true))
                .body("user.email", equalTo(user.getEmail()))
                .body("user.name", equalTo(user.getName()))
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        spec()
                .header("Authorization", accessToken)
                .when()
                .delete("api/auth/user")
                .then()
                .log().all()
                .statusCode(HttpURLConnection.HTTP_ACCEPTED);
    }
}

