import java.util.*;
import java.io.*;
import java.net.*;

public class Client extends Multiplayer {

    public Client(int port) throws IOException {

        this.port = port; // Save the port
        this.s = new Socket("localhost", port); // Set up Socket with host
        this.din = new DataInputStream(this.s.getInputStream()); // Set up Input stream
        this.dout = new DataOutputStream(this.s.getOutputStream()); // Set up Output stream

    }
}