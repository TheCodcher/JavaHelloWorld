package com.company;

public class Game {
    private String stopWord;
    private IGameUI ui;
    private ISettings rules;
    Game(IGameUI gameUI, ISettings settings){
        rules = settings;
        stopWord = rules.getStopWord();
        ui = gameUI;

    }
    void Play(){
        ui.userOutput(rules.getMessage(GameMessage.EnterDifficulty));
        try {
            var diff = 0;
            //можно еще разбить ошибки, дабавить надписей и т.д, но лень
            try {
                diff = Integer.parseInt(ui.userInput());
            }
            catch (Exception e){
                throw new Exception(rules.getMessage(GameMessage.ErrorDifficult));
            }
            if (diff<rules.getBottomDifficult()||diff>rules.getTopDifficult()){
                throw new Exception(rules.getMessage(GameMessage.OutOfDifficult));
            }
            var gameString = new Sequence(diff);
            int tryCount = 0;
            while(true){
                ui.userOutput(rules.getMessage(GameMessage.NextTurnMessage));
                var s = ui.userInput();
                if (s.contentEquals(stopWord)) {
                    ui.userOutput(rules.getMessage(GameMessage.SequenceAnswer));
                    ui.userOutput(gameString.toString());
                    throw new Exception(rules.getMessage(GameMessage.GameOver));
                }
                try{
                    Integer.parseInt(s);
                    if(s.length()!=gameString.length())
                        throw new Exception();
                    var turn = new Sequence(s);
                    if(turn.haveRepetitions())
                        throw new Exception();
                    var comp = compare(gameString,turn);
                    if (comp.guessed==gameString.length()){
                        ui.userOutput(rules.getMessage(GameMessage.VictoryMessage));
                        ui.userOutput(String.valueOf(tryCount));
                        return;
                    }
                    else {
                        tryCount++;
                        ui.userOutput(rules.getMessage(GameMessage.StateMessage));
                        ui.userOutput(comp.toString());
                    }
                }
                catch (Exception e){
                    ui.userOutput(rules.getMessage(GameMessage.ErrorAttempt));
                    continue;
                }
            }
        }
        catch(Exception e){
            ui.userOutput(e.getMessage());
            return;
        }
    }
    Turple compare(Sequence envisioned, Sequence attempt){
        var temp = new Turple();
        for(var i=0;i<envisioned.length();i++){
            var indx = attempt.findIndx(envisioned.source[i]);
            if (indx!=-1){
                if(indx==i)
                    temp.guessed++;
                else
                    temp.exists++;
            }
        }
        return  temp;
    }
    class Turple{
        int exists;
        int guessed;
        @Override
        public String toString() {
            return exists+" : "+guessed;
        }
    }
}