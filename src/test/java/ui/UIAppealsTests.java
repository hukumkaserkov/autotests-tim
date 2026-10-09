package ui;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import page.AppealCardPage;
import page.AppealListPage;
import page.AuthPage;
import page.SideBar;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UIAppealsTests {

    AuthPage authPage = new AuthPage();
    SideBar sideBar = new SideBar();
    AppealListPage appealListPage = new AppealListPage();
    AppealCardPage appealCardPage = new AppealCardPage();

    @BeforeAll
    public static void setUpAll() {
        // Устанавливаем размер окна 1690x920
        Configuration.browserSize = "1690x920";
    }

    @AfterEach
    void closeBrowser() {
        // Закрываем браузер после каждого теста
        closeWebDriver();
    }

    @Test
    @Description("Проверка создания обращения без заполненных обязательных полей " +
            "и закрытие модалки кликом вне области модалки")
    public void createAppealWithNoData() {
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.clickAppeal();
        appealListPage.clickOnCreateAppeal();
        appealListPage.clickCreateButton();
        appealListPage.requiredInputMessage();
        appealListPage.closeCreateAppealWindow();
        // actions().sendKeys(ESCAPE).perform(); - можно еще кликнуть escape для закрытия модалки
        appealListPage.buttonCreateAppealIsVisible();
    }

    @Test
    @Description("Проверка закрытия модалки Создания обращения кнопкой Отмена")
    public void clickCancelInModalWindow(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.clickAppeal();
        appealListPage.clickOnCreateAppeal();
        appealListPage.clickCancelButton();
        appealListPage.buttonCreateAppealIsVisible();
    }

    @Test
    @Description("Создание обращения")
    public void createAppeal(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.clickAppeal();
        appealListPage.clickOnCreateAppeal();
        appealListPage.fillAppealNumber();
        appealListPage.fillObjectName();
        appealListPage.fillObjectKind();
        appealListPage.fillRequirementsVersion();
        appealListPage.clickCreateButton();
        appealCardPage.AppealHeader();
    }

    @Test
    @Description("Проверка пагинации в реестре Обращений")
    public void checkPagination(){
        open("https://tim-develop.d8dev.mgexp.org/");
        authPage.fillLogin("EXPERT").fillPassword("EXPERT").clickSubmit();
        sideBar.clickAppeal();
        String firstItemBefore = appealListPage.getFirstItemText();
        appealListPage.clickPageTwo();
        sleep(800);
        String firstItemAfter = appealListPage.getFirstItemText();
        assertNotEquals(firstItemBefore, firstItemAfter, "Элемент не изменился");
        firstItemBefore = firstItemAfter;
        appealListPage.clickPageFive();
        sleep(800);
        firstItemAfter = appealListPage.getFirstItemText();
        assertNotEquals(firstItemBefore, firstItemAfter, "Элемент не изменился");
        firstItemBefore = firstItemAfter;
        appealListPage.clickPageLast();
        sleep(800);
        firstItemAfter = appealListPage.getFirstItemText();
        assertNotEquals(firstItemBefore, firstItemAfter, "Элемент не изменился");
        firstItemBefore = firstItemAfter;
        appealListPage.clickPaginationBack();
        sleep(800);
        firstItemAfter = appealListPage.getFirstItemText();
        assertNotEquals(firstItemBefore, firstItemAfter, "Элемент не изменился");
        firstItemBefore = firstItemAfter;
        appealListPage.clickPaginationForward();
        sleep(800);
        firstItemAfter = appealListPage.getFirstItemText();
        assertNotEquals(firstItemBefore, firstItemAfter, "Элемент не изменился");
    }

}
