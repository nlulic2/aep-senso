package business;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePattern extends SensoPattern {

    GamePattern() {
        super();
    }

    public void Append() {
        this.pattern.add(randomValue());
    }

    public boolean Assert(SensoPattern userPattern) {

        List<SensoValue> equalLengthPattern = this.getPattern().subList(0, userPattern.getPattern().size());

        return userPattern.getPattern().equals(equalLengthPattern);
    }

    private int latestIndex = 0;
    public SensoValue Next() {

        if(latestIndex > this.pattern.size()-1) {
            this.latestIndex = 0;
            return null;
        }

        SensoValue next = this.pattern.get(latestIndex);
        latestIndex++;

        return next;
    }

    private SensoValue randomValue() {

        return SensoValue.values()[
            new Random().nextInt(SensoValue.values().length)
        ];
    }





}
