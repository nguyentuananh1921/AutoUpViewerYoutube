package tuanbuffet.common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebUI {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void switchToWindows(int number) {
        Set<String> windows = getDriver().getWindowHandles();
        String firstWindow = (String) windows.toArray()[number];
        getDriver().switchTo().window(firstWindow);
    }


    public static void HideBrowers() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        /*options.addArguments("--disable-gpu");*/ // Optional on Linux, needed on Windows
        options.addArguments("--no-sandbox"); // Optional on Linux
        options.addArguments("--disable-dev-shm-usage"); // Optional on Linux
        options.addArguments("--blink-settings=imagesEnabled=false");
        driver.set(new ChromeDriver(options));
        /*driver = new ChromeDriver(options);*/
    }

    public static void clickPartialLinkText(String content) {
        By locator = By.partialLinkText(content);
        clickElement(locator);
    }

    public static void openBrowserOption(String numbersProfiles) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\Nguyen Tuan Anh\\AppData\\Local\\Google\\Chrome\\User Data\\");
        options.addArguments("--profile-directory=Profile " + numbersProfiles);
        driver.set(new ChromeDriver(options));
    }

    public static void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        /*prefs.put("profile.managed_default_content_settings.images", 2); // Tắt tải hình ảnh
        prefs.put("profile.managed_default_content_settings.stylesheets", 2); // Tắt tải CSS
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-gpu"); // Optional on Linux, needed on Windows
        options.addArguments("--no-sandbox"); // Optional on Linux
        options.addArguments("--disable-dev-shm-usage"); // Optional on Linux
        options.addArguments("--blink-settings=imagesEnabled=false");*/
        driver.set(new ChromeDriver(options));
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
    }
    public static void openBrowserSize(int width, int height,int distance) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-position=" +distance +",0" );
        options.addArguments("--window-size=" + width + "," + height);
        driver.set(new ChromeDriver(options));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
    }
    public static void closeWindow(){
        getDriver().close();
    }
    public static void newWindow(){
        getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public static void closeBrowser(){
        getDriver().quit();
    }

    public static void AlertDismiss() {
        getDriver().switchTo().alert().dismiss();
    }

    public static void AlertAccept() {
        getDriver().switchTo().alert().accept();
    }

    public static void AlertSendKeys(String text) {
        getDriver().switchTo().alert().sendKeys(text);
    }

    public static String AlertClick() {
        return getDriver().switchTo().alert().getText();
    }

    private static int EXPLICIT_WAIT_TIMEOUT = 15;
    private static int WAIT_PAGE_LEADED_TIMEOUT = 30;

    public WebUI() {
    }

    public static WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static void logConsole(String message) {
        /*System.out.println(message);*/
    }

    public static void hoverOnElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by));
        logConsole("Hover on element " + by);
    }

    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        /*if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
            sleep(1);
        }*/
        return getWebElement(by);
    }

    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.contextClick(getWebElement(by));
        logConsole("Right click on element " + by);
    }

    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoaded();
        logConsole("Open URL: " + URL);
    }

    public static String getCurrentUrl() {
        waitForPageLoaded();
        logConsole("Get Current URL: " + getDriver().getCurrentUrl());
        return getDriver().getCurrentUrl();
    }

    public static void clickElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).click();
        logConsole("Click on element " + by);
        //Report
    }

    public static void clearText(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        highLightElement(by);
        getWebElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        getWebElement(by).sendKeys(Keys.DELETE);
        logConsole("Click on element " + by);
    }

    public static void enterText(By by, String value) {
        waitForPageLoaded();
        waitForElementVisible(by);
        /*getWebElement(by).clear();*/
        getWebElement(by).sendKeys(value);
    }
    public static boolean isSelected(By locator){
        waitForPageLoaded();
        waitForElementVisible(locator);
        return getWebElement(locator).isSelected();
    }

    public static void refreshPageWeb() {
        getDriver().navigate().refresh();
    }

    public static String getTextElement(By by) {
        waitForPageLoaded();
        waitForElementVisible(by);
        logConsole("Get text of element " + by);
        logConsole("==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    public static String getAttributeElement(By by, String attributeName) {
        waitForPageLoaded();
        waitForElementVisible(by);
        logConsole("Get attribute value of element " + by);
        logConsole("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    public static void scrollToElementWithJS(By by) {
        waitForElementPresent(by);
        //Dùng Actions class
        //Robot class
        //Dùng JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        logConsole("Scroll to element " + by);
    }

    public static void scrollToElementWithJS(int y) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0, " + y + ")");
    }

    public static void scrollToElement(By by) {
        //Dùng Actions class

    }

    public static void scrollToElementWithRobot(By by) {
        //Dùng Robot class
    }

    public static void waitForElementVisible(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementVisible(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static boolean verifyElementIsDisplay(By by, int second) {
        waitForPageLoaded();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static boolean verifyElementIsDisplay(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static int listElements(By by) {
        waitForPageLoaded();
        List<WebElement> listElement = getDriver().findElements(by);
        return listElement.size();
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));

        if (!listElement.isEmpty()) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }

    public static String getTextElement(String xpath) {
        return getDriver().findElement(By.xpath(xpath)).getText();
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    /*public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };


    }*/
    public static void waitForPageLoaded() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LEADED_TIMEOUT));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        } catch (Exception e) {
            System.out.println("Error waiting for page to load: " + e.getMessage());
        }
    }

    public static void clickElementJS(By by) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        WebElement button = getDriver().findElement(by);
        js.executeScript("arguments[0].click();", button);
    }

    public static void clickElementJS(String x) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementsById('input')[3].click()");
    }
}

