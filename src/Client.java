import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String... args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter server socket's port : ");
        int port = Integer.parseInt(scanner.nextLine());

        try (
                Socket socket = new Socket("localhost", port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println("Binding to port localhost: " + port);
            System.out.println("Server socket : localhost/127.0.0.1:" + port);

            String player1Name = in.readLine();

            System.out.println("Player 2, you will be playing with " + player1Name + ", please enter your name : ");
            String player2Name = scanner.nextLine();

            out.println(player2Name);

            for(int i = 0; i< 3; i++){
                System.out.println(player2Name + ", please enter your x and y guesses, comma separated. : ");
                String player2Input = scanner.nextLine();

                String[] inputParts = player2Input.split(",");
                if (inputParts.length == 2) {
                    try {
                        int xGuess = Integer.parseInt(inputParts[0].trim());
                        int yGuess = Integer.parseInt(inputParts[1].trim());

                        out.println(xGuess);
                        out.println(yGuess);

                        System.out.println(in.readLine());

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter two valid integer values separated by a comma.");
                    }
                } else {
                    System.out.println("Invalid input format. Please enter two integer values separated by a comma.");
                }
            }
            System.out.println(in.readLine());

        }
    }
}
