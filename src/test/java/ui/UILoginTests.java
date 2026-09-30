package ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.*;

import static com.codeborne.selenide.Selenide.*;


public class UILoginTests {
    AuthPage authPage = new AuthPage();
    AppealListPage appealListPage = new AppealListPage();
    AppealCardPage appealCardPage = new AppealCardPage();
    SideBar sideBar = new SideBar();

    @BeforeAll
    public static void setUpAll() {
        // Устанавливаем размер окна 1920x1080
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void closeBrowser() {
        // Закрываем браузер после каждого теста
        closeWebDriver();
    }

    @Test
    public void AuthTestPositive(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.LogoVisible();
    }

    @Test
    public void AuthTestIncorrectLogin(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("ABCDEF").fillPassword("EXPERT").clickSubmit();
        authPage.messageIncorrect();
    }

    @Test
    public void AuthTestIncorrectPassword(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("12345").clickSubmit();
        authPage.messageIncorrect();
    }

    @Test
    public void ReturnInAppealAfterLogOut(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.clickAppeal();
        appealListPage.clickAppealNumberButton();
        appealCardPage.logOut();
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        appealCardPage.AppealHeader();
    }

}
