package com.mechgames.game.world;

import com.mechgames.engine.Renderer;
import com.mechgames.engine.math.Vector2d;
import com.mechgames.game.MiningWithBombs;
import com.mechgames.game.UI.Camera;
import com.mechgames.game.tile.*;

public class World {
    private final TileList tiles = new TileList();
    private final TileList removedTiles = new TileList();
    private final TileList placedTiles = new TileList();
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

                if (placedTiles.getTileAt(position) != null && tiles.getTileAt(position) == null) {
                    tiles.add(placedTiles.getTileAt(position));
                    continue;
                }

                if (removedTiles.getTileAt(position) != null) {
                    continue;
                }

                if (tiles.getTileAt(position) == null) {
                    double height = groundNoise.noise(x) * 100;
                    double caveHeight = groundNoise.noise(x, y);


                    if (y == (int) height) {
                        tiles.add(new GrassTile(position));
                    } else if (y < height) {
                        tiles.add(new AirTile(position));
                    } else if (y > height && y < height + 5) {
                        tiles.add(new DirtTile(position));
                    } else if (y > height + 5 && y < height + 20) {
                        tiles.add(new StoneTile(position));
                    } else {
                        if (caveHeight < 0.5) {
                            tiles.add(new StoneTile(position));
                        } else {
                            tiles.add(new CaveAirTile(position));
                        }
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

            if (placedTiles.getTileAt(t.getPosition()) != null) {
                placedTiles.getTileAt(t.getPosition()).draw(renderer);
                continue;
            }

            if (removedTiles.getTileAt(t.getPosition()) != null) {
                continue;
            }

            t.draw(renderer);
        }

        for (Tile tt : tl.getTiles()) {
            getTileList().remove(tt);
        }
    }

    public void placeTile(Tile tile) {
        placedTiles.add(tile);
        tiles.remove(tile);
    }

    public void removeTile(Tile tile) {
        removedTiles.add(tile);
        tiles.remove(tile);
    }

    public TileList getTileList() {
        return tiles;
    }
}
