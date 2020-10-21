package com.company;

import java.util.Scanner;

public class Main{

    private static final int BOTTOM_DIFFICULT_BORDER = 3;
    private static final int TOP_DIFFICULT_BORDER = 5;
    private static final String END_GAME_MESSAGE = "Game Over";
    private static final String ENTER_DIFFICULTY = "Enter difficulty";
    private static final String END_KEY_WORD = "сдаюсь";
    private static final String WON_MESSAGE = "Victory! Count of attempts:";
    private static final String NEXT_PRINT_MESSAGE = "Enter your sequence";
    private static final String WIN_SEQUENCE_WAS = "Win sequence was";
    private static final String STATE_MESSAGE = "Exists count and guessed numbers count";

    public static void main(String[] args){
        var in = new Scanner(System.in);
        System.out.println(ENTER_DIFFICULTY);
        try {
            var diff = in.nextInt();
            if (diff<BOTTOM_DIFFICULT_BORDER||diff>TOP_DIFFICULT_BORDER){
                throw new Exception();
            }
            var gameString = genGameString(diff);
            int tryCount = 0;
            while(true){
                System.out.println(NEXT_PRINT_MESSAGE);
                var s = in.next();
                if (s.contentEquals(END_KEY_WORD)) {
                    System.out.println(WIN_SEQUENCE_WAS);
                    System.out.println(arrayToString(gameString));
                    throw new Exception();
                }
                try{
                    Integer.parseInt(s);
                    if(s.length()!=gameString.length)
                        throw new Exception();

                    int exists = 0;
                    int guessed = 0;
                    var tempArr = stringToArray(s);
                    for(var i=0;i<gameString.length;i++){
                        var indx = findIndx(tempArr,gameString[i]);
                        if (indx!=-1){
                            if(indx==i)
                                guessed++;
                            else
                                exists++;
                        }
                    }
                    if (guessed==gameString.length){
                        System.out.println(WON_MESSAGE);
                        System.out.println(tryCount);
                        return;
                    }
                    else {
                        tryCount++;
                        System.out.println(STATE_MESSAGE);
                        System.out.println(exists+" : "+guessed);
                    }
                }
                catch (Exception e){
                    continue;
                }
            }
        }
        catch(Exception e){
            System.out.println(END_GAME_MESSAGE);
            return;
        }
    }
    static String arrayToString(int[] source){
        var result = "";
        for(var c : source){
            result+=c;
        }
        return result;
    }
    static int[] stringToArray(String source){
        var arr = source.toCharArray();
        var result = new int[arr.length];
        for (var i=0;i<arr.length;i++){
            result[i] =  Integer.parseInt(""+arr[i]);
        }
        return result;
    }
    static int[] genGameString(int length){
        var result = new int[length];
        for(var i=0;i<result.length;i++){
            result[i]=-1;
        }
        for (var i=0;i<length;i++){
            var temp = genGameInt();
            while (findIndx(result,temp)!=-1){
                temp = temp == 9?0:++temp;
            }
            result[i]=temp;
        }
        return result;
    }
    static int findIndx(int[] source, int key){
        for (var i=0;i<source.length;i++){
            if (source[i]==key) return i;
        }
        return -1;
    }
    //0-9
    static int genGameInt(){
        return (int)Math.floor(Math.random()*10);
    }
}
