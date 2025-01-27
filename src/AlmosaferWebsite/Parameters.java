package AlmosaferWebsite;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

    // Random object for generating random values
    Random rand = new Random();

    // WebDriver instance for controlling the browser
    WebDriver driver = new ChromeDriver();

    // Default website URL
    String websitename = "https://global.almosafer.com/en/flights-home";

    // Expected default language (English)
    String ExpectedDefaultLanguage = "en";

    // Expected language for Arabic
    String ExpectedArabicLanaguage = "ar";

    // Expected default currency
    String ExpectedDefaultCurrency = "SAR";

    // Expected contact number for the website
    String ExpectedContactNumber = "+966554400000";

    // JavaScript executor instance for executing custom JavaScript commands
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Expected status of the Qitaf logo (true for displayed)
    boolean ExpectedQitafLogo = true;

    // Expected value for the "aria-selected" attribute of the hotel tab (false for not selected)
    String ExpectedValueForHotelTab = "false";

    // LocalDate instance for retrieving the current date
    LocalDate date = LocalDate.now();

    // Today's date as an integer (day of the month)
    int Today = date.getDayOfMonth();

    // Tomorrow's date as a string
    String Tomorrow = Integer.toString(date.plusDays(1).getDayOfMonth());

    // The day after tomorrow's date as a string
    String DayAfterTomorrow = Integer.toString(date.plusDays(2).getDayOfMonth());

    // List of website URLs (English and Arabic versions)
    String[] Websites = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

    // Randomly selected index for the website array
    int RandomIndexForTheWebSite = rand.nextInt(Websites.length);

    // List of English city names for searching
    String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };

    // Randomly selected index for the English cities array
    int RandomEnglishCity = rand.nextInt(EnglishCities.length);

    // List of Arabic city names for searching
    String[] ArabicCities = { "\u062C\u062F\u0629", "\u062F\u0628\u064A" }; // "جدة", "دبي"

    // Randomly selected index for the Arabic cities array
    int RandomArabicCity = rand.nextInt(ArabicCities.length);

    // Expected results for tests (true for success)
    boolean ExpectedResults = true;

    // Configuration method to initialize browser settings and navigate to the website
    public void Configration() {
        // Navigate to the default website URL
        driver.get(websitename);

        // Maximize the browser window
        driver.manage().window().maximize();

        // Set an implicit wait duration of 4 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        // Locate and click the SAR confirmation button (first-time user prompt)
        WebElement FirstsarMessage = driver
                .findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
        FirstsarMessage.click();
    }
}
