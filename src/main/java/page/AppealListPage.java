package page;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AppealListPage {

    public void buttonCreateAppealIsVisible() {
        $(byXpath("//button[contains(text(), 'Создать обращение')]")).shouldBe(visible);
    }

    public void clickOnCreateAppeal(){
        $(byXpath("//button[contains(text(), 'Создать обращение')]")).click();
    }

    public void clickAppealNumberButton(){
        $(byXpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[1]/a"))
                .click();
    }

    public void clickCreateButton(){
        $(byXpath("/html/body/div[2]/div[3]/div/form/div[2]/button[2]")).shouldHave(exactText("Создать")).click();
    }

    public void requiredInputMessage(){
        $(byXpath("//p[contains(text(), 'Обязательное поле')]")).shouldBe(visible);
    }

    public void closeCreateAppealWindow(){
        $("body").click(usingJavaScript().offset(10, 10));
    }

    public void clickCancelButton(){
        $(byXpath("//button[contains(text(), 'Отмена')]")).click();
    }

    private String generateAppealNumberForUI() {
        return "Тест Авто UI " + System.currentTimeMillis();
    }

    public void fillAppealNumber(){
        String appealNumber = generateAppealNumberForUI();
        $(byXpath("//input[@name='number']")).setValue(appealNumber);
    }

    public void fillObjectName(){
        $(byXpath("//input[@name='objectName']")).setValue("тестовый объект авто");
    }

    public void fillObjectKind(){
        $(byXpath("/html/body/div[2]/div[3]/div/form/div[1]/div/div[3]/div/div/input")).click();
        $(byXpath("//li[contains(text(), 'Непроизводственного назначения')]")).click();
    }

    public void fillRequirementsVersion(){
        $(byXpath("/html/body/div[2]/div[3]/div/form/div[1]/div/div[4]/div/div/input")).click();
        $(byXpath("//li[contains(text(), '4.1')]")).click();
    }

    public void clickPageOne(){
        $(byXpath("//button[contains(text(), '1')]")).click();
    }

    public void clickPageTwo(){
        $(byXpath("//button[contains(text(), '2')]")).click();
    }

    public void clickPageFive(){
        $(byXpath("//button[contains(text(), '5')]")).click();
    }

    public void clickPageLast(){
        $(byXpath("//nav//ul//li[8]//button")).click();
    }

    public void clickPaginationForward(){
        $(byXpath("//nav//ul//li[9]//button")).click();
    }

    public void clickPaginationBack(){
        $(byXpath("//nav//ul//li[1]//button")).click();
    }

    public String getFirstItemText(){
        return $(byXpath("//table/tbody/tr[1]/td[1]/a")).getText();
    }
}
