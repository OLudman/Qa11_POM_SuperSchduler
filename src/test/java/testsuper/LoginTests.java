package testsuper;

import datatransferobject.User;
import manager.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screen.HomeScreen;
import screen.LoginScreen;
import screen.SplashScreen;

public class LoginTests extends Configuration {

    @Test
    public void registrationSuccess(){
        int index = (int) (System.currentTimeMillis()/1000)%3600;
//        boolean isRegistrationSuccess = new SplashScreen(driver)
//                .checkVersion("0.0.3")
        boolean isRegistrationSuccess = new LoginScreen(driver)
                .fillEmail("vick"+index+"@mail.com")
                .fillPassword("Ww123454$")
                .clickLoginButton()
                .skipWizard()
                .isPlusButtonPresent();

        Assert.assertTrue(isRegistrationSuccess);
    }

    @Test
    public void loginSuccess(){
//        boolean isLoginSuccess = new SplashScreen(driver)
//                .checkVersion("0.0.3")
        boolean isLoginSuccess = new LoginScreen(driver)
                .fillEmail("vick@mail.com")
                .fillPassword("Ww123454$")
                .clickLoginButton()
                .skipWizard()
                .isPlusButtonPresent();

        Assert.assertTrue(isLoginSuccess);
    }

    @Test
    public void registrationSuccessModel(){
        int index = (int) (System.currentTimeMillis()/1000)%3600;
        User user = User.builder().email("vick"+index+"@mail.com").password("Ww123454$").build();
        logger.info("This test run with user and password" + user.toString());
        boolean plusButtonPresent = new LoginScreen(driver)
                .complexLogin(user)
                .skipWizard()
                .isPlusButtonPresent();
        Assert.assertTrue(plusButtonPresent);
    }

    @AfterMethod
    public void postCondition(){
        boolean logOutSuccess = new HomeScreen(driver)
                .openMenu() // add new element menu
                .logOut() /// click log out button - add new element
                .isLoginButtonPresent();
        Assert.assertTrue(logOutSuccess);

//        new HomeScreen(driver)
//                .openMenu()
//                .logOut()
//                .loginButtonPresent();
    }
}
