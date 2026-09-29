package page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AppealCardPage {

    public void clickProfileLogo(){
        $(byXpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div/button")).click();
    }

    public void logOut(){
        clickProfileLogo();
        $(byXpath("//button[contains(text(), 'Выйти')]")).click();
    }

    public void AppealHeader(){
        $(byXpath("//h1[contains(text(), 'Обращение №')]")).shouldBe(visible);
    }
}
