package screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

public class BaseScreen {
    AppiumDriver<MobileElement> driver;
    Logger logger = LoggerFactory.getLogger(BaseScreen.class);

    public BaseScreen(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void type(MobileElement element, String text){
        if(text!=null){
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void should(MobileElement element, int time){
        new WebDriverWait(driver,time)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void shouldHave(MobileElement element, String text, int time){
        new WebDriverWait(driver,time)
                .until(ExpectedConditions.textToBePresentInElement(element,text));
    }

    public boolean isDisplayedWithException(MobileElement element) {
        try{
            should(element,10);
            return element.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
