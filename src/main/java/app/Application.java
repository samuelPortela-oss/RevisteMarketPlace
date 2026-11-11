package app;

import app.UI.AppUserInterface;
import app.config.Config;

public class Application {

    public static void main(String[] args){

        AppUserInterface appUserInterface = Config.app();

        appUserInterface.startApp();
    }
}
