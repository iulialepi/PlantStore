import controller.PlantAcquisition;
import model.Client;
import model.Plant;
import repository.inmemory.PlantInMemoryRepo;
import repository.inmemory.ClientInMemoryRepo;
import utils.InvalidCredentialsException;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class SQLTest {
    public static void main(String[] args) throws IOException, InvalidCredentialsException {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            Statement stmt=con.createStatement();

            String plantTable = "create table if not exists plant(plant_id int primary key," +
                    "plant_name varchar(45),plant_price int, plant_quantity int);";
            stmt.execute(plantTable);

            String clientTable = "create table if not exists client(client_id int primary key auto_increment," +
                    "fname varchar(45), lname varchar(45), username varchar(45), password varchar(45),total_credit int);";
            stmt.execute(clientTable);

            String clients_plantsTable = "create table if not exists clients_plants(client_id int primary key," +
                    "plant_id int primary key," +
                    "quantity int," +
                    "foreign key (client_id) references client(client_id)," +
                    "foreign key (plant_id) references plant(plant_id));";
            stmt.execute(clients_plantsTable);

            String receiptTable = "create table if not exists receipt(receipt_id int primary key," +
                    "plant_id_receipt int," +
                    "client_id_receipt int," +
                    "date DATE," +
                    "quantity int," +
                    "foreign key (client_id) references client(client_id)," +
                    "foreign key (plant_id) references plant(plant_id));";
            stmt.execute(receiptTable);
        }
        catch(Exception e){
            System.out.println(e);
        }

        PlantInMemoryRepo plantInMemoryRepo = new PlantInMemoryRepo();
        ClientInMemoryRepo clientInMemoryRepo = new ClientInMemoryRepo();

        PlantAcquisition acquisition = new PlantAcquisition(clientInMemoryRepo, plantInMemoryRepo);


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

            if(username.equals("admin")){
                System.out.println("Add a plant");
                acquisition.addPlantAdmin();
            }

            System.out.println("These are the plants for sale");
            int i=0;
            for(Plant p: acquisition.showAllPlantsForSale()){
                System.out.print(i + ".) ");
                System.out.println(p);
                i++;
            }
            System.out.println("Choose the number of the plant you would like to buy");
            Scanner in5 = new Scanner(System.in);
            int number = in5.nextInt();

            Plant p = acquisition.showAllPlantsForSale().get(number);

            Client client_cart = null;
            for(Client c: clientInMemoryRepo.findAll()){
                if(username.equals(c.getUsername())){
                    client_cart = c;
                }
            }

            System.out.println("Choose how many of the chosen plants you want to buy");
            Scanner in6 = new Scanner(System.in);
            int quantity_buy = in6.nextInt();

            acquisition.addToCart(p, client_cart, quantity_buy);

            System.out.println(acquisition.showAllPlantsForSale());
        }


    }
}