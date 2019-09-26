import java.util.*;
import java.io.*;
import java.net.*;

public abstract class Multiplayer {

    protected int port; // The port used for the Server
    protected Socket s; // The socket used to connect to server
    protected DataInputStream din; // Input stream
    protected DataOutputStream dout; // Output stream

    /**
     * Writes an int array to the DataOutput Stream.
     * @param arr the int array to be sent to stream
     * @throws IOException
     */     
    public void writeArray(int[] arr) throws IOException {

        // Write the length as the first number
        this.dout.writeInt(arr.length);

        // Append each number in array to stream
        for (int i : arr) {
            this.dout.writeInt(i);
        }

        // Flush the stream
        this.dout.flush();

    }

    /**
     * Reads an int array from the DataInput Stream.
     * @return the coordinates of the opponent's piece
     * @throws IOException
     */ 
    public int[] readArray() throws IOException {

        // Read in the length of the array
        int length = this.din.readInt();

        // Set up array with provided length
        int[] res = new int[length];

        // Read in all subsequent numbers and append to array
        for (int i = 0; i < length; i++) {
            res[i] = this.din.readInt();
        }

        return res;
    }

    /**
     * Closes the streams and the Socket
     * @throws IOException
     */ 
    public void close() throws IOException {

        this.din.close();
        this.dout.close();
        this.s.close();

    }

}