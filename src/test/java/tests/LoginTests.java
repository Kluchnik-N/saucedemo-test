package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTests extends BaseTest {

    @Test(description = "Позитивный сценарий: успешный вход стандартного пользователя")
    public void testSuccessfulLogin() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        ProductsPage products = new ProductsPage(driver);
        Assert.assertTrue(products.isAt(), "Ожидаем страницу Products после логина");
    }

    @Test(description = "Негативный сценарий: вход с неверным паролем")
    public void testLoginWithWrongPassword() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "wrong_password");
        
        Assert.assertTrue(login.isErrorMessageDisplayed(),
            "Ожидаем сообщение об ошибке при неверном пароле");
        
        String errorMessage = login.getErrorMessage();
        Assert.assertFalse(errorMessage.isEmpty(), 
            "Текст ошибки не должен быть пустым");
    }

    @Test(description = "Негативный сценарий: вход с пустыми полями")
    public void testLoginWithEmptyFields() {
        LoginPage login = new LoginPage(driver);
        login.open();
        
        login.clickLogin();
        Assert.assertTrue(login.isErrorMessageDisplayed(),
            "Ожидаем сообщение об ошибке при пустых полях");
        Assert.assertFalse(driver.getCurrentUrl().contains("/inventory"), 
            "Не должны быть на странице продуктов");
    }
}

