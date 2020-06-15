package com.mechgames.game;

import com.mechgames.engine.AbstractGame;
import com.mechgames.engine.Engine;
import com.mechgames.engine.Renderer;

public class GameManager extends AbstractGame {
    public GameManager() {

    }

    public static void main(String[] args) {
        Engine engine = new Engine(new GameManager());
        engine.start();
    }

    @Override
    public void update(Engine engine, float dt) {

    }

    @Override
    public void render(Engine engine, Renderer renderer) {

    }
}
