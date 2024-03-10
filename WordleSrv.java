import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class WordleSrv {
    private static final String[] WORDS = {"avion", "mesa", "cabeza", "flor", "perro", "amarillo", "falda", "peach"};

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server waiting for connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String wordToGuess = getRandomWord();
            System.out.println("Word to guess: " + wordToGuess);

            out.println("Welcome to Wordle!");
            out.println("Guess the word by typing it in.");

            String guess;
            boolean guessed = false;
            while (!guessed) {
                guess = in.readLine();
                System.out.println("Received guess: " + guess);
                if (guess.equals(wordToGuess)) {
                    out.println("Congratulations! You guessed the word.");
                    guessed = true;
                } else {
                    out.println("Incorrect guess. Try again.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }
}
