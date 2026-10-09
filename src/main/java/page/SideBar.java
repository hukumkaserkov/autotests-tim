package page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SideBar {

    public void LogoVisible(){
        $(byXpath("//strong[contains(text(), 'АИС ТИМ')]")).shouldBe(visible);
    }

    public void clickAppeal(){
        $(byXpath("//p[contains(text(), 'Обращения')]")).click();
    }
}
