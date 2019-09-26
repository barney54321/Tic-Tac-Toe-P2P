import java.util.*;
import java.io.*;
import java.net.*;

public class Client extends Multiplayer {

    public Client(int port) throws Exception {
        this.port = port;
        this.port = port;
        this.s = new Socket("localhost", port);
        this.din = new DataInputStream(this.s.getInputStream());
        this.dout = new DataOutputStream(this.s.getOutputStream());
    }
    
}