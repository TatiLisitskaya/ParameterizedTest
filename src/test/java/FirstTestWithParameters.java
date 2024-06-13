import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка количества результатов в поисковом запросе")
public class FirstTestWithParameters {
    SelenideElement input = $("[data-test-id=input__search]"),
            button = $("[data-test-id=button__search]");
    ElementsCollection listProducts = $$("[data-test-id=list__products]>div");
    String baseUrl = "https://kazanexpress.ru/";
    int quantity = 10;

    @BeforeEach
    void setup() {
        open(baseUrl);
    }

    @ValueSource(strings = {
            "Корм для кошек", "Корм для собак", "Пилка для ногтей"
    })
    @ParameterizedTest(name = "В поисковой выдаче на kazanexpress должно отоброжаться 10 результатов по запросу {0}")
    void simpleWithParameters(String testData) {
        input.setValue(testData);
        button.click();
        listProducts.shouldHave(sizeGreaterThanOrEqual(quantity));
    }
}