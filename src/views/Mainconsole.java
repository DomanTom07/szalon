package views;

import java.util.ArrayList;

import models.Restapi;
import models.Vehicle;

public class Mainconsole {
    Restapi restapi;
    public Mainconsole(Restapi restapi) {
        this.restapi = restapi;
        start();
    }
    public void start() {
        ArrayList<Vehicle> vehicles = this.restapi.getVehicles();
        for(Vehicle vehicle : vehicles) {
            System.out.printf("%10s %12s %6.2f\n",
            vehicle.licenseplate,
            vehicle.brand,
            vehicle.price);
        }
    }
}
