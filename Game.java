import java.util.*;
import java.io.*;
import java.net.*;

public class Game {

    private Multiplayer player;
    private Scanner sc;

    public Game() {
        this.sc = new Scanner(System.in);

        System.out.print("Do you want to join or host? ");
        String response = this.sc.nextLine();

        if (response.toLowerCase().equals("join")) {

            System.out.print("What port is the host on? ");
            int port = this.sc.nextInt();
            this.player = new Client(port);

        } else {
            this.player = new Host();
        }
    }
}