package com.mechgames.game;

import com.mechgames.engine.AbstractGame;
import com.mechgames.engine.Engine;
import com.mechgames.engine.Math.Vector2d;
import com.mechgames.engine.Renderer;
import com.mechgames.engine.Window;
import com.mechgames.engine.gfx.Image;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.entity.Player;
import com.mechgames.game.tile.GrassTile;
import com.mechgames.game.tile.Tile;
import com.mechgames.game.world.WorldGen;

import java.util.EmptyStackException;

public class MiningWithBombs extends AbstractGame {
    public static int width = 800;
    public static int height = 800;
    public static MiningWithBombs instance;
    public Player player = new Player(new Vector2d(400, 400), Image.load("sprites/dirtTile.png"));
    Camera cam;

    WorldGen generator;

    public MiningWithBombs() {
        generator = new WorldGen();
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

        renderer.translate((int)-cam.getPosition().getX(), (int)-cam.getPosition().getY());
        for (Tile t: generator.tiles.getTiles()) {
            t.draw(renderer);
        }
        player.render(renderer);
        renderer.translate((int)cam.getPosition().getX(), (int)cam.getPosition().getY());



    }

}
