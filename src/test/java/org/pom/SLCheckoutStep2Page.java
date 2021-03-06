package org.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.pom.inventoryPage.SLInventoryPage;

public class SLCheckoutStep2Page extends SitePage {
    private final By cancelButton = new By.ById("cancel");
    private final By finishButton = new By.ById("finish");
    private final By inventoryElement = new By.ByClassName("inventory_item_name");
    private WebDriver driver;

    public SLCheckoutStep2Page(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SLInventoryPage goToInventoryPage() {
        driver.findElement(cancelButton).click();
        return new SLInventoryPage(driver);
    }

    public SLConfirmationOfOrderPage goToConfirmationPage() {
        driver.findElement(finishButton).click();
        return new SLConfirmationOfOrderPage(driver);
    }

    public SLIndividualItemPage goToIndividualItemPage(int index) {
        driver.findElements(inventoryElement).get(index).click();
        return new SLIndividualItemPage(driver);
    }
}
