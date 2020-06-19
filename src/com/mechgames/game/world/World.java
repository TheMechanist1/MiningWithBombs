package com.mechgames.game.world;

import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.tile.*;

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
                    double evaluated = groundNoise.noise(x);
                    int height = (int) (evaluated * 20);

                    if (y < height) {
                        tiles.add(new AirTile(position));
                    } else if (y == height) {
                        tiles.add(new GrassTile(position));
                    } else if (y > height && y < height + 5) {
                        tiles.add(new DirtTile(position));
                    } else if (y >= height + 5) {
                        tiles.add(new StoneTile(position));
                    }
                }
            }
        }


    }

    public TileList getTileList() {
        return tiles;
    }
}
