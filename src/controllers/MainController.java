package controllers;

import models.Restapi;
import views.Mainconsole;

public class MainController {
    Mainconsole mainconsole;
    Restapi restapi;
    public MainController() {
        restapi = new Restapi();
        mainconsole = new Mainconsole(restapi);
    }
}
