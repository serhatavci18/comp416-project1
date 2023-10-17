import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Server {
    public static void main(String... args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter welcoming socket port : ");
        int port = Integer.parseInt(scanner.nextLine());
        System.out.println("Waiting for client to connect...");

        try (
                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Client socket: /127.0.0.1:" + clientSocket.getLocalPort());
            System.out.println("Player 1, please enter your name :");
            String player1Name = scanner.nextLine();
            System.out.println("Waiting for player 2 name...");

            out.println(player1Name);

            String player2Name = in.readLine();

            System.out.println("You will be playing with " + player2Name);

            Random rand = new Random();

            int player1Score = 0;
            int player2Score = 0;

            for (int i = 0; i < 3 ; i++){
                int xTarget = rand.nextInt(256);
                int yTarget = rand.nextInt(256);

                System.out.println(player1Name + ", please enter your x and y guesses, comma separated. : ");
                String player1Input = scanner.nextLine();

                String[] inputParts = player1Input.split(",");
                if (inputParts.length == 2) {
                    try {
                        int xGuessP1 = Integer.parseInt(inputParts[0].trim());
                        int yGuessP1 = Integer.parseInt(inputParts[1].trim());

                        int player1Closeness = Math.abs((yGuessP1-yTarget)*(yGuessP1-yTarget)+(xGuessP1-xTarget)*(xGuessP1-xTarget));

                        System.out.println("Waiting for player 2 to guess...");

                        int xGuessP2 = Integer.parseInt(in.readLine());
                        int yGuessP2 = Integer.parseInt(in.readLine());

                        int player2Closeness = Math.abs((yGuessP2-yTarget)*(yGuessP2-yTarget)+(xGuessP2-xTarget)*(xGuessP2-xTarget));

                        if (player1Closeness < player2Closeness){
                            System.out.println("Winner for round " + (i+1) + " is " + player1Name);
                            out.println("Winner for round " + (i+1) + " is " + player1Name);
                            player1Score++;
                        }else if(player1Closeness > player2Closeness){
                            System.out.println("Winner for round " + (i+1) + " is " + player2Name);
                            out.println("Winner for round " + (i+1) + " is " + player2Name);
                            player2Score++;
                        }else{
                            System.out.println("Winner for round " + (i+1) + " is both players");
                            out.println("Winner for round " + (i+1) + " is both players" );
                        }


                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter two valid integer values separated by a comma.");
                    }
                } else {
                    System.out.println("Invalid input format. Please enter two integer values separated by a comma.");
                }

            }
            if(player1Score > player2Score){
                System.out.println("Game winner is " + player1Name);
                out.println("Game winner is " + player1Name);
            }else if(player1Score < player2Score){
                System.out.println("Game winner is " + player2Name);
                out.println("Game winner is " + player2Name);
            }else{
                System.out.println("Game ended in a tie");
                out.println("Game ended in a tie");
            }


        }
    }
}
