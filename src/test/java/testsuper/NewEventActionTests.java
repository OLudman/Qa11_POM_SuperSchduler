package testsuper;

import datatransferobject.Event;
import datatransferobject.User;
import manager.Configuration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screen.HomeScreen;
import screen.LoginScreen;

public class NewEventActionTests extends Configuration {

    @BeforeMethod
    public void preCondition(){
        new LoginScreen(driver)
                .complexLogin(User.builder().email("vick@mail.com").password("Ww123454$").build())
                .skipWizard();
    }

    @Test
    public void addEventAction(){
        Event event = Event.builder()
                .title("Title")
                .type("Type")
                .breaks(2)
                .wage(50)
                .build();
        boolean plusButtonPresent = new HomeScreen(driver)
                .initCreationEvent()
                .actionData()
                .createNewEvent(event)
                .isPlusButtonPresent();
        Assert.assertTrue(plusButtonPresent);
    }
}
