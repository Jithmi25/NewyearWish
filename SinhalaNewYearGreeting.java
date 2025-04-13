import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SinhalaNewYearGreeting extends JPanel {
    private int angle = 0;
    private final Timer timer;

    public SinhalaNewYearGreeting() {
        timer = new Timer(50, e -> {
            angle = (angle + 5) % 360;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(new Color(102, 0, 153));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int sunRadius = 150;

        g2d.setColor(new Color(212, 175, 55));
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Ellipse2D.Double(centerX - sunRadius, centerY - sunRadius,
                sunRadius * 2, sunRadius * 2));

        int rayLength = 70;
        int outerRadius = sunRadius + rayLength;

        for (int i = 0; i < 360; i += 15) {
            double radian = Math.toRadians(i + angle);
            double startX = centerX + sunRadius * Math.cos(radian);
            double startY = centerY + sunRadius * Math.sin(radian);
            double endX = centerX + outerRadius * Math.cos(radian);
            double endY = centerY + outerRadius * Math.sin(radian);

            g2d.setStroke(new BasicStroke(5));
            g2d.draw(new Line2D.Double(startX, startY, endX, endY));
        }

        Font font = new Font("Iskoola Pota", Font.BOLD, 30);
        g2d.setFont(font);
        g2d.setColor(new Color(212, 175, 55));

        String greeting = "සුභ අලුත් අවුරුද්දක් වේවා!";
        FontMetrics fm = g2d.getFontMetrics();

        String[] lines = {
                "සුභ අලුත්",
                "අවුරුද්දක් වේවා!"
        };

        int totalTextHeight = fm.getHeight() * lines.length;

        for (int i = 0; i < lines.length; i++) {
            int textWidth = fm.stringWidth(lines[i]);
            int textX = centerX - textWidth / 2;
            int textY = centerY - totalTextHeight / 2 + fm.getAscent() + i * fm.getHeight();
            g2d.drawString(lines[i], textX, textY);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sinhala & Tamil New Year Greeting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        SinhalaNewYearGreeting greeting = new SinhalaNewYearGreeting();
        frame.add(greeting);

        frame.setVisible(true);
    }
}