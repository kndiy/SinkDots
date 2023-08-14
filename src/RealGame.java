import java.util.ArrayList;
import java.util.Scanner;

public class RealGame {
    private ArrayList<Target> targetList = new ArrayList<>();

    private int numOfGuess = 0;

    public void playGame(){
        setupGame();
        while (true) {
            String guess = getPlayerGuess();
            numOfGuess++;
            checkGuess(guess);
            if (targetList.isEmpty()) {
                break;
            }
        }
        endGame();
    }
    void checkGuess(String guess){
        String bigResult = "You missed...";
        for (Target target : targetList) {
            String result = target.checkGuessSub(guess);
            if (result.equals("Hit")) {
                String targetName = target.getTargetName();
                bigResult = "You hit " + targetName + " at " + guess + " Coordination";
                break;
            }
            else if (result.equals("Kill")) {
                String targetName = target.getTargetName();
                bigResult = "You killed " + targetName + " !!!";
                targetList.remove(target);
                break;
            }
        }
        System.out.println(bigResult);
    }

    void setupGame(){
        Target one = new Target();
        Target two = new Target();
        Target three = new Target();

        targetList.add(one);
        targetList.add(two);
        targetList.add(three);

        one.setTargetName("Submarine");
        two.setTargetName("Plane");
        three.setTargetName("Cruiser");

        ArrayList<String> accruedLocation = new ArrayList<>();

        for (Target target : targetList) {
            target.setTargetLocation(accruedLocation);
            accruedLocation.addAll(target.getTargetLocation());
        }

        for (Target target : targetList) {
            System.out.print(target.getTargetName() + " At ");
            System.out.println(target.getTargetLocation().toString());
        }
    }

    String getPlayerGuess(){
        String inputLine;
        Scanner scanner = new Scanner(System.in);
        inputLine = scanner.next().toLowerCase();
        return inputLine;
    }
    void endGame(){
        System.out.println("Game Over!");
        System.out.println("You took " + numOfGuess + " guesses!");
    }

}
