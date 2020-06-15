package com.mechgames.engine;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private Engine engine;

    private final int NUM_KEYS = 1000;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 10;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;

    public Input(Engine engine) {
        this.engine = engine;
        mouseX = 0;
        mouseY = 0;
        scroll = 0;

        engine.getWindow().getCanvas().addKeyListener(this);
        engine.getWindow().getCanvas().addMouseListener(this);
        engine.getWindow().getCanvas().addMouseMotionListener(this);
        engine.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update() {
        scroll = 0;

        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);

        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getScroll() {
        return scroll;
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int button) {
        return buttons[button];
    }

    public boolean isButtonUp(int button) {
        return !buttons[button] && buttonsLast[button];
    }

    public boolean isButtonDown(int button) {
        return buttons[button] && !buttonsLast[button];
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }
}
