package com.mechgames.game.world;

import com.mechgames.engine.Renderer;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.tile.StoneTile;
import com.mechgames.game.tile.Tile;
import com.mechgames.game.tile.TileList;

public class World {
    private final TileList tiles = new TileList();
    private final Noise groundNoise = new Noise();

    public World() {

    }

    public void update(Camera cam) {
        int camStartX = (((int) cam.getPosition().getX() - 1) / Tile.size - 2);
        int camStartY = ((int) cam.getPosition().getY()) / Tile.size - 2;

        int camEndX = ((int) cam.getPosition().getX() / Tile.size) + MiningWithBombs.width / Tile.size + 2;
        int camEndY = ((int) cam.getPosition().getY() / Tile.size) + MiningWithBombs.height / Tile.size + 2;


        for (int x = camStartX; x < camEndX; x++) {
            for (int y = camStartY; y < camEndY; y++) {
                Vector2d position = new Vector2d(x * Tile.size, y * Tile.size);

                if (tiles.getTileAt(position) == null) {
                    if (y > 0) {
                        tiles.add(new StoneTile(position));
                    }
                }
            }
        }
    }

    public void render(Renderer renderer, Camera cam) {
        TileList tl = new TileList();

        for (Tile t : getTileList().getTiles()) {

            if (!t.isOnScreen(cam, MiningWithBombs.width, MiningWithBombs.height)) {
                tl.add(t);
                continue;
            }

            t.draw(renderer);
        }

        for (Tile tt : tl.getTiles()) {
            getTileList().remove(tt);
        }
    }


    public TileList getTileList() {
        return tiles;
    }
}
