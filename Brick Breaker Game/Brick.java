import java.awt.*;

public class Brick {
    private int x, y;
    private int width, height;
    private boolean isVisible;

    public Brick(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isVisible = true;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, width, height);
        }
    }
}
