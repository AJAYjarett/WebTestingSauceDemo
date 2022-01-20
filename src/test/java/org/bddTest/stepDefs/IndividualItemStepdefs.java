package org.bddTest.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.PomUtility;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pom.SLCartPage;
import org.pom.SLIndividualItemPage;
import org.pom.SlLoginPage;
import org.pom.inventoryPage.SLInventoryPage;

public class IndividualItemStepdefs {
    private WebDriver driver;
    private SLInventoryPage slInventoryPage;
    private SLIndividualItemPage slIndividualItemPage;
    private SlLoginPage loginPage;
    private SLCartPage cartPage;
    private String productName;

    @Before
    public void setup(){
        PomUtility.setDriverLocation(PomUtility.getDefaultDriverLocation());
        PomUtility.setChromeDriverService(PomUtility.getDefaultDriverLocation());
        driver = new ChromeDriver();
        loginPage = new SlLoginPage(driver);
        loginPage.Login("standard_user", "secret_sauce");
        slInventoryPage = new SLInventoryPage(driver);

    }

    @And("I am on the individual item page")
    public void iAmOnTheIndividualItemPage() {
        slIndividualItemPage = slInventoryPage.goToItem(4);
        productName = slIndividualItemPage.getProductName();
    }

    @When("I click on add to cart button")
    public void iClickOnAddToCartButton() {
        slIndividualItemPage.addItemToCart();
    }

    @Then("Cart icon shows one item")
    public void cartIconShowsOneItem() {
        Assertions.assertTrue(slIndividualItemPage.getNumberOfCartItems()==1);
    }

    @And("I click on remove button")
    public void iClickOnRemoveButton() {
        slIndividualItemPage.removeItemFromCart();
    }

    @Then("Cart icon shows zero items")
    public void cartIconShowsZeroItems() {
        Assertions.assertTrue(slIndividualItemPage.getNumberOfCartItems()==0);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @And("I go to cart page")
    public void iGoToCartPage() {
        cartPage = slIndividualItemPage.goToCartPage();
    }

    @Then("My item is in the cart")
    public void myItemIsInTheCart() {
        Assertions.assertEquals(productName, cartPage.getItemNameInCart(0));
    }

    @Then("The cart is empty")
    public void theCartIsEmpty() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,() -> cartPage.getItemNameInCart(0));

    }
}