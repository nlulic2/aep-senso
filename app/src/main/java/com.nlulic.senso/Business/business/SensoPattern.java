package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensoPattern {

    List<SensoValue> pattern;

    SensoPattern() {
        this.pattern = new ArrayList<SensoValue>();
    }

    public List<SensoValue> getPattern() {

        return this.pattern;
    }

    public void Add(SensoValue value) {
        this.pattern.add(value);
    }

}
