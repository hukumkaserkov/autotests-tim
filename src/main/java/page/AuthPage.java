package page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    public AuthPage fillLogin(String login){
        $(byXpath("//input[@name=\"username\"]")).setValue(login);
        return this;
    }

    public AuthPage fillPassword(String password){
        $(byXpath("//input[@name=\"password\"]")).setValue(password);
        return this;
    }

    public void clickSubmit(){
        $(byXpath("//button[contains(text(), 'Войти')]")).click();
    }

    public void messageIncorrect(){
        $(byXpath("//div[contains(text(), 'Не удалось войти. Неверный логин или пароль')]"))
                .shouldBe(visible);
    }
}
