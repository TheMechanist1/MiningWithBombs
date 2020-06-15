package com.mechgames.engine;

public abstract class AbstractGame {
    public abstract void update(Engine engine, float dt);
    public abstract void render(Engine engine, Renderer renderer);
}
