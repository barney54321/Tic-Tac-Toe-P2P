import java.util.*;
import java.io.*;
import java.net.*;

/**
 * The Client subclass of Multiplayer.
 */
public class Client extends Multiplayer {

    /**
     * Creates a Client and connects to the given port.
     * @param port the port to connect to.
     * @throws IOException
     */
    public Client(int port) throws IOException {

        this.port = port; // Save the port
        this.s = new Socket("localhost", port); // Set up Socket with host
        this.din = new DataInputStream(this.s.getInputStream()); // Set up Input stream
        this.dout = new DataOutputStream(this.s.getOutputStream()); // Set up Output stream

    }
}