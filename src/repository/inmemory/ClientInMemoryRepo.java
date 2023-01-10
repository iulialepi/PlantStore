package repository.inmemory;

import model.Client;
import model.Plant;
import repository.ClientRepo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ClientInMemoryRepo implements ClientRepo {
    private List<Client> clients;

    public ClientInMemoryRepo() throws IOException {
        this.clients = new ArrayList<>();
        //populateClients();
        clients = readFromFile();
    }

    public void populateClients(){
        List<Plant> plantList = new ArrayList<>();
        Client client1 = new Client(200, "Iulia", "Lepinzan", plantList, 500, "iulialepi", "1234");
        Client client2 = new Client(200, "Raul", "Barbat", plantList, 600, "raulb", "raulecool");

        this.clients.add(client1);
        this.clients.add(client2);
    }

    @Override
    public void add(Client entity) {
        for(Client c: this.clients){
            if(c.getId() == entity.getId()){
                return;
            }
        }
        this.clients.add(entity);
    }

    @Override
    public Client delete(Long id) {
        for(Client c: this.clients){
            if(c.getId() == id){
                this.clients.remove(c);
                return c;
            }
        }
        return null;
    }

    @Override
    public Client update(Long id, Client entity) {
        for(Client c: this.clients){
            if(c.getId() == id){
                c.setFirstName(entity.getFirstName());
                c.setLastName(entity.getLastName());
                c.setOwnedPlants(entity.getOwnedPlants());
                return c;
            }
        }
        return null;
    }

    @Override
    public Client findById(Long id) {
        for(Client client: clients){
            if(client.getId() == id){
                return client;
            }
        }
        return null;
    }

    @Override
    public Iterable<Client> findAll() {
        return clients;
    }

    @Override
    public Client findByUsernameAndPassword(String username, String password){
        for(Client client: clients){
            if(Objects.equals(client.getUsername(), username) && Objects.equals(client.getPassword(), password)){
                return client;
            }
        }
        return null;
    }

    @Override
    public List<Client> readFromFile() throws IOException {
        List<Client> clientList = new ArrayList<>();

        File file= new File("C:\\Users\\Iulia\\Documents\\UBB_MI\\An III\\Sem1\\+MetodeAvans\\Lab\\YuRaPlantStore\\src\\client.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] values;

        while(line != null){
            values = line.split(",");

            int id = Integer.parseInt(values[0]);
            String fname = values[1];
            String lname = values[2];
            int credit = Integer.parseInt(values[3]);
            String user = values[4];
            String pass = values[5];
            List<Plant> plantList = new ArrayList<>();
            Client client = new Client(id,fname,lname,plantList,credit,user,pass);

            clientList.add(client);

            line = reader.readLine();
        }
        return clientList;
    }

    @Override
    public String toString() {
        return "ClientRepo{" +
                "clients=" + clients +
                '}';
    }
}
