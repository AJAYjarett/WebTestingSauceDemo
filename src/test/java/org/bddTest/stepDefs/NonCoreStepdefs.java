package org.bddTest.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.pom.SLCartPage;
import org.pom.SLCheckoutStep1Page;
import org.pom.SLIndividualItemPage;
import org.pom.SlLoginPage;
import org.pom.inventoryPage.SLInventoryPage;

public class NonCoreStepdefs {
    private WebDriver driver;
    private SlLoginPage loginPage;
    private SLInventoryPage inventoryPage;
    private SLCheckoutStep1Page checkoutStep1Page;
    private SLCartPage cartPage;
    private SLIndividualItemPage slIndividualItemPage;
    private String productName;

    @And("I click on continue")
    public void iClickOnContinue() {
        checkoutStep1Page.clickContinueButton();
    }

    @Then("The website should give me an error saying {string}")
    public void theWebsiteShouldGiveMeAnErrorSaying(String string) {
        String errorMessage = driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < errorMessage.length(); i++) {
            if (i >= 7) {
                sb.append(errorMessage.toCharArray()[i]);
            }
        }
        Assertions.assertEquals(string, sb.toString());
    }

    @When("The first name field is empty")
    public void theFirstNameFieldIsEmpty() {
        checkoutStep1Page.setFirstName("");
    }

    @When("The last name field is empty")
    public void theLastNameFieldIsEmpty() {
        checkoutStep1Page.setLastName("");
    }

    @When("The postal code field is empty")
    public void thePostalCodeFieldIsEmpty() {
        checkoutStep1Page.setPostalCode("");
    }

    @And("The first name field is filled")
    public void theFirstNameFieldIsFilled() {
        checkoutStep1Page.setFirstName("Luciano");
    }

    @And("The last name field is filled")
    public void theLastNameFieldIsFilled() {
        checkoutStep1Page.setLastName("Big Tuna");
    }

    @And("The postal code field is filled")
    public void thePostalCodeFieldIsFilled() {
        checkoutStep1Page.setPostalCode("0123");
    }

    @When("I click on add to cart button")
    public void iClickOnAddToCartButton() {
        slIndividualItemPage.addItemToCart();
    }

    @Then("Cart icon shows one item")
    public void cartIconShowsOneItem() {
        Assertions.assertEquals(1, slIndividualItemPage.getNumberOfCartItems());
    }

    @Then("My item is in the cart")
    public void myItemIsInTheCart() {
        Assertions.assertEquals(productName, cartPage.getItemNameInCart(0));
    }

    @And("I click on remove button")
    public void iClickOnRemoveButton() {
        slIndividualItemPage.removeItemFromCart();
    }

    @Then("Cart icon shows zero items")
    public void cartIconShowsZeroItems() {
        Assertions.assertEquals(0, slIndividualItemPage.getNumberOfCartItems());
    }

    @Then("The cart is empty")
    public void theCartIsEmpty() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cartPage.getItemNameInCart(0));
    }

    @Then("the add to cart button is changed to remove")
    public void theAddToCartButtonIsChangedToRemove() {
        Assertions.assertEquals("REMOVE", inventoryPage.getInventoryElement(0).getText().substring(
                inventoryPage.getInventoryElement(0).getText().length() - 6));
    }

    @When("I click on logout button")
    public void iClickOnLogoutButton() {
        loginPage = inventoryPage.logout();
    }

    @Then("I will be logged out")
    public void iWillBeLoggedOut() {
        Assertions.assertEquals("https://www.saucedemo.com/", loginPage.getURL());
    }
}
