package com.mechgames.game;

import com.mechgames.engine.Engine;

public class Main {

    public static void main(String[] args) {
        MiningWithBombs instance = new MiningWithBombs();
        Engine engine = new Engine(instance);

        engine.start();
    }
}
