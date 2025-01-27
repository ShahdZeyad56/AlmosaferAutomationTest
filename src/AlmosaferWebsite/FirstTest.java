package AlmosaferWebsite;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstTest extends Parameters {

    // Runs before the test suite to set up configurations
    @BeforeTest
    public void mySetup() {
        Configration();
    }

    // 1) Check the default language of the website
    @Test(priority = 1, description = "Check The Default Language", enabled = false)
    public void DefaultLanguage() {
        // Retrieve the language attribute of the HTML tag
        String ActualDefaultLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");

        // Assert that the actual language matches the expected default language
        Assert.assertEquals(ActualDefaultLanguage, ExpectedDefaultLanguage);
    }

    // 2) Check the default currency displayed on the website
    @Test(priority = 2, description = "Check The Default Currency", enabled = false)
    public void DefaultCurrency() {
        // Retrieve the text of the currency selector button
        String ActualDefaultCurrency = driver
                .findElement(By.xpath("//button[@data-testid=\"Header__CurrencySelector\"]")).getText();

        // Assert that the actual currency matches the expected default currency
        Assert.assertEquals(ActualDefaultCurrency, ExpectedDefaultCurrency);
    }

    // 3) Verify the contact number displayed on the website
    @Test(priority = 3, description = "Check The Contact Number", enabled = false)
    public void ContactNumber() {
        // Retrieve the text of the contact number from the <strong> tag
        String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

        // Assert that the actual contact number matches the expected contact number
        Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
    }

    // 4) Verify that the Qitaf logo is displayed in the footer
    @Test(priority = 4, description = "Qitaf Logo is displayed in footer", enabled = true)
    public void QitafLogo() {
        // Scroll to the bottom of the page (footer)
        js.executeScript("window.scroll(0,8000)");

        // Locate the footer element
        WebElement Footer = driver.findElement(By.tagName("footer"));

        // Locate the container div inside the footer
        WebElement ContainerDiv = Footer.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs"));

        // Verify if the Qitaf logo (SVG) is displayed
        boolean ActualQitafLogo = ContainerDiv.findElement(By.tagName("svg")).isDisplayed();

        // Assert that the actual logo display status matches the expected status
        Assert.assertEquals(ActualQitafLogo, ExpectedQitafLogo);
    }

    // 5) Verify that the hotel tab is not selected by default
    @Test(priority = 5)
    public void CheckHotelTabIsNotSelected() throws InterruptedException {
        // Locate the hotel tab element by its ID
        WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

        // Retrieve the "aria-selected" attribute of the hotel tab
        String ActualValueforHotelTab = HotelTab.getDomAttribute("aria-selected");

        // Assert that the actual value matches the expected value
        Assert.assertEquals(ActualValueforHotelTab, ExpectedValueForHotelTab);
    }

    // 6) Verify the departure date displayed on the website
    @Test(priority = 6)
    public void CheckDepatureDate() throws InterruptedException {
        // Locate all date elements by their CSS selector
        List<WebElement> dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));

        // Retrieve the text of the first date element (departure date)
        String ActualDepatureDate = dates.get(0).getText();

        // Assert that the actual departure date matches the expected date
        Assert.assertEquals(ActualDepatureDate, Tomorrow);
    }

    // 7) Verify the return date displayed on the website
    @Test(priority = 7)
    public void CheckReturnDate() throws InterruptedException {
        // Locate all date elements by their CSS selector
        List<WebElement> dates = driver.findElements(By.cssSelector(".sc-fvLVrH.hNjEjT"));

        // Retrieve the text of the second date element (return date)
        String ActualReturnDate = dates.get(1).getText();

        // Assert that the actual return date matches the expected date
        Assert.assertEquals(ActualReturnDate, DayAfterTomorrow);
    }

    // 8) Randomly change the website language and verify functionality
    @Test(priority = 8)
    public void RandomlyChangeWebSiteLanguage() throws InterruptedException {
        // Navigate to a random website URL from the list
        driver.get(Websites[RandomIndexForTheWebSite]);

        // Locate the language switch button in the header
        WebElement headerforTheLanagauge = driver.findElement(By.xpath("//a[@data-testid='Header__LanguageSwitch']"));

        // Locate the hotel tab and click on it
        WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
        HotelTab.click();

        // Locate the search input field for cities
        WebElement SearchCityInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

        // Check if the current language is Arabic or English, then perform actions accordingly
        if (headerforTheLanagauge.getText().equals("\u0627\u0644\u0639\u0631\u0628\u064a\u0629")) { // "العربية"
            // Verify the default language attribute is Arabic
            String ActualLanaguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
            Assert.assertEquals(ActualLanaguage, ExpectedDefaultLanguage);

            // Input a random English city
            SearchCityInput.sendKeys(EnglishCities[RandomEnglishCity]);

            // Select a city from the suggestions
            WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
            ListOfCities.findElements(By.tagName("li")).get(1).click();

        } else {
            // Verify the default language attribute is English
            String ActualLanaguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
            Assert.assertEquals(ActualLanaguage, ExpectedArabicLanaguage);

            // Input a random Arabic city
            SearchCityInput.sendKeys(ArabicCities[RandomArabicCity]);

            // Select a city from the suggestions
            WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
            ListOfCities.findElements(By.tagName("li")).get(1).click();
        }

        // Locate the visitor number selector and select a random index
        WebElement NumberOfVistor = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
        Select mySelector = new Select(NumberOfVistor);
        int RandomIndex = rand.nextInt(2);
        mySelector.selectByIndex(RandomIndex);

        // Locate the search button and click it
        WebElement SearchButton = driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk"));
        SearchButton.click();

        // Wait for the results to load
        Thread.sleep(25000);

        // Locate the results element and verify its content
        WebElement Results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']"));
        boolean ActualResult = Results.getText().contains("stays") || Results.getText().contains("\u0645\u0643\u0627\u0646"); // "مكان"

        // Assert that the actual results match the expected results
        Assert.assertEquals(ActualResult, ExpectedResults);
    }
}
