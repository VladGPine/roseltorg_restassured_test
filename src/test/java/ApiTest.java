import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ApiTest {

    @Test
    public void rosElTorgTest() {
        Document page = Steps.goToRosElTorg();
        List<Map<String, Object>> purchases = Steps.getPurchases(page);
//        System.out.println(purchases.size());
//        System.out.println("---------");
//        System.out.println(purchases.stream().filter(element -> Double.parseDouble(element.get("PRICE").toString()) < 4000000.00).count());

        purchases.stream()
                .filter(element -> Double.parseDouble(element.get("PRICE").toString()) < 4000000.00)
                .forEach(System.out::println);
    }
}
