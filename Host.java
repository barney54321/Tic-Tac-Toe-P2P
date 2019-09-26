import java.util.*;
import java.io.*;
import java.net.*;

public class Host extends Multiplayer {

    protected ServerSocket ss; // ServerSocket for hosting the game

    public Host(int port) throws IOException {

        this.port = port; // Save port
        this.ss = new ServerSocket(port); // Create new ServerSocket
        this.s = this.ss.accept(); // Block until client connects
        this.din = new DataInputStream(this.s.getInputStream()); // Set up Input stream
        this.dout = new DataOutputStream(this.s.getOutputStream()); // Set up Output stream

    }

    @Override
    public void close() throws IOException {

        // Close all the Streams, the Socket and the ServerSocket
        this.din.close();
        this.dout.close();
        this.s.close();
        this.ss.close();

    }
}