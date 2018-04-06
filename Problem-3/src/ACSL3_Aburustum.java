import java.util.Scanner;

public class ACSL3_Aburustum
{
    enum Direction {
        RIGHT (0),
        LEFT (180),
        ABOVE (90),
        BELOW (270);

        private int numVal;

        Direction(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        boolean running = true;

        System.out.println("Please input the list of Array values in Hex, comma and space delimited. Ex: '4F9D39, DEB456, 3DA8B9, A57CA7, 26A84A, 2FCFA3, 3AAB09, 89CBF5'");
        String arrayInput = reader.nextLine();
        String[] inputRows = arrayInput.split(", ");
        int[][] array = getRowVals(inputRows);

        while (running) {

            System.out.println("\nPlease input an input line, comma and space delimited. Ex: '1, 2, L, 2'. Enter q to quit.");
            String input = reader.nextLine();
            if (input.toLowerCase().equals("q")) {
                running = false;
                continue;
            }
            String[] inputArr = input.split(", ");

            int[] curPos = new int[2];
            curPos[0] = Character.getNumericValue(inputArr[0].charAt(0));
            curPos[1] = Character.getNumericValue(inputArr[1].charAt(0));
            Direction entryDirection = getDirection(inputArr[2].toUpperCase());
            int numMoves = Character.getNumericValue(inputArr[3].charAt(0));
            int curAngle;
            int prevAngle = entryDirection.getNumVal();

            while (numMoves > 0) {
                curAngle = 45 * array[curPos[1]][curPos[0]];
                prevAngle = (curAngle + prevAngle) % 360;
                switch (prevAngle) {
                    case 0:
                        curPos[0] = curPos[0];
                        curPos[1] = curPos[1] + 1;
                        break;

                    case 45:
                        curPos[0] = curPos[0] - 1;
                        curPos[1] = curPos[1] + 1;
                        break;

                    case 90:
                        curPos[0] = curPos[0] - 1;
                        curPos[1] = curPos[1];
                        break;

                    case 135:
                        curPos[0] = curPos[0] - 1;
                        curPos[1] = curPos[1] - 1;
                        break;

                    case 180:
                        curPos[0] = curPos[0];
                        curPos[1] = curPos[1] - 1;
                        break;

                    case 225:
                        curPos[0] = curPos[0] + 1;
                        curPos[1] = curPos[1] - 1;
                        break;

                    case 270:
                        curPos[0] = curPos[0] + 1;
                        curPos[1] = curPos[1];
                        break;

                    case 315:
                        curPos[0] = curPos[0] + 1;
                        curPos[1] = curPos[1] + 1;
                        break;
                }

                curPos[0] += (curPos[0] > 8 ? -8 : curPos[0] < 1 ? 8 : 0);
                curPos[1] += (curPos[1] > 8 ? -8 : curPos[1] < 1 ? 8 : 0);

                numMoves--;
            }

            System.out.println("(" + curPos[0] + ", " + curPos[1] + ")");

        }

    }

    public static int[][] getRowVals(String[] hexString) {
        String[] octalString = new String[hexString.length];
        int[][] rowVals = new int[octalString.length + 1][octalString.length + 1];

        for (int x = 0; x < hexString.length; x++) {
            octalString[x] = Integer.toOctalString(Integer.parseInt(hexString[x],16));
        }

        for (int x = 1; x < octalString.length; x++) {
            for (int y = 1; y < octalString[x].length(); y++) {
                rowVals[y][x] = Character.getNumericValue(octalString[x].charAt(y));
            }
        }

        return rowVals;
    }

    public static Direction getDirection(String input) {
        switch(input) {
            case "L":
                return Direction.LEFT;

            case "R":
                return Direction.RIGHT;

            case "A":
                return Direction.ABOVE;

            case "B":
                return Direction.BELOW;

            default:
                return null;
        }

    }
}
