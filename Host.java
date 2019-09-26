import java.util.*;
import java.io.*;
import java.net.*;

/**
 * The Host subclass of Multiplayer.
 */
public class Host extends Multiplayer {

    /**
     * Hosts the Game.
     */
    protected ServerSocket ss;

    /**
     * Creates a Host at the given port.
     * @param port the port to host the server on.
     * @throws IOException
     */
    public Host(int port) throws IOException {

        this.port = port; // Save port
        this.ss = new ServerSocket(port); // Create new ServerSocket
        this.s = this.ss.accept(); // Block until client connects
        this.din = new DataInputStream(this.s.getInputStream()); // Set up Input stream
        this.dout = new DataOutputStream(this.s.getOutputStream()); // Set up Output stream

    }

    /**
     * Closes the streams, the Socket and the ServerSocket.
     * @throws IOException
     */ 
    @Override
    public void close() throws IOException {

        this.din.close();
        this.dout.close();
        this.s.close();
        this.ss.close();

    }
}