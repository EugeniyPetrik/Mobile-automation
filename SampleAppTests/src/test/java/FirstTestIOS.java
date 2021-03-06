import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FirstTestIOS {

    public static AppiumDriver<MobileElement> driver;

    public static void main(String[] args) throws MalformedURLException {

        File classPathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classPathRoot, "app/iOS");
        File app = new File(appDir, "ContactsSimulator.app");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Simulator");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "F678F40D-B14D-4252-B1AD-1997CBB7DF11");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3.1");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, app);
        desiredCapabilities.setCapability("newCommandTimeout", 300);

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        ArrayList<MobileElement> arrrayAllContacts = findElementsByXpath("//XCUIElementTypeTable//XCUIElementTypeCell");

        int i = 0;

        for (MobileElement arrayElement : arrrayAllContacts) {

            if (i == 5) {
                break;
            }

            arrayElement.click();

            String detailName = findElementByXpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[1]").getText();

            MobileElement userImage = findElementByAccessibilityId("contact_details");
            MobileElement userPhone = findElementByXpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[3]");
            MobileElement userEmail = findElementByXpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[5]");
            MobileElement userFirstAddress = findElementByXpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[7]");
            MobileElement userSecondAddress = findElementByXpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[8]");

            switch (detailName) {

                case "Byron Workman" : {

                    userImage.isDisplayed();
                    userPhone.getText().equals("+1(656)-6779916");
                    userEmail.getText().equals("ggbyron6@yopmail.com");
                    userFirstAddress.getText().equals("413 Wescam Drive");
                    userSecondAddress.getText().equals("43115 Miami");
                    break;
                }

                case "Chris Heavener" : {

                    userImage.isDisplayed();
                    userPhone.getText().equals("+1(959)-1775994");
                    userEmail.getText().equals("ischris18@outlook.com");
                    userFirstAddress.getText().equals("201 Metz Bates");
                    userSecondAddress.getText().equals("15840 New York");
                    break;
                }

                case "Christin Steinberg" : {

                    userImage.isDisplayed();
                    userPhone.getText().equals("+1(656)-1115633");
                    userEmail.getText().equals("awchristin22@yahoo.com");
                    userFirstAddress.getText().equals("412 Barfield Trail");
                    userSecondAddress.getText().equals("53713 Houston");
                    break;
                }

                case "Dulcie Moller" : {

                    userImage.isDisplayed();
                    userPhone.getText().equals("+1(555)-9779202");
                    userEmail.getText().equals("eimoller8@yopmail.com");
                    userFirstAddress.getText().equals("52 Woodside Way");
                    userSecondAddress.getText().equals("58193 New Jersey");
                    break;
                }

                case "Duane Cedillo" : {

                    userImage.isDisplayed();
                    userPhone.getText().equals("+1(141)-1779583");
                    userEmail.getText().equals("hxduane23@gmail.com");
                    userFirstAddress.getText().equals("438 Ethels Lane");
                    userSecondAddress.getText().equals("24456 Houston");
                    break;
                }

                default : {
                    System.out.println("I didn't find this element");
                    break;
                }

            }

            driver.navigate().back();
            i++;
        }

        MobileElement mainSearch = findElementByAccessibilityId("Search for contact");
        mainSearch.setValue("123");

        ArrayList<MobileElement> userSearchEmpty = findElementsByXpath("//XCUIElementTypeTable/XCUIElementTypeCell[@visible=\"true\"]");
        Assert.assertEquals(userSearchEmpty.size(), 0);

        mainSearch.clear();

        mainSearch.sendKeys("Li");

        List<MobileElement> userSearchNotEmpty = findElementsByXpath("//XCUIElementTypeCell/XCUIElementTypeStaticText[@visible=\"true\"]");
        Assert.assertEquals(userSearchNotEmpty.size(), 2);

        MobileElement firstName = findElementByAccessibilityId("Lily Barnhill");
        MobileElement secondName = findElementByAccessibilityId("Philippe Meyerson");

        firstName.isDisplayed();
        secondName.isDisplayed();

        mainSearch.clear();

        findElementByAccessibilityId("Cancel");

        driver.quit();

    }

    private static MobileElement findElementByAccessibilityId (String id) {
        return driver.findElementByAccessibilityId(id);
    }

    private static ArrayList<MobileElement> findElementsByXpath (String xpath) {
        return (ArrayList<MobileElement>) driver.findElementsByXPath(xpath);
    }

    private static MobileElement findElementByXpath (String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

}

//    @Test(description = "Search")
//    public void testSearch() throws Exception {
//        MobileElement searchField;
//        searchField = platform.equals(Platform.ANDROID) ? driver.findElement(By.id("main_search")) : driver.findElement(By.id("ID_ON_IOS"));
//        searchField.setValue("Joy S");
//        Assert.assertFalse(false);
//    }
//
//    @Test(description = "Click to name")
//    public void someOtherTest() throws Exception {
//        List<MobileElement> name = driver.findElements(By.id("name"));
//        Assert.assertEquals(name.size(), 1);
//
//        for (MobileElement element : name) {
//            if (element.getText().equals("Joy Stclair")) {
//                element.click();
//            }
//        }
//
//        driver.findElement(By.xpath("//*[@resource-id='android:id/content']//android.widget.ImageView"));
//        driver.findElement(By.className("android.widget.ImageView"));
//    }
//
//
//
//
//    public static void main(String[] args) throws MalformedURLException {
//
//        MobileElement searchField = driver.findElement(By.id("main_search"));   // driver.findElementById("main_search");
//        searchField.setValue("Joy S");                                          // searchField.sendKeys("Joy S");
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        MobileElement elementByXpath = driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.jayway.contacts:id/name\"]");
//        elementByXpath.click();
//
//        String email = findElementById("email").getText();
//
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);    // explicit wait
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
//        System.out.println(email.equals("alstclair11@yopmail.com"));
//
//
//
//
//        List<MobileElement> name = driver.findElements(By.id("name"));
//        Assert.assertEquals(name.size(), 1);    // Assert.assertTrue(name.size() == 1);
//
//        for (MobileElement element : name) {
//            element.getText().equals("Joy Stclair");
//            element.click();
//        }
//
//        MobileElement image = driver.findElement(By.xpath("//*[@resource-id=\"android:id/content\"]//android.widget.ImageView"));
//
//
//
//        // explicit wait
//        WebDriverWait explicitWait = new WebDriverWait(driver, 30);
//        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("some_id")));
//
//        // fluent wait
//        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
//                .withTimeout(30, SECONDS)
//                .pollingEvery(5, SECONDS)
//                .ignoring(NoSuchElementException.class);
//
//        WebElement element = fluentWait.until(new com.google.common.base.Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver webDriver) {
//                return driver.findElement(By.id("some_id"));
//            }
//        });
//    }
