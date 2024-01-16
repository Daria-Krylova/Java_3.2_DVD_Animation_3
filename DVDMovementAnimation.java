import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DVDMovementAnimation extends JPanel implements ActionListener {

    private static final int WIDTH = 800;   // Ширина окна
    private static final int HEIGHT = 600;  // Высота окна

    private BufferedImage logo;
    private int logoX, logoY;
    private int logoWidth, logoHeight;
    private int xDirection, yDirection;

    private Timer timer;

    public DVDMovementAnimation() {
        // Загрузка логотипа (замените на свой путь)
        ImageIcon icon = new ImageIcon("DVD_picture.png");
        logo = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = logo.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        // Размер логотипа
        int newWidth = 200;  // Новая ширина
        int newHeight = 200; // Новая высота
        BufferedImage scaledLogo = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gScaled = scaledLogo.createGraphics();
        gScaled.drawImage(logo, 0, 0, newWidth, newHeight, null);
        gScaled.dispose();
        logo = scaledLogo;

        // Начальная позиция 
        logoWidth = newWidth;
        logoHeight = newHeight;
        logoX = (int) (Math.random() * (WIDTH - logoWidth));
        logoY = (int) (Math.random() * (HEIGHT - logoHeight));

        // Начальное направление движения
        xDirection = 1;
        yDirection = 1;

        // Создание таймера для анимации
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Заливка фона черным цветом
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Отображение уменьшенного логотипа
        g.drawImage(logo, logoX, logoY, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        

        // Перемещение логотипа
        logoX += xDirection;
        logoY += yDirection;

        // Обработка столкновения с границами окна
        if (logoX <= 0 || logoX + logoWidth >= WIDTH) {
            xDirection *= -1;
        }
        if (logoY <= 0 || logoY + logoHeight >= HEIGHT) {
            yDirection *= -1;
        }

        // Перерисовка панели
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD animation");
        DVDMovementAnimation dvdAnimation = new DVDMovementAnimation();
        frame.add(dvdAnimation);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
