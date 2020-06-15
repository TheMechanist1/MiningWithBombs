package com.mechgames.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {
    private final int w, h;
    private final int[] p;
    private boolean alpha = false;

    public Image(String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(Image.class.getResourceAsStream("/"+path));
        } catch (IOException e) {
//            RuntimeException means that we don't need to explicitly handle this everywhere, which would be painful and unnecessary
//            this is better than raising a NullPointerException later when we try to getWidth
            throw new RuntimeException(e);
        }

        w = image.getWidth();
        h = image.getHeight();
        p = image.getRGB(0, 0, w, h, null, 0, w);

        image.flush();
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int[] getP() {
        return p;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }
}
