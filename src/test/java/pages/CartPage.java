package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    private By cartItems = By.className("cart_item");
    private By cartItemNames = By.className("inventory_item_name");
    private By removeButtons = By.cssSelector("button[class*='cart_button']");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By cartBadge = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public List<WebElement> getCartItemNames() {
        return driver.findElements(cartItemNames);
    }

    public String getFirstItemName() {
        return driver.findElements(cartItemNames).get(0).getText();
    }

    public void removeFirstItem() {
        driver.findElements(removeButtons).get(0).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public boolean isAt() {
        try {
            return driver.findElement(checkoutButton).isDisplayed() ||
                   driver.findElements(cartItems).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCartBadgeText() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0";
        }
    }
}

