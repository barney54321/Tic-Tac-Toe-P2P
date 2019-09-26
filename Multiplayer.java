import java.util.*;
import java.io.*;
import java.net.*;

public class Multiplayer {

    protected int port;

    protected Socket s;

    protected DataInputStream din;
    protected DataOutputStream dout;

    public void writeArray(int[] arr) throws Exception {

    }

    public int[] readArray() throws Exception {

        return null;
    }

    public void close() throws Exception {

        this.din.close();
        this.dout.close();
        this.s.close();

    }

}