import controller.PlantAcquisition;
import repository.inmemory.ClientInMemoryRepo;
import repository.inmemory.PlantInMemoryRepo;
import utils.InvalidCredentialsException;

import java.io.IOException;
import java.util.Scanner;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws InvalidCredentialsException, IOException {

        PlantAcquisition controller = new PlantAcquisition(new ClientInMemoryRepo(), new PlantInMemoryRepo());
        controller.showAllPlantsForSale();

        System.out.println("Username: ");
        Scanner in = new Scanner(System.in);
        String username = in.nextLine();

        System.out.println("Password: ");
        Scanner in2 = new Scanner(System.in);
        String password = in.nextLine();

        if(controller.login(username, password)){
            System.out.println("Successful login!");
            controller.showAllPlantsForSale();

            /*
            System.out.println("Choose a plant you would like to buy (enter number)");
            Scanner in3 = new Scanner(System.in);
            int choice = in.nextInt();
            System.out.println(controller.nameFilter(choice));

             */
        }



        //controller.addToCart();


        /*

        //clients credit before buying and list of plants
        System.out.println("The clients credit is: " + client1.totalCredit);
        System.out.println("The clients list of plants" + client1.ownedPlants);

        //sellers list of plants
        System.out.println("The sellers list of plants" + seller1.plantsForSale);

        receipt.addToCart(plant1, client1, seller1, 3);

        //clients credit and list after
        System.out.println("The clients remaining credit: " + client1.totalCredit);
        System.out.println("The clients list of plants after the aquisition" + client1.ownedPlants);

        //sellers list of plants after
        System.out.println("The sellers list of plants after selling" + seller1.plantsForSale);

    */

    }

}
