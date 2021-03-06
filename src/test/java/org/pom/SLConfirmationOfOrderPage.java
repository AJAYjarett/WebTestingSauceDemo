package org.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.pom.inventoryPage.SLInventoryPage;

public class SLConfirmationOfOrderPage extends SitePage {

    private By backToProduct = new By.ById("back-to-products");
    private WebDriver driver;

    public SLConfirmationOfOrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public SLInventoryPage returnToInventoryPage() {
        driver.findElement(backToProduct).click();
        return new SLInventoryPage(driver);
    }


}
