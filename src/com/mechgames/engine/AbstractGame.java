package com.mechgames.engine;

public abstract class AbstractGame {
    private Engine engine;

    public abstract void update(float dt);
    public abstract void render(Renderer renderer);

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
