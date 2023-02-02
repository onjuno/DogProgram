/*
 * @author Olle Nordlander olno8946
 */

import java.io.InputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class InputReader {
    private static ArrayList<InputStream> inputStreamArrayList = new ArrayList<>();

    private Scanner input;



    public InputReader(){
        this(System.in);
    }


    public InputReader(InputStream inputStream) {
        if (inputStreamArrayList.contains(inputStream)) {
            throw new IllegalStateException("Error!");
        }
        inputStreamArrayList.add(inputStream);
        input = new Scanner(inputStream);
    }

    public int getInteger(String answer) {
        System.out.print(answer + "?>");
        int integerOne = input.nextInt();
        input.nextLine();
        return integerOne;
    }

    public double getDouble(String answer) {
        System.out.print(answer + "?>");
        double integerTwo = input.nextDouble();
        input.nextLine();
        return integerTwo;
    }
    public String getString(String answer) {
        System.out.print(answer + "?>");
        String textOne = input.nextLine();
        return textOne.trim();
    }
}







