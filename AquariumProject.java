
import edu.cymbal.graphics.*;
import java.awt.Color;

public class AquariumProject {

    // Aquarium colors
    static Color waterColor = new Color(135, 206, 235);
    static Color sandColor = new Color(238, 214, 175);
    static Color black = Color.BLACK;

    public static void main(String[] args) {

        CanvasAPI canvas =
            Graphics.createCanvas(800, 600, "My Aquarium");

        PainterAPI painter = canvas.getPainter();
        painter.setSpeed(0);

        // Draw the complete aquarium
        drawAquarium(painter);

        canvas.awaitClose();
    }

    /**
     * Organizes all elements in the aquarium.
     *
     * @param painter the PainterAPI object used for drawing
     */
    public static void drawAquarium(PainterAPI painter) {

        canvasBackground(painter);

        drawSeaweed(0, -260, 100, painter);
        drawSeaweed(65, -260, 145, painter);
        drawSeaweed(-310, -260, 125, painter);

        drawCoral(275, -260, painter);
        drawCoral(345, -260, painter);

        drawSchoolOfFish(painter);

        // Draw two sharks with different sizes and positions
        drawShark(155, 125, 1.0, true, painter);
        drawShark(-220, 20, 0.65, false, painter);

        drawBubbles(painter);
    }

    /**
     * Draws the background and sandy bottom of the aquarium.
     *
     * @param painter the PainterAPI object used for drawing
     */
    public static void canvasBackground(PainterAPI painter) {

        drawOvalShape(0, -285, 780, 35,
                      sandColor, painter);

        // Decorative rocks along the aquarium floor
        for (int i = 0; i < 7; i++) {
            drawOvalShape(-340 + i * 110, -275,
                          28, 15, new Color(180, 160, 140),
                          painter);
        }
    }

    /**
     * Draws a school of six colorful fish.
     *
     * @param painter the PainterAPI object used for drawing
     */
    public static void drawSchoolOfFish(PainterAPI painter) {

        Color[] fishColors = {
            Color.ORANGE,
            Color.YELLOW,
            Color.RED,
            Color.MAGENTA,
            Color.GREEN,
            Color.CYAN
        };

        for (int i = 0; i < 6; i++) {

            double x = -300 + (i % 3) * 95;
            double y = 150 - (i / 3) * 95;

            int size = 35 + (i * 3);

            // Fish move farther left if the shark is nearby
            boolean sharkNearby = x > 100;

            if (sharkNearby) {
                x = x - 40;
            }

            drawFish(x, y, size, fishColors[i], painter);
        }
    }

    /**
     * Draws an individual fish with a body, tail, and eye.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param size overall size of the fish
     * @param color body color of the fish
     * @param painter drawing tool
     */
    public static void drawFish(double x, double y, int size,
                                Color color, PainterAPI painter) {

        // Fish body
        drawOvalShape(x, y, size, size * 0.65,
                      color, painter);

        // Fish tail
        drawTriangleShape(x - size * 0.65, y,
                          size * 0.55, size * 0.65,
                          color, false, painter);

        // Fish eye
        drawOvalShape(x + size * 0.25, y + size * 0.08,
                      size * 0.10, size * 0.10,
                      black, painter);
    }

    /**
     * Draws a shark with a body, tail, fins, and black eye.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param size overall shark size
     * @param facingRight direction the shark faces
     * @param painter drawing tool
     */
    public static void drawShark(double x, double y, double size,
                                 boolean facingRight,
                                 PainterAPI painter) {

        Color sharkColor = new Color(95, 110, 125);

        double direction = facingRight ? 1 : -1;

        // Shark body
        drawOvalShape(x, y, 125 * size, 55 * size,
                      sharkColor, painter);

        // Shark tail
        drawTriangleShape(x - direction * 85 * size, y,
                          50 * size, 65 * size,
                          sharkColor, !facingRight, painter);

        // Top fin
        drawTriangleShape(x, y + 30 * size,
                          45 * size, 35 * size,
                          sharkColor, true, painter);

        // Bottom fin
        drawTriangleShape(x, y - 30 * size,
                          35 * size, 25 * size,
                          sharkColor, true, painter);

        // Black eye
        drawOvalShape(x + direction * 48 * size,
                      y + 12 * size,
                      8 * size, 8 * size,
                      black, painter);
    }

