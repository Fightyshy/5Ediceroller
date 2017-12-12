package com.example.harv0kz.dd5ediceroller.fragments;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Harv0kz on 8/10/2017.
 */

public class RNG {
    int masterMin = 1;
    int d4Max = 4;
    int d6Max = 6;
    int d8Max = 8;
    int d10Max = 10;
    int d12Max = 12;
    int d20Max = 20;

    public int rollD4(){
        Random newD4Dice = new SecureRandom();
        return newD4Dice.nextInt(d4Max-masterMin+1)+masterMin;
    }

    public int rollD6(){
        Random newD6Dice = new SecureRandom();
        return newD6Dice.nextInt(d6Max-masterMin+1)+masterMin;
    }

    public int rollD8(){
        Random newD8Dice = new SecureRandom();
        return newD8Dice.nextInt(d8Max-masterMin+1)+masterMin;
    }

    public int rollD10(){
        Random newD10Dice = new SecureRandom();
        return newD10Dice.nextInt(d10Max-masterMin+1)+masterMin;
    }

    public int rollD12(){
        Random newD12Dice = new SecureRandom();
        return newD12Dice.nextInt(d12Max-masterMin+1)+masterMin;
    }

    public int rollD20(){
        Random newD20Dice = new SecureRandom();
        return newD20Dice.nextInt(d20Max-masterMin+1)+masterMin;
    }

    public int randomNumber(int numberDice, int diceSides){
        int result = 0;
        List<Integer> diceRolled = new ArrayList<Integer>(); //Eg. 5d6 d6 is rolled separately 5 times, then summed, also logging

        if(numberDice < 1){
            numberDice = 1;
        }

        switch(diceSides){
            case 4:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD4();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 6:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD6();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 8:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD8();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 10:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD10();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 12:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD12();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 20:
                for(int i = 0; i < numberDice; i++){
                    int oneRoll = rollD20();
                    diceRolled.add(oneRoll);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 100:
                for(int i = 0; i < numberDice; i++){
                    int bothRolls = Integer.parseInt(rollD10()+""+rollD10());
                    diceRolled.add(bothRolls);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            case 66:
                for(int i = 0; i < numberDice; i++){
                    int bothRolls = Integer.parseInt(rollD6()+""+rollD6());
                    diceRolled.add(bothRolls);
                }
                for (int j : diceRolled){
                    result += j;
                }
                break;
            default:
                result = 1;
                break;
        }
        return result;
    }
}
