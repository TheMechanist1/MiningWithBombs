package com.mechgames.game.tile;

import com.mechgames.engine.math.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TileList {
    private Map<Vector2d, Tile> tileMap = new HashMap<>();
    private List<Tile> tiles = new ArrayList<>();

    public void add(Tile tile) {
        tileMap.put(tile.getPosition(), tile);
        tiles.add(tile);
    }

    public Tile getTileAt(Vector2d pos) {
        return tileMap.get(pos);
    }

    public Map<Vector2d, Tile> getTileMap() {
        return tileMap;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void remove(Tile tile) {
        tileMap.remove(tile.getPosition(), tile);
        tiles.remove(tile);
    }

}
