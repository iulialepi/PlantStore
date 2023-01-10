package test;

import controller.PlantAcquisition;
import repository.inmemory.PlantInMemoryRepo;
import repository.inmemory.ClientInMemoryRepo;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class SystemTest {
    private PlantAcquisition controller;
    private PlantInMemoryRepo plantInMemoryRepo;
    private ClientInMemoryRepo clientInMemoryRepo;

    @BeforeEach
    void setUp() throws IOException {
        plantInMemoryRepo = new PlantInMemoryRepo();
        clientInMemoryRepo = new ClientInMemoryRepo();
        controller = new PlantAcquisition(clientInMemoryRepo, plantInMemoryRepo);
    }



    /*
    //why does the exercise from the seminar create a repo then instantiate an in memory repo
    private UserRepository userRepository;
    userRepository = new UserRepositoryMemory();

    @Test
    void testSuccessfulLogin() throws InvalidCredentialsException {
        assertTrue(server.login("ion", "1234"));
    }

    @Test
    void testUnsuccessfulLogin(){
        //assertFalse(server.login("ion", "12345"));
        assertThrows(InvalidCredentialsException.class, () -> server.login("ion", "12345"));
        Throwable exception = assertThrows(InvalidCredentialsException.class, () -> server.login("ion", "12345"));
        assertEquals(exception.getMessage(), "Credentials are not valid");

    }

    @Test
    void testTransferMessages() throws InvalidCredentialsException {
        server.login("ion", "1234");
        User sender = userRepository.findById("ion");
        User receiver = userRepository.findById("marie");
        server.sendMessage(new Message(sender, receiver, "Salut!!", 4));
        server.login("marie", "12345");
        assertEquals(receiver.getPending().size(), 0);
        assertTrue(receiver.getPending().isEmpty());
    }

    @Test
    void sendMessageToNonFriend() throws InvalidCredentialsException {
        server.login("ion", "1234");
        User sender = userRepository.findById("ion");
        User receiver = userRepository.findById("gheorghe");
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> server.sendMessage(new Message(sender, receiver, "Salut!!", 5) ));
        assertEquals(exception.getMessage(), "Sender is not valid as you are not friends");
        //server.login("marie", "12345");
    }
     */
}


/*

import chat.utils.InvalidCredentialsException;

class ServerTest {
}
 */