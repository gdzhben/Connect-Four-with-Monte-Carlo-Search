package connectfour;

/**
 * 12252511 Zhesi NING
 *
 */
import static java.lang.System.out;
import static java.lang.String.format;
import java.util.Scanner;

public class MainGame {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        play();
    }

    public static void play() throws Exception {

        out.println("Pls choose one of the flollowing option:");
        out.println("1-Human v. Computer\n2-Computer v. Computer\n3- report experiment");
        out.println();
        String input = scanner.next();
        switch (Integer.parseInt(input)) {
            case 1:
                GameBoard board = inputBoard();
                out.println("Enter effort Setting for the computer");
                input = scanner.next();
                Player computer = new ComputerPlayer(Integer.parseInt(input));
                Player human = new HumanPlayer();
                CFGameLogic game = new CFGameLogic(human, computer, board);
                game.play(false);
                break;
            case 2:
                board = inputBoard();
                out.println("pls enter effort level for the first computer player");
                input = scanner.next();
                computer = new ComputerPlayer(Integer.parseInt(input));
                out.println("pls enter effort level for the second computer player");
                input = scanner.next();
                Player computer2 = new ComputerPlayer(Integer.parseInt(input));
                game = new CFGameLogic(computer, computer2, board);
                game.play(false);
                break;
            case 3:
                experimentForReport();
        }
    }

    private static void experimentForReport() throws Exception {
        int noOfRun = 100;
        int[] effortList = new int[]{80, 150, 500, 2000};
        int p1win = 0, p2win = 0, draw = 0;
        System.out.println("X-effort level\t\tY-effort level\tNo.Xwins\tNo.Ywins\tNo.Draw");
        for (int firstEffort : effortList) {
            Player p1 = new ComputerPlayer(firstEffort);
            for (int secondEffort : effortList) {
                Player p2 = new ComputerPlayer(secondEffort);
                GameBoard board = GameBoard.create("7 6 3 5 6 7", "A4 B4");
                for (int i = 0; i < noOfRun; i++) {
                    CFGameLogic game = new CFGameLogic(p1, p2, board);

                    Player player = game.play(true);
                    if (player == null) {
                        draw++;
                    } else if (player == p1) {
                        p1win++;
                    } else {
                        p2win++;
                    }
                }
                System.out.println(firstEffort + "\t" + secondEffort + "\t" + p1win + "\t" + p2win + "\t" + draw);
            }
        }
    }

    public static GameBoard inputBoard() throws Exception {
        out.println("Enter the heights of the columns:");

        String heights = scanner.next();
        out.println("Enter the first dont count cells: (like A3)");

        String firstIgnoreCells = scanner.next();
        out.println("Enter the second dont count cells: (like A3)");

        String secondIgnoreCells = scanner.next();
        String ignoreCells = firstIgnoreCells + " " + secondIgnoreCells;

        return GameBoard.create(heights, ignoreCells);
    }

}
