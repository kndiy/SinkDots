import java.util.ArrayList;
import java.util.Random;

public class Target {
    private ArrayList<String> targetLocation = new ArrayList<>();
    private String targetName = new String();
    public String getTargetName(){
        return targetName;
    }

    public ArrayList<String> getTargetLocation(){
        return targetLocation;
    }
    public void setTargetName(String targetName){
        this.targetName = targetName;
    }
    ArrayList<String> setSubLocation(){
        Random random = new Random();
        int direction = random.nextInt(2);
        int x = 0;
        int y = 0;
        String Coordinate;
        StringBuilder builder = new StringBuilder();
        ArrayList<String> firstLoc = new ArrayList<>();

        if (direction == 0) {
            x = random.nextInt(5);
            y = random.nextInt(7);
            for (int i = 0; i < 3; i++) {
                builder.append(convertToChar(x + i));
                builder.append((char)(y+'0'));
                firstLoc.add(builder.toString());
                builder.setLength(0);
            }
        }
        else {
            x = random.nextInt(7);
            y = random.nextInt(5);
            for (int i = 0; i < 3; i++) {
                builder.append(convertToChar(x));
                builder.append((char)((y+i)+'0'));
                firstLoc.add(builder.toString());
                builder.setLength(0);
            }
        }
        return firstLoc;
    }
    public void setTargetLocation(ArrayList<String> compare){
        ArrayList<String> currentLoc = setSubLocation();
        for(String loc : currentLoc) {
            if (compare.contains(loc)) {
                currentLoc = setSubLocation();
                continue;
            }
        }
        targetLocation = currentLoc;
    }

    char convertToChar(int firstCoordinate){
        char afterConvert = ' ';
        switch(firstCoordinate){
            case 0 : afterConvert = 'a';
                break;
            case 1 : afterConvert = 'b';
                break;
            case 2 : afterConvert = 'c';
                break;
            case 3 : afterConvert = 'd';
                break;
            case 4 : afterConvert = 'e';
                break;
            case 5 : afterConvert = 'f';
                break;
            case 6 : afterConvert = 'g';
                break;
        }
        return afterConvert;
    }

    public String checkGuessSub(String guess){
        String result = "Miss";
        for (String loc : targetLocation) {
            int checkIndex = targetLocation.indexOf(guess);
            if (checkIndex != -1) {
                targetLocation.remove(checkIndex);
                result = "Hit";
                break;
            }
        }
        if (targetLocation.isEmpty()){
            result = "Kill";
        }
        return result;
    }
}
