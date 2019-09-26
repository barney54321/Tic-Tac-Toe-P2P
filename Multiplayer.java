import java.util.*;
import java.io.*;
import java.net.*;

public class Multiplayer {

    protected int port;

    protected Socket s;

    protected DataInputStream din;
    protected DataOutputStream dout;

    public void writeArray(int[] arr) throws Exception {

        this.dout.writeInt(arr.length);
        for (int i : arr) {
            this.dout.writeInt(i);
        }
        this.dout.flush();

    }

    public int[] readArray() throws Exception {

        int length = this.din.readInt();
        int[] res = new int[length];

        for (int i = 0; i < length; i++) {
            res[i] = this.din.readInt();
        }

        return res;
    }

    public void close() throws Exception {

        this.din.close();
        this.dout.close();
        this.s.close();

    }

}