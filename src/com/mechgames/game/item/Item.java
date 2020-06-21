package com.mechgames.game.item;

import com.mechgames.engine.gfx.Image;

public class Item {
    private int count = 0;
    private Image itemImage;

    public Item(Image itemImage) {
        this.itemImage = itemImage;
    }
}
