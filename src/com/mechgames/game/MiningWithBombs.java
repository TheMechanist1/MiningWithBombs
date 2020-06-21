package com.mechgames.game;

import com.mechgames.engine.AbstractGame;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.entity.Player;
import com.mechgames.game.world.World;

public class MiningWithBombs extends AbstractGame {
    public static int width = 800;
    public static int height = 800;
    public static MiningWithBombs instance;
    public Player player = new Player(new Vector2d(400, 400));
    public Camera cam;

    public World generator;

    public MiningWithBombs() {
        generator = new World();
        instance = this;
        cam = new Camera();
        generator.update(cam);

        while (generator.getTileList().getTileAt(player.getPosition()) != null) {
            if (!generator.getTileList().getTileAt(player.getPosition()).isCollidable()) return;
            player.getPosition().setY(player.getPosition().getY() - 16);
        }
    }

    @Override
    public void update(float dt) {
        player.update();
        cam.update(player.getPosition());
        generator.update(cam);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.translate((int) -cam.getPosition().getX(), (int) -cam.getPosition().getY());

        generator.render(renderer, cam);


        player.render(renderer);
        renderer.translate((int) cam.getPosition().getX(), (int) cam.getPosition().getY());

        renderer.drawImage(Image.load("textures/tiles/airTile.png"), getEngine().getInput().getMouseX(), getEngine().getInput().getMouseY());
    }
}
