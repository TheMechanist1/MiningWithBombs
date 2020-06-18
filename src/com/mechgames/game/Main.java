package com.mechgames.game;

import com.mechgames.engine.Engine;
import com.mechgames.engine.Input;

public class Main {

    public static void main(String[] args) {
        MiningWithBombs instance = new MiningWithBombs();
        Engine engine = new Engine(instance);


        engine.start();
    }
}
