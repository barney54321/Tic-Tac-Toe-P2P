import java.util.*;
import java.io.*;
import java.net.*;

public class Game {

    private Multiplayer player; // Manages Socket and the streams
    private Scanner sc; // Reads user input

    private int[][] board; // Stores the board state

    private boolean first; // Does the player move first
    private boolean host; // Is the player hosting

    public Game() throws Exception {

        this.sc = new Scanner(System.in);

        System.out.print("Do you want to join or host? ");
        String response = this.sc.nextLine();

        if (response.toLowerCase().equals("join")) {

            // Join to the host Socket
            System.out.print("What port is the host on? ");
            int port = this.sc.nextInt();
            this.player = new Client(port);
            this.first = false;
            this.host = false;

        } else {

            // Set up host Socket
            System.out.print("What port do you want to host on? ");
            int port = this.sc.nextInt();
            this.player = new Host(port);
            this.first = true;
            this.host = true;

        }

        this.board = new int[3][3];
    }

    public void makeMove() throws Exception {

        // Check if game is over
        if (this.checkGame() != 0) {
            int[] move = {-1, -1};
            this.player.writeArray(move);
            return;
        }

        System.out.print("\nEnter coordinates: ");

        int x = this.sc.nextInt();
        int y = this.sc.nextInt();

        // While coordinates are invalid, force new coordinate values
        if (x < 0 || x > 2 || y < 0 || y > 2 || this.board[x][y] != 0) {
            while (x < 0 || x > 2 || y < 0 || y > 2 || this.board[x][y] != 0) {
                System.out.println("\nINVALID COORDINATES\n");
                System.out.print("Enter coordinates: ");
                x = this.sc.nextInt();
                y = this.sc.nextInt();
            }
        }

        // Place the piece on the board
        if (this.host) {
            this.board[x][y] = 1;
        } else {
            this.board[x][y] = 2;
        }

        // Send coordinates to other player
        int[] coordinates = {x, y};
        this.player.writeArray(coordinates);

    }

    public void waitMove() throws Exception {

        // Block until coordinates are received
        int[] coordinates = this.player.readArray();

        // Game is over if invalid coordinates
        if (coordinates[0] == -1 && coordinates[1] == -1) {
            return;
        }

        // Place opponent's piece
        if (this.host) {
            this.board[coordinates[0]][coordinates[1]] = 2;
        } else {
            this.board[coordinates[0]][coordinates[1]] = 1;
        }

    }

    public int checkGame() {

        // Check horizontals
        for (int i = 0; i < 3; i++) {
            if (this.board[i][0] != 0) {
                if (this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2]) {
                    return this.board[i][0];
                }
            }
        }

        // Check verticals
        for (int i = 0; i < 3; i++) {
            if (this.board[0][i] != 0) {
                if (this.board[0][i] == this.board[1][i] && this.board[0][i] == this.board[2][i]) {
                    return this.board[0][i];
                }
            }
        }

        // Check diagonals
        if (this.board[1][1] != 0) {
            if (this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2]) {
                return this.board[0][0];
            }

            if (this.board[2][0] == this.board[1][1] && this.board[2][0] == this.board[0][2]) {
                return this.board[0][2];
            }
        }

        // Check for draw
        int space = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.board[i][j] == 0) {
                    space = 0;
                }
            }
        }

        return space;

    }

    public void printBoard() {

        System.out.println("");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    System.out.print(" ");
                } else if (board[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }

                if (j == 0 || j == 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i == 0 || i == 1) {
                System.out.println("-----");
            }
        }

    }

    public boolean play() throws Exception {

        if (this.first) {
            this.printBoard();
            this.makeMove();
            this.printBoard();
            this.waitMove();
        } else {
            this.waitMove();
            this.printBoard();
            this.makeMove();
            this.printBoard();
        }

        boolean result = true;

        if (this.checkGame() != 0) {
            result = false;
        }

        return result;
    }

    public void end() throws Exception {

        this.player.close();

    }

    public void printResult() {

        int res = this.checkGame();
        if (res == -1) {
            System.out.println("DRAW");
        } else if (res == 1 && this.host) {
            System.out.println("YOU WIN");
        } else if (res == 2 && !this.host) {
            System.out.println("YOU WIN");
        } else {
            System.out.println("YOU LOSE");
        }
    }

    public static void main(String[] args) throws Exception {

        // Create new Game object
        Game g = new Game();

        // Continue to make moves until result is achieved
        boolean play = g.play();
        while (play) {
            play = g.play();
        }

        // Print result and close Sockets and streams
        g.printResult();
        g.end();

    }
}