package controller;

import model.Client;
import model.Plant;

import repository.inmemory.ClientInMemoryRepo;
import repository.inmemory.PlantInMemoryRepo;
import utils.InvalidCredentialsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.*;
import java.util.Scanner;

public class PlantAcquisition {

    public ClientInMemoryRepo clientInMemoryRepo;
    public PlantInMemoryRepo plantInMemoryRepo;

    public PlantAcquisition(ClientInMemoryRepo clientInMemoryRepo, PlantInMemoryRepo plantInMemoryRepo){
        this.clientInMemoryRepo = clientInMemoryRepo;
        this.plantInMemoryRepo = plantInMemoryRepo;
    }



    public List<Plant> showAllPlantsForSale(){
        List<Plant> plantList = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            Statement stmt=con.createStatement();

            String select = "select * from plant";
            //stmt.execute(select);

            ResultSet rs;
            rs = stmt.executeQuery(select);

            while (rs.next()) {
                int id = rs.getInt("plant_id");
                //plant.setId(rs.getInt("plant_id"));
                String plantName = rs.getString("plant_name");
                int price = rs.getInt("plant_price");
                int quantity = rs.getInt("plant_quantity");
                Plant plant = new Plant(id, plantName, price, quantity);

                plantList.add(plant);
            }
            /*
            List<Plant> plantList = plantInMemoryRepo.findAll();
            for(int i = 0; i < plantList.size(); i++){
                System.out.println(i + ") " + plantList.get(i));
            }

             */

        } catch (Exception e) {
            System.out.println(e);
        }
        return plantList;
    }

    //user login
    public boolean login(String username, String password) throws InvalidCredentialsException{
        Client client = clientInMemoryRepo.findByUsernameAndPassword(username, password);

        // throw an exception on invalid credentials
        if(client == null){
            throw new InvalidCredentialsException("Credentials are not valid");
        }
        if(password == null){
            throw new InvalidCredentialsException("Credentials are not valid");
        }
        return true;
    }

    public boolean addPlantAdmin(){
        System.out.println("Enter id: ");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();

        List<Plant> plantList = showAllPlantsForSale();

        for(Plant p:plantList){
            if(p.getId() == id){
                System.out.println("Already exists!! Try again");
                return false;
            }
        }

        System.out.println("Enter a name: ");
        Scanner in4 = new Scanner(System.in);
        String name = in4.nextLine();

        System.out.println("Enter a price: ");
        Scanner in2 = new Scanner(System.in);
        int price = in2.nextInt();

        System.out.println("Enter quantity: ");
        Scanner in3 = new Scanner(System.in);
        int quantity = in3.nextInt();


        Plant p = new Plant(id, name, price, quantity);
        plantInMemoryRepo.add(p);

        return true;

    }

    //this function returns a list of plants with the chosen name from all sellers
    //from all the plants

    public HashMap<Plant, Integer> nameFilter(int choice){
        HashMap<Plant, Integer> searched = new HashMap<>();

        for(int i=0; i< plantInMemoryRepo.plants.size();i++){
            if(i == choice){
                for(Plant p:plantInMemoryRepo.plants){

                    //if the seller has the plant we are searching for
                    /*
                    if(s.plantsForSale.containsKey(plantInMemoryRepo.plants.get(i))) {
                        searched.put(plantInMemoryRepo.plants.get(i),10);
                    }
                    */
                }

            }
        }




        //aici am gresit ca trebiue returnate valorile de la toti selleri nu doar de la plante
        /*
        for(int i=0; i< plantInMemoryRepo.plants.size();i++){
            if(i == choice){
                //the plant chosen by the user
                plantName = plantInMemoryRepo.plants.get(i).getPlantName();
            }
        }

        for(Plant p:plantInMemoryRepo.plants){

            //we create and add to a list because there can be more plants from other sellers with the same name
            if(p.getPlantName().equals(plantName)) {
                searched.add(p);
            }
        }

         */
        return searched;
    }


    //receipt of one plant
    //quantity = how many plants added to cart
    //function that returns true if plant was successfully added to cart
    public boolean addToCart(Plant plant, Client client, int quantity){
        //client does not have enough credit
        if(client.getTotalCredit() < plant.getPrice()*quantity){
            System.out.println("Not enough credit :((");
            return false;
        }
        //there are no plants in stock
        if(plant.getQuantity() < quantity){
            System.out.println("Not enough plants, try again at another time :((");
            return false;
        }
        //the clients credit after buying
        client.totalCredit -= plant.price * quantity;

        //stock after buying
        plant.setQuantity(plant.getQuantity() - quantity);

        System.out.println(plant.plantName + " was successfully added " + quantity + " times");

        return true;
    }


}
