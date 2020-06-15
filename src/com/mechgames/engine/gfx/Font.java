package com.mechgames.engine.gfx;

public class Font {
    public static final Font STANDARD = new Font("fonts/standardFont.png");

    private final boolean[] data;
    private final int[] offsets;
    private final int[] widths;
    private final int imageWidth;
    private final int imageHeight;

    public Font(String path) {
        Image image = new Image(path);
        offsets = new int[100];
        widths = new int[100];

        int charCode = 0;


        for (int i = 0; i < image.getW(); i++) {

            if(charCode >= offsets.length) continue;

            if(image.getP()[i] == 0xff0000ff) {
                offsets[charCode] = i;
            }

            if(image.getP()[i] == 0xffffff00) {
                widths[charCode] = i - offsets[charCode];
                charCode++;
            }
        }

        imageWidth = image.getW();
        imageHeight = image.getH();

        data = new boolean[image.getP().length];
        for (int i = 0; i < image.getP().length; i++) {
            data[i] = image.getP()[i] == 0xff000000;
        }
    }

    public boolean[] getData() {
        return data;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getOffset(int charCode) {
        return offsets[charCode];
    }

    public int getWidth(int charCode) {
        if(charCode >= widths.length || charCode < 0) {
            return -1;
        } else if(widths[charCode] == 0){
            return -1;
        } else {
            return widths[charCode];
        }
    }
}
