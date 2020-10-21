package com.company;

public interface ISettings {
    int getBottomDifficult();
    int getTopDifficult();
    String getStopWord();
    String getMessage(GameMessage key);
}
