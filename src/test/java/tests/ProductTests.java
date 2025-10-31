package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class ProductTests extends BaseTest {

    @Test(description = "Добавление товара в корзину")
    public void testAddProductToCart() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        
        ProductsPage products = new ProductsPage(driver);
        String firstProductName = products.getFirstProductName();
        products.addFirstProductToCart();
        
        String cartBadge = products.getCartBadgeText();
        Assert.assertEquals(Integer.parseInt(cartBadge), 1, 
            "В корзине должен быть 1 товар");
        
        products.clickCartIcon();
        CartPage cart = new CartPage(driver);
        Assert.assertTrue(cart.isAt(), "Должна открыться страница корзины");
        String cartProductName = cart.getFirstItemName();
        Assert.assertEquals(cartProductName, firstProductName, 
            "Название товара в корзине должно совпадать с добавленным");
    }

    @Test(description = "Покупка: добавить товар, оформить заказ и завершить")
    public void testCompletePurchase() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        
        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.clickCartIcon();
        
        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getCartItemsCount(), 1, "В корзине должен быть 1 товар");
        cart.clickCheckout();
        
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillCheckoutInfo("John", "Doe", "12345");
        checkout.clickContinue();
        checkout.clickFinish();
        
        Assert.assertTrue(checkout.isOrderComplete(), "Заказ должен быть завершен");
        Assert.assertTrue(checkout.getCompleteHeader().contains("Thank you"), 
            "Должно быть сообщение об успешном заказе");
    }

    @Test(description = "Добавление нескольких товаров в корзину")
    public void testAddMultipleProductsToCart() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        
        ProductsPage products = new ProductsPage(driver);
        products.addFirstProductToCart();
        products.addProductByIndexToCart(1);
        products.addProductByIndexToCart(2);
        
        String cartBadge = products.getCartBadgeText();
        Assert.assertEquals(Integer.parseInt(cartBadge), 3, 
            "В корзине должно быть 3 товара");
        
        products.clickCartIcon();
        CartPage cart = new CartPage(driver);
        int itemsCount = cart.getCartItemsCount();
        Assert.assertEquals(itemsCount, 3, "В корзине должно быть 3 товара");
    }

    @Test(description = "Сортировка товаров по цене от низкой к высокой")
    public void testSortProductsByPrice() {
        LoginPage login = new LoginPage(driver);
        login.open();
        login.login("standard_user", "secret_sauce");
        
        ProductsPage products = new ProductsPage(driver);
        
        String initialSortValue = products.getSortOptionValue();
        products.sortProducts("lohi");
        
        String newSortValue = products.getSortOptionValue();
        Assert.assertEquals(newSortValue, "lohi", 
            "Сортировка должна быть установлена на 'lohi' (low to high)");
        Assert.assertNotEquals(initialSortValue, newSortValue, 
            "Значение сортировки должно измениться");
    }
}

