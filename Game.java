import java.util.*;
import java.io.*;
import java.net.*;

public class Game {

    private Multiplayer player;
    private Scanner sc;

    public Game() throws Exception {
        this.sc = new Scanner(System.in);

        System.out.print("Do you want to join or host? ");
        String response = this.sc.nextLine();

        if (response.toLowerCase().equals("join")) {

            System.out.print("What port is the host on? ");
            int port = this.sc.nextInt();
            this.player = new Client(port);

        } else {

            System.out.print("What port do you want to host on? ");
            int port = this.sc.nextInt();
            this.player = new Host(port);
        }
    }

    public boolean play() {
        return true;
    }

    public void end() {

    }

    public static void main(String[] args) throws Exception {

        Game g = new Game();

        boolean play = g.play();
        while (play) {
            play = g.play();
        }

        g.end();
    }
}