package testsuper;

import datatransferobject.Event;
import manager.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.HomeScreen;
import screen.LoginScreen;

public class EventCreateWithLogoutAfterTest extends Configuration {
    @BeforeMethod
    public void preCondition(){
        new LoginScreen(driver)
                .fillEmail("vick@mail.com")
                .fillPassword("Ww123454$")
                .clickLoginButton()
                .skipWizard();
    }

    @Test
    public void createNewEventTest(){
        Event event = Event.builder()
                .title("Title")
                .type("Type")
                .breaks(2)
                .wage(50)
                .build();
        boolean plusButtonPresent = new HomeScreen(driver)
                .initCreationEvent()
                .createNewEvent(event)
                .isPlusButtonPresent();
        Assert.assertTrue(plusButtonPresent);
    }

    @Test
    public void createNewEventTest2(){
        Event event = Event.builder()
                .title("Title")
                .type("Type")
                .breaks(2)
                .wage(50)
                .build();
        boolean plusButtonPresent = new HomeScreen(driver)
                .initCreationEvent()
                .createNewEvent(event)
                .isPlusButtonPresent();
        Assert.assertTrue(plusButtonPresent);
    }

    @AfterMethod
    public void postCondition(){
        new HomeScreen(driver)
                .openMenu()
                .logOut()
                .loginButtonPresent();
    }

}
