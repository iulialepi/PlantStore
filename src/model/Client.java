package model;

import java.util.HashMap;
import java.util.List;

public class Client extends Person {
    public List<Plant> ownedPlants;
    public int totalCredit;
    public String username;
    public String password;

    //public HashMap<Plant, Integer> ownedPlants;


    public Client(int id, String firstName, String lastName, List<Plant> ownedPlants, int totalCredit, String username, String password) {
        super(id, firstName, lastName);

        this.ownedPlants = ownedPlants;
        this.totalCredit = totalCredit;
        this.username = username;
        this.password = password;
    }

    public List<Plant> getOwnedPlants() {
        return ownedPlants;
    }

    public void setOwnedPlants(List<Plant> ownedPlants) {
        this.ownedPlants = ownedPlants;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Client: " + getFirstName() + " " + getLastName()+"\n" + "username: " + getUsername() + "\n" +
                "List of plants: \n" + ownedPlants;
    }
}
