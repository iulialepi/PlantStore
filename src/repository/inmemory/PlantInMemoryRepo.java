package repository.inmemory;

import model.Plant;
import repository.PlantRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantInMemoryRepo implements PlantRepo{

    public List<Plant> plants;

    public PlantInMemoryRepo(){
        this.plants = new ArrayList<>();
        //populatePlants();
    }

    public void populatePlants(){
        Plant plant1 = new Plant(100, "Monstera", 39, 55);
        Plant plant2 = new Plant(101, "Pothos", 48, 40);
        Plant plant3 = new Plant(102, "RubberPlant", 25, 23);
        Plant plant4 = new Plant(103, "Ficus", 28, 15);
        Plant plant5 = new Plant(104, "Evergreen", 41, 28);

        this.plants.add(plant1);
        this.plants.add(plant2);
        this.plants.add(plant3);
        this.plants.add(plant4);
        this.plants.add(plant5);

    }

    @Override
    public void add(Plant entity) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            PreparedStatement statement = con.prepareStatement("INSERT INTO plant (plant_id, plant_name, plant_price, plant_quantity)VALUES (?, ?, ?, ?);");

            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getPlantName());
            statement.setInt(3, entity.getPrice());
            statement.setInt(4, entity.getQuantity());
            int row = statement.executeUpdate();

            if(row == 0) {
                return;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        /*
        for(Plant p: this.plants){
            if(p.getId() == entity.getId()){
                return;
            }
        }
        this.plants.add(entity);

         */
    }

    @Override
    public Plant delete(Integer id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            PreparedStatement statement = con.prepareStatement("delete from plant where plant.plant_id = ?;");

            statement.setInt(1, id);
            int row = statement.executeUpdate();



        } catch (Exception e) {
            System.out.println(e);
        }
        /*
        if(plants.contains(plants.get(id))){
            Plant c = plants.get(id);
            plants.remove(plants.get(id));
            return c;
        }
        return null;

         */
        return null;
    }

    @Override
    public Plant update(Integer id, Plant entity) {
        for(Plant p: this.plants){
            if(p.getId() == id){
                p.setPlantName(entity.getPlantName());
                p.setPrice(entity.getPrice());
                //here we will have the possibility to update any attribute except for the id
                //each field will be replaced even though it might be the same value
                return p;
            }
        }
        return entity;
    }

    @Override
    public Plant findById(Integer id) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            PreparedStatement stmt = con.prepareStatement("select * from plant where plant.plant_id = ?");

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            //INSERT INTO plant (plant_id, plant_name, plant_price, plant_quantity)
            if(rs.next()) {
                int plant_id = rs.getInt("plant_id");
                String name = rs.getString("plant_name");
                int price = rs.getInt("plant_price");
                int quantity = rs.getInt("plant_quantity");
                Plant p = new Plant(plant_id, name, price, quantity);
                return p;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;

        /*
        for(Plant plant: plants){
            if(plant.getId() == id){
                return plant;
            }
        }
        return null;

         */
    }

    @Override
    public Plant findByPlantName(String name) {

        for(Plant plant: plants){
            if(plant.getPlantName() == name){
                return plant;
            }
        }
        return null;
    }

    @Override
    public List<Plant> findAll() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plantstoremap", "root", "mysql1234");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from plant");

            Plant p = null;
            this.plants.clear();

            if(rs.next()){
                do{
                    int plant_id = rs.getInt("plant_id");
                    String name = rs.getString("plant_name");
                    int price = rs.getInt("plant_price");
                    int quantity = rs.getInt("plant_quantity");
                    p = new Plant(plant_id, name, price, quantity);

                    if(!plants.contains(p)){
                        plants.add(p);
                    }
                }while(rs.next());

                return plants;
            }
            else{
                return null;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return null;

        //return plants;
    }

    @Override
    public String toString() {
        return "PlantRepo{" +
                "plants=" + plants +
                '}';
    }



}
