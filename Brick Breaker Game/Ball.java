import java.awt.*;

public class Ball {
    private int x, y;
    private int diameter;
    private int dx, dy; // velocity components

    public Ball(int x, int y, int diameter, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.dx = dx;
        this.dy = dy;
    }

    public void move() {
        x -= dx;
        y -= dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void reverseDirectionX() {
        dx = -dx;
    }

    public void reverseDirectionY() {
        dy = -dy;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }
}
