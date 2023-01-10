package repository;

import model.Client;

import java.io.IOException;
import java.util.List;

public interface ClientRepo  extends ICrudRepo<Long, Client> {
    public Client findByUsernameAndPassword(String username, String password);

    public List<Client> readFromFile() throws IOException;
    //sau file not found exception?
}
