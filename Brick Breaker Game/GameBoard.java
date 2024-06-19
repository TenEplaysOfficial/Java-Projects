import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard extends JPanel implements KeyListener, ActionListener {
    private final int WIDTH = 600;
    private final int HEIGHT = 400;
    private final int PADDLE_WIDTH = 100;
    private final int PADDLE_HEIGHT = 10;
    private final int BALL_DIAMETER = 20;
    private final int BRICK_WIDTH = 50;
    private final int BRICK_HEIGHT = 20;
    private final int BRICK_ROWS = 5;
    private final int BRICK_COLUMNS = 10;
    private final int INITIAL_PADDLE_SPEED = 10;
    private final int PADDLE_SPEED_INCREMENT = 2;
    private int paddleSpeed = INITIAL_PADDLE_SPEED;
    private final int INITIAL_BALL_SPEED = 3;
    private int ballSpeed = INITIAL_BALL_SPEED;

    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;

    private boolean gameRunning;
    private boolean gameWon;
    private boolean gameOver;

    public GameBoard() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        initializeGame();
    }

    private void initializeGame() {
        paddle = new Paddle(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - 50, PADDLE_WIDTH, PADDLE_HEIGHT, paddleSpeed);
        Random rand = new Random();
        int initialDx = rand.nextBoolean() ? 1 : -1;
        int initialDy = rand.nextBoolean() ? 1 : -1;
        ball = new Ball(WIDTH / 2 - BALL_DIAMETER / 2, HEIGHT / 2 - BALL_DIAMETER / 2, BALL_DIAMETER, initialDx * ballSpeed, initialDy * ballSpeed);

        bricks = new ArrayList<>();

        for (int i = 0; i < BRICK_ROWS; i++) {
            for (int j = 0; j < BRICK_COLUMNS; j++) {
                int x = j * BRICK_WIDTH + 50;
                int y = i * BRICK_HEIGHT + 30;
                Brick brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT);
                bricks.add(brick);
            }
        }

        gameRunning = true;
        gameWon = false;
        gameOver = false;

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        paddle.draw(g);

        ball.draw(g);

        for (Brick brick : bricks) {
            brick.draw(g);
        }

        if (!gameRunning) {
            String message = gameWon ? "You won!" : "Game Over";
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            FontMetrics fm = g.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            g.drawString(message, WIDTH / 2 - messageWidth / 2, HEIGHT / 2);

            if (gameOver) {
                String restartMessage = "Press Enter to restart";
                g.setFont(new Font("Arial", Font.PLAIN, 24));
                fm = g.getFontMetrics();
                int restartWidth = fm.stringWidth(restartMessage);
                g.drawString(restartMessage, WIDTH / 2 - restartWidth / 2, HEIGHT / 2 + 40);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameRunning) {
            move();
            checkCollisions();
            checkGameEnd();
            repaint();
        }
    }

    private void move() {
        ball.move();
    }

    private void checkCollisions() {

        if (ball.getX() <= 0 || ball.getX() + ball.getDiameter() >= WIDTH) {
            ball.reverseDirectionX();
        }
        if (ball.getY() <= 0) {
            ball.reverseDirectionY();
        }

        if (ball.getBounds().intersects(paddle.getBounds())) {
            ball.reverseDirectionY();
        }

        for (Brick brick : bricks) {
            if (brick.isVisible() && ball.getBounds().intersects(brick.getBounds())) {
                brick.setVisible(false);
                ball.reverseDirectionY();
            }
        }
    }

    private void checkGameEnd() {

        boolean allDestroyed = true;
        for (Brick brick : bricks) {
            if (brick.isVisible()) {
                allDestroyed = false;
                break;
            }
        }

        if (allDestroyed) {
            gameRunning = false;
            gameWon = true;
            gameOver = true;
        }

        if (ball.getY() + ball.getDiameter() >= HEIGHT) {
            gameRunning = false;
            gameOver = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gameRunning && gameOver && key == KeyEvent.VK_ENTER) {
            initializeGame();
        } else if (gameRunning) {
            if (key == KeyEvent.VK_LEFT) {
                if (paddle.getX() > 0) {
                    paddle.moveLeft();
                }
            } else if (key == KeyEvent.VK_RIGHT) {
                if (paddle.getX() + paddle.getWidth() < WIDTH) {
                    paddle.moveRight();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Brick Breaker Game");
        GameBoard gameBoard = new GameBoard();
        frame.getContentPane().add(gameBoard);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}