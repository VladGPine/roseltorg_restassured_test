import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Steps {

    @Step
    public static Document goToRosElTorg() {
        Response response = given()
                .when()
                .get("https://www.roseltorg.ru/search/44fz")
                .then()
//                .log().body()
                .statusCode(200)
                .extract()
                .response();

        Document page = Jsoup.parse(response.asString());
        return page;
    }

    @Step
    public static List<Map<String, Object>> getPurchases(Document page) {
        List<Map<String, Object>> purchaseListMap = new ArrayList<Map<String, Object>>();
        List<Element> purchaseList = page.body().getElementsByClass("search-results__item");

        for (Element element: purchaseList) {
            purchaseListMap.add(Map.of(
//                    "ELEMENT", element,
                    "PURCHASE_NUMBER", element
                            .getElementsByClass("search-results__link").get(0)
                            .text().substring(0, 19),
                    "PRICE", element.getElementsByClass("search-results__sum").get(0).child(0)
                            .text().replace(",", ".").replace(" ", "")
            ));
        }
        return purchaseListMap;
    }
}
