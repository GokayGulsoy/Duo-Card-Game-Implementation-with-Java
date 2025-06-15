package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import app.Player;

/**
 * FileIO class performs reading and writing
 * operations for CSV file at the beginning
 * and at the end of rounds. Also it performs
 * writing operations at the start of the game
 * and at the end of the game
 * 
 * @author Gökay Gülsoy
 * @version 1.0.0
 * @since 20/03/2025
 */
public class FileIO {

    private final String FILE_NAME;

    /**
     * constructor that initializes the FILE_NAME
     * data memeber with the "game_status.csv" as a
     * CSV file name
     */
    public FileIO() {
        this.FILE_NAME = "Gökay_Gülsoy_CENG431_HW1\\DuoCardGameApplication\\src\\game_status.csv";
    }

    /**
     * creates CSV file and header according to number of players
     * 
     * @param players <b>ArrayList</b> that contains the
     *                players of the game
     */
    public void createCSVFileAndHeader(ArrayList<Player> players) {

        try {
            // creates the CSV file and header
            File csvFile = new File(FILE_NAME);
            if (csvFile.exists()) {
                csvFile.delete();
            }

            csvFile.createNewFile();

            FileWriter csvWriter = new FileWriter(FILE_NAME);
            String header = "Round,";
            for (int i = 0; i < players.size(); i++) {
                if (i != players.size() - 1) {
                    header += players.get(i).getName() + ",";
                }

                else {
                    header += players.get(i).getName();
                }
            }

            csvWriter.write(header);
            csvWriter.close();
        }

        catch (IOException e) {
            System.out.println("An error occurred while creating game_status.csv");
            e.printStackTrace();
        }
    }

    /**
     * writes score for each player to game_status.csv file's
     * appropriate line according to roundNumber
     * 
     * @param players     ArrayList that contains the players of the game
     * @param roundNumber int that is the current round number
     */
    public void writeRoundInformation(ArrayList<Player> players, int roundNumber) {
        try {
            FileWriter csvWriter = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedCSVWriter = new BufferedWriter(csvWriter);

            bufferedCSVWriter.newLine();
            String roundInfo = "Round " + roundNumber + ",";

            for (int i = 0; i < players.size(); i++) {
                if (i != players.size() - 1) {
                    roundInfo += players.get(i).getScore() + ",";
                }

                else {

                    roundInfo += players.get(i).getScore();
                }
            }

            bufferedCSVWriter.write(roundInfo);
            bufferedCSVWriter.close();
        }

        catch (IOException e) {
            System.out.println("An error occured while writing round information to game_status.csv file");
            e.printStackTrace();
        }

    }

    /**
     * writes winner's name to game_status.csv file as a last line
     * 
     * @param winner Player object which represents the winner of the game
     */
    public void writeWinnerInformation(Player winner) {
        try {
            FileWriter csvWriter = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedCSVWriter = new BufferedWriter(csvWriter);

            String winnerInfo = "Winner," + winner.getName();
            bufferedCSVWriter.newLine();
            bufferedCSVWriter.write(winnerInfo);

            bufferedCSVWriter.close();
        }

        catch (IOException e) {
            System.out.println("An error occured while writing winner information to game_status.csv file");
            e.printStackTrace();
        }

    }

    /**
     * sets score of each player as the score from previous round
     * 
     * @param players          ArrayList of Player objects that contains the players
     *                         of game
     * @param numOfLinesToPass int that indicates number of lines to be read in
     *                         advance
     *                         to reach line that contains the scores for previous
     *                         round
     */
    public void readScoresFromCSVFile(ArrayList<Player> players, int numOfLinesToPass) {
        try {
            File csvFile = new File(FILE_NAME);
            Scanner csvReader = new Scanner(csvFile);

            // read the header line
            csvReader.nextLine();

            int passedLines = 0;
            while (passedLines < numOfLinesToPass) {
                csvReader.nextLine();
                passedLines++;
            }

            // after reaching appropriate line parse
            // it and read score for each player
            StringTokenizer tokenizer = new StringTokenizer(csvReader.nextLine(), ",");
            // consume first token (Round Number)
            tokenizer.nextToken();

            for (Player player : players) {
                player.setScore(Integer.parseInt(tokenizer.nextToken()));
            }

            csvReader.close();
        }

        catch (IOException e) {
            System.out.println("An error occured while reading scores from game_status.csv file");
            e.printStackTrace();
        }

    }

}
