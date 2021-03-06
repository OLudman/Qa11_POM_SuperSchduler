package screen;

import datatransferobject.Event;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EditCreateEventScreen extends BaseScreen{
    public EditCreateEventScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_title_input']")
    MobileElement title;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_type_input']")
    MobileElement type;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_break_plus_btn']")
    MobileElement breaksPlusButton;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_edit']")
    MobileElement wageEdit;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_input']")
    MobileElement wageInput;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_save']")
    MobileElement wageSave;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/info_save_btn']")
    MobileElement confirmButton;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']")
    List<MobileElement> days;

    public HomeScreen createNewEvent(Event event){
        should(title,15);
        type(title, event.getTitle());
        type(type, event.getType());
        driver.hideKeyboard();

        int breaks = event.getBreaks();
        if(breaks>0&&breaks<5){
            for (int i = 0; i < breaks; i++) {
                breaksPlusButton.click();
            }
        }
        wageEdit.click();
        type(wageInput,String.valueOf(event.getWage()));
        wageSave.click();
        confirmButton.click();
        return new HomeScreen(driver);
    }

    public EditCreateEventScreen actionData() {
        pause(3000);
        Dimension size  = driver.manage().window().getSize();
        int windowH= size.getHeight();
        int windowW = size.getWidth();
        logger.info("The window Height ----> " +windowH);
        logger.info("The window Wight ----> " +windowW);
        MobileElement dayFirst = days.get(0);
        Rectangle rect = dayFirst.getRect();
        int xF = rect.getX();
        int yF = rect.getY();
        logger.info("The first element 'x' --------> " +xF);
        logger.info("The first element 'y' --------> " +yF);
        int xL = days.get(2).getRect().getX();
        int yL = days.get(2).getRect().getY();
        logger.info("The last element 'x' --------> " +xL);
        logger.info("The last element 'y' --------> " +yL);

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xL, yL))
                .moveTo(PointOption.point(xF,yF))
                .release()
                .perform();
        pause(3000);

        touchAction.longPress(PointOption.point(xF,yF))
                .moveTo(PointOption.point(xL, yL))
                .release()
                .perform();
        pause(3000);

        touchAction.longPress(PointOption.point(800,300))
                .moveTo(PointOption.point(300,300))
                .release()
                .perform();
        pause(3000);
        return this;
    }
}
