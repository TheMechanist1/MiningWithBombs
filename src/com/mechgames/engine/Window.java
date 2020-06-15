package com.mechgames.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private final JFrame frame;
    private final BufferedImage screenImage;
    private final Canvas canvas;
    private final BufferStrategy bufferStrategy;
    private final Graphics graphics;
    private final Engine engine;

    public Window(Engine engine) {
        this.engine = engine;

        screenImage = new BufferedImage(engine.getWidth(), engine.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension widthHeight = new Dimension((int)(engine.getWidth() * engine.getScale()), (int)(engine.getHeight() * engine.getScale()));
        canvas.setPreferredSize(widthHeight);
        canvas.setMaximumSize(widthHeight);
        canvas.setMinimumSize(widthHeight);

        frame = new JFrame(engine.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }


    public void update() {
        graphics.drawImage(screenImage, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

    public BufferedImage getScreenImage() {
        return screenImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

}
