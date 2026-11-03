package app;

import app.UI.AppUserInterface;
<<<<<<< HEAD

public class Application {

    public static void main(String[] args){


        AppUserInterface appUserInterface = new AppUserInterface();
=======
import app.config.Config;

public class Application {

    public static void main(String[] args) {

        AppUserInterface appUserInterface = Config.app();
>>>>>>> 50c9d38602c8a1e449437fdd0a906df001cbd04b

        appUserInterface.startApp();
    }
}