    /**
     * Draws green seaweed using repeated triangle shapes.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param height height of the seaweed
     * @param painter drawing tool
     */
    public static void drawSeaweed(double x, double y,
                                   double height,
                                   PainterAPI painter) {

        Color green = new Color(35, 140, 65);

        for (int i = 0; i < 4; i++) {

            drawTriangleShape(x + i * 12, y + height / 2,
                              25, height,
                              green, true, painter);
        }
    }

    /**
     * Draws pink coral with multiple branches.
     *
     * @param x horizontal position
     * @param y vertical position
     * @param painter drawing tool
     */
    public static void drawCoral(double x, double y,
                                 PainterAPI painter) {

        Color pink = new Color(255, 105, 180);

        drawTriangleShape(x, y + 35, 35, 80,
                          pink, true, painter);

        drawTriangleShape(x - 22, y + 55, 25, 65,
                          pink, false, painter);

        drawTriangleShape(x + 22, y + 55, 25, 65,
                          pink, true, painter);

        drawOvalShape(x, y + 100, 18, 18,
                      pink, painter);

        drawOvalShape(x - 22, y + 90, 15, 15,
                      pink, painter);

        drawOvalShape(x + 22, y + 90, 15, 15,
                      pink, painter);
    }

    /**
     * Draws bubbles at different positions.
     *
     * @param painter drawing tool
     */
    public static void drawBubbles(PainterAPI painter) {

        for (int i = 0; i < 12; i++) {

            double x = -340 + i * 60;
            double y = -100 + (i % 4) * 65;

            drawOvalShape(x, y, 12, 12,
                          new Color(210, 245, 255),
                          painter);
        }
    }

    /**
     * Draws an oval using a series of connected lines.
     *
     * @param x horizontal center position
     * @param y vertical center position
     * @param width oval width
     * @param height oval height
     * @param color fill color
     * @param painter drawing tool
     */
    public static void drawOvalShape(double x, double y,
                                     double width, double height,
                                     Color color,
                                     PainterAPI painter) {

        painter.liftPen();

        double radiusX = width / 2;
        double radiusY = height / 2;

        painter.moveTo(x + radiusX, y);
        painter.lowerPen();

        painter.startFill(color);

        for (int i = 1; i <= 36; i++) {

            double angle = Math.toRadians(i * 10);

            double nextX = x + radiusX * Math.cos(angle);
            double nextY = y + radiusY * Math.sin(angle);

            painter.liftPen();
            painter.moveTo(nextX, nextY);
            painter.lowerPen();
        }

        painter.finishFill();
        painter.liftPen();
    }

    /**
     * Draws a triangle using three connected sides.
     *
     * @param x horizontal center position
     * @param y vertical center position
     * @param width triangle width
     * @param height triangle height
     * @param color fill color
     * @param pointRight whether the triangle points right
     * @param painter drawing tool
     */
    public static void drawTriangleShape(double x, double y,
                                         double width, double height,
                                         Color color,
                                         boolean pointRight,
                                         PainterAPI painter) {

        double direction = pointRight ? 1 : -1;

        double firstX = x - direction * width / 2;
        double firstY = y - height / 2;

        double secondX = x + direction * width / 2;
        double secondY = y;

        double thirdX = x - direction * width / 2;
        double thirdY = y + height / 2;

        painter.liftPen();
        painter.moveTo(firstX, firstY);
        painter.lowerPen();

        painter.startFill(color);

        painter.liftPen();
        painter.moveTo(secondX, secondY);
        painter.lowerPen();

        painter.liftPen();
        painter.moveTo(thirdX, thirdY);
        painter.lowerPen();

        painter.liftPen();
        painter.moveTo(firstX, firstY);
        painter.lowerPen();

        painter.finishFill();
        painter.liftPen();
    }
}
