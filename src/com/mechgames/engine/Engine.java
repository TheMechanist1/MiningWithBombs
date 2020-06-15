package com.mechgames.engine;

public class Engine {

    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean running = false;
    private final double UPDATES_PER_SECOND = 1.0/60.0;

    private int width = 300, height = 300;
    private float scale = 3f;
    private String title = "";


    public Engine(AbstractGame game) {
        this.game = game;
    }

    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        run();
    }

    public void run() {
        running = true;

        boolean render;
        double firstTime;
        double lastTime = System.nanoTime()/1000000000.0;
        double passedTime;
        double unprocessedTime = 0;

        double frameTime = 0;
        double fps = 0;
        double frames = 0;

        while(running) {
            render = false;
            firstTime = System.nanoTime()/1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATES_PER_SECOND) {
                unprocessedTime -= UPDATES_PER_SECOND;
                render = true;

                game.update(this, (float) UPDATES_PER_SECOND);

                input.update();

                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if(render) {
                renderer.clear();
                game.render(this, renderer);
                renderer.drawString("FPS: " + fps, 0, 0, 0xffffffff);
                window.update();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }
}
