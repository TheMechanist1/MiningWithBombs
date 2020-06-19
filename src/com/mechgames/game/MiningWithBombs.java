package com.mechgames.game;

import com.mechgames.engine.AbstractGame;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.entity.Player;
import com.mechgames.game.tile.Tile;
import com.mechgames.game.tile.TileList;
import com.mechgames.game.world.World;

public class MiningWithBombs extends AbstractGame {
    public static int width = 800;
    public static int height = 800;
    public static MiningWithBombs instance;
    public Player player = new Player(new Vector2d(400, 400), Image.load("sprites/dirtTile.png"));
    Camera cam;

    public World generator;

    public MiningWithBombs() {
        generator = new World();
        instance = this;
        cam = new Camera();
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

        TileList tl = new TileList();

        for (Tile t : generator.getTileList().getTiles()) {

            if (!t.isOnScreen(cam, width, height)) {
                tl.add(t);
                continue;
            }

            t.draw(renderer);
        }

        for (Tile tt : tl.getTiles()) {
            generator.getTileList().remove(tt);
        }


        player.render(renderer);
        renderer.translate((int) cam.getPosition().getX(), (int) cam.getPosition().getY());
    }
}
