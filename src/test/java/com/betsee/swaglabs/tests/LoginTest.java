package com.betsee.swaglabs.tests;

import com.betsee.swaglabs.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @Test(groups = {"positive", "smoke","regression"})
    @Parameters({"username","password"})
    public void validLoginTest(String username,String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
                currentUrl.contains("inventory"),
                "Login was unsuccessful"
        );
    }

    @Test(groups = {"negative", "regression"})
    @Parameters({"username", "password"})
    public void lockedOutUserTest(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage =
                "Epic sadface: Sorry, this user has been locked out.";

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Incorrect error message displayed");
    }

    @Test(groups = {"negative", "regression"})
    @Parameters({"username", "password"})
    public void invalidUsernameTest(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Incorrect error message displayed");
    }

    @Test(groups = {"negative", "regression"})
    @Parameters({"username", "password"})
    public void invalidPasswordTest(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage =
                "Epic sadface: Username and password do not match any user in this service";

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Incorrect error message displayed");
    }

    @Test(groups = {"negative", "regression"})
    @Parameters({"password"})
    public void emptyUsernameTest(String password) {
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage =
                "Epic sadface: Username is required";

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Incorrect error message displayed");
    }


    @Test(groups = {"negative", "regression"})
    @Parameters({"username"})
    public void emptyPasswordTest(String username) {
        loginPage.enterUsername(username);
        loginPage.clickLogin();

        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage =
                "Epic sadface: Password is required";

        Assert.assertEquals(
                actualErrorMessage,
                expectedErrorMessage,
                "Incorrect error message displayed");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null)
        driver.quit();
    }
}