package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;

    private By productsTitle = By.className("title");
    private By productsContainer = By.className("inventory_container");
    private By productNames = By.className("inventory_item_name");
    private By addToCartButtons = By.cssSelector("button[class*='btn_inventory']");
    private By cartIcon = By.className("shopping_cart_link");
    private By sortDropdown = By.className("product_sort_container");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAt() {
        try {
            return driver.findElement(productsTitle).isDisplayed() &&
                   driver.findElement(productsContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(productNames);
    }

    public String getFirstProductName() {
        return driver.findElements(productNames).get(0).getText();
    }

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    public void addProductByIndexToCart(int index) {
        driver.findElements(addToCartButtons).get(index).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    public void sortProducts(String sortOption) {
        driver.findElement(sortDropdown).click();
        driver.findElement(By.cssSelector("option[value='" + sortOption + "']")).click();
    }

    public String getSortOptionValue() {
        WebElement sortElement = driver.findElement(sortDropdown);
        return sortElement.getAttribute("value");
    }

    public int getProductCount() {
        return driver.findElements(productNames).size();
    }

    public void removeProductFromCart(int index) {
        driver.findElements(By.cssSelector("button[class*='btn_inventory'][class*='cart']")).get(index).click();
    }

    public String getCartBadgeText() {
        try {
            return driver.findElement(By.className("shopping_cart_badge")).getText();
        } catch (Exception e) {
            return "0";
        }
    }
}

