package com.betsee.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private By usernameInput=By.id("user-name");
    private By passwordInput=By.id("password");
    private By submitButton=By.id("login-button");
    private By errorMessage = By.xpath("//h3[@data-test='error']");
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(submitButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
