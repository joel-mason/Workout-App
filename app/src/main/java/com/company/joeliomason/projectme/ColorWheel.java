package com.company.joeliomason.projectme;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by JoelioMason on 11/11/14.
 */
public class ColorWheel{
    public String mColors[] = {
            "#44F44336", // light blue
            "#449C27B0", // dark blue
            "#44673AB7", // mauve
            "#442196F3", // red
            "#4400BCD4", // orange
            "#44009688", // lavender
            "#44CDDC39", // purple
            "#44FFC107", // aqua
            "#44795548", // green
            "#449E9E9E", // mustard
            "#44607D8B", // dark gray
            "#44f092b0", // pink
            "#44b7c0c7"  // light gray
    };

    public int getColor(int x) {
        String color = "";
        switch(x) {
            case 1: color = mColors[1];
                break;
            case 2: color = mColors[2];
                break;
            case 3: color = mColors[3];
                break;
            case 4: color = mColors[4];
                break;
            case 5: color = mColors[5];
                break;
            case 6: color = mColors[6];
                break;
            case 7: color = mColors[7];
                break;
            case 8: color = mColors[8];
                break;
            case 9: color = mColors[9];
                break;
            case 10: color = mColors[10];
                break;
            case 11: color = mColors[11];
        }
        if(color.equals("")) {
            return Color.WHITE;
        } else {
            int colorAsInt = Color.parseColor(color);
            return colorAsInt;
        }
    }

    public int getColor() {


        String color = "";
        //Randomly select a fact
        Random randomGenerator = new Random(); //construct a new random number generator
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        int colorAsInt = Color.parseColor(color);
        return colorAsInt;
    }
}