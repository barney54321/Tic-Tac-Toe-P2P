import java.util.*;
import java.io.*;
import java.net.*;

public class Host extends Multiplayer {

    protected ServerSocket ss;

    public Host(int port) throws Exception {
        this.port = port;
        this.ss = new ServerSocket(port);
        this.s = this.ss.accept();
        this.din = new DataInputStream(this.s.getInputStream());
        this.dout = new DataOutputStream(this.s.getOutputStream());
    }

    public void close() throws Exception {

        this.din.close();
        this.dout.close();
        this.s.close();
        this.ss.close();

    }
}