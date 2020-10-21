package com.company;

import java.util.*;

public class Main{


    public static void main(String[] args){
        var game = new Game(new ConsoleUI(),new MyDefaultSettings());
        game.Play();
    }
}
class ConsoleUI implements IGameUI{
    Scanner scan;
    ConsoleUI(){
        scan = new Scanner(System.in);
    }
    public String userInput(){
        return scan.next();
    }
    public void userOutput(String message){
        System.out.println(message);
    }
}

class MyDefaultSettings implements ISettings{
    Map<GameMessage,String> dict = new HashMap<>();
    //вообще понятия не имею как статически инициализировать словать, в с# удобнее
    MyDefaultSettings(){
        dict.put(GameMessage.StateMessage,"Exists count and guessed numbers count");
        dict.put(GameMessage.VictoryMessage,"Victory! Count of attempts:");
        dict.put(GameMessage.GameOver,"Game Over");
        dict.put(GameMessage.SequenceAnswer,"Win sequence was");
        dict.put(GameMessage.NextTurnMessage,"Enter your sequence");
        dict.put(GameMessage.ErrorDifficult,"Difficulty entered incorrectly");
        dict.put(GameMessage.EnterDifficulty,"Enter difficulty");
        dict.put(GameMessage.ErrorAttempt,"Attempt entered incorrectly");
        dict.put(GameMessage.OutOfDifficult,"Difficult must be "+getBottomDifficult()+"< and <"+getTopDifficult());
    }

    public int getBottomDifficult() {
        return 3;
    }

    public int getTopDifficult() {
        return 5;
    }

    public String getStopWord() {
        return "сдаюсь";
    }

    public String getMessage(GameMessage key) {
        return dict.get(key);
    }
}
