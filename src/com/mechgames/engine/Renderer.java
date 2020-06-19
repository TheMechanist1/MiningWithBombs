package com.mechgames.engine;

import com.mechgames.engine.gfx.Font;
import com.mechgames.engine.gfx.Image;
import com.mechgames.engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pW, pH;
    private int[] p;
    private byte[] zBuffer;
    private byte depth = 0;

    private int translateX = 0;
    private int translateY = 0;

    private final Font STANDARD_FONT = Font.STANDARD;

    Engine engine;

    public Renderer(Engine engine) {
        pW = engine.getWidth();
        pH = engine.getHeight();
        p = ((DataBufferInt) engine.getWindow().getScreenImage().getRaster().getDataBuffer()).getData();
        zBuffer = new byte[p.length];
        this.engine = engine;
    }

    public void clear() {
        for (int i = 0; i < p.length; i++) {
            p[i] = 0;
            zBuffer[i] = 0;
        }
    }

    public void translate(int x, int y) {
        translateX += x;
        translateY += y;
    }

    public void setPixel(int x, int y, int value) {
        x = x + translateX;
        y = y + translateY;

        int alpha = (value >> 24 & 0xff);

        if ((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0) return;

        int index = x + y * pW;
        if (zBuffer[index] > depth) return;
        zBuffer[index] = depth;

        if (alpha == 255) {
            p[index] = value;
        } else {
            int pixelColor = p[index];
            int backgroundRed = (pixelColor >> 16) & 0xff;
            int backgroundGreen = (pixelColor >> 8) & 0xff;
            int backgroundBlue = pixelColor & 0xff;

            float alphaFloat = alpha / 255f;
            int foregroundRed = (value >> 16) & 0xff;
            int foregroundGreen = (value >> 8) & 0xff;
            int foregroundBlue = value & 0xff;

            int outputRed = (int) ((alphaFloat * foregroundRed) + (backgroundRed * (1 - alphaFloat)));
            int outputGreen = (int) ((alphaFloat * foregroundGreen) + (backgroundGreen * (1 - alphaFloat)));
            int outputBlue = (int) ((alphaFloat * foregroundBlue) + (backgroundBlue * (1 - alphaFloat)));
            p[index] = outputRed << 16 | outputGreen << 8 | outputBlue;
        }
    }

    public void drawString(String string, int startX, int startY, int color) {
//        TODO: Make lowercase letters

        int renderOffset = 0;

        int charMapWidth = STANDARD_FONT.getImageWidth();
        int height = STANDARD_FONT.getImageHeight();
        int stringLength = string.length();
        boolean[] fontData = STANDARD_FONT.getData();

        for (int i = 0; i < stringLength; i++) {
            int charCode = string.codePointAt(i) - 32;
            int width;

            if(STANDARD_FONT.getWidth(charCode) == -1) {

                drawRect(startX + renderOffset + 1, startY+1, 2, 5, color);
                renderOffset += 1.5 * engine.getScale();
                continue;
            }

            width = STANDARD_FONT.getWidth(charCode);
            int charOffset = STANDARD_FONT.getOffset(charCode);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (fontData[charOffset + x + y * charMapWidth]) {
                        setPixel(startX + x + renderOffset, startY + y, color);
                    }
                }
            }
            renderOffset += width;
        }
    }

    public void drawImage(Image image, int offsetX, int offsetY) {
        int width = image.getW();
        int height = image.getH();

        for (int x = 0; x < width; x += 15) {
            for (int x2 = x; x2 < Math.min(width, x + 15); x2++) {
                for (int y = 0; y < height; y++) {
                    setPixel(x2 + offsetX, y + offsetY, image.getP()[x2 + y * image.getW()]);
                }
            }
        }
    }

    public void drawTileImage(ImageTile imageTile, int offsetX, int offsetY, int tileX, int tileY) {
        int width = imageTile.getTileWidth();
        int height = imageTile.getTileHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setPixel(x + offsetX, y + offsetY, imageTile.getP()[(x + tileX * imageTile.getTileWidth()) + (y + tileY * imageTile.getTileHeight()) * imageTile.getW()]);
            }
        }
    }

    public void drawRect(int offsetX, int offsetY, int width, int height, int color) {
        for (int y = 0; y <= height ; y++) {
            setPixel(offsetX, y + offsetY, color);
            setPixel(offsetX + width, y + offsetY, color);
        }

        for (int x = 0; x <= width ; x++) {
            setPixel(x + offsetX, offsetY, color);
            setPixel(x + offsetX, offsetY + height, color);
        }
    }

    public void drawFillRect(int offsetX, int offsetY, int width, int height, int color) {
        for (int y = 0; y <= height; y++) {
            for (int x = 0; x <= width; x++) {
                setPixel(x + offsetX, y + offsetY, color);
            }
        }
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(byte depth) {
        this.depth = depth;
    }


}
