import edu.cymbal.graphics.*;
import java.awt.Color;

public class Aquariumproject {

    static Color waterColor = new Color(135, 206, 235);
    static Color sandColor = new Color(238, 214, 175);
    static Color black = Color.BLACK;

    public static void main(String[] args) {

        CanvasAPI canvas = Graphics.createCanvas(800, 600, "My Aquarium");
        PainterAPI painter = canvas.getPainter();

        painter.setSpeed(0);

        // Run the aquarium animation for 600 frames.
        for (int frame = 0; frame < 600; frame++) {

            painter.clear();

            drawAquarium(painter, frame);

            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        canvas.awaitClose();
    }

    /**
     * Draws the complete aquarium scene.
     */
    public static void drawAquarium(PainterAPI painter, int frame) {

        canvasBackground(painter);

        // Draw seaweed.
        drawSeaweed(0, -260, 100, painter);
        drawSeaweed(65, -260, 145, painter);
        drawSeaweed(-310, -260, 125, painter);

        // Draw coral.
        drawCoral(275, -260, painter);
        drawCoral(345, -260, painter);

        // Draw the school of fish.
        drawSchoolOfFish(painter);

        // Draw two sharks facing different directions.
        drawShark(155, 125, 1.0, true, painter);
        drawShark(-220, 20, 0.65, false, painter);

        // Draw animated bubbles.
        drawBubbles(painter, frame);
    }

    /**
     * Draws the aquarium background and sandy bottom.
     */
    public static void canvasBackground(PainterAPI painter) {

        drawOvalShape(0, -285, 780, 35, sandColor, painter);

        for (int i = 0; i < 7; i++) {
            drawOvalShape(
                -340 + i * 110,
                -275,
                28,
                15,
                new Color(180, 160, 140),
                painter
            );
        }
    }

    /**
     * Draws six fish in a school.
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

            // Move some fish away from the nearby shark.
            boolean sharkNearby =
                Math.abs(x - 155) < 300 ||
                Math.abs(x + 220) < 100;

            if (sharkNearby) {
                x = x - 40;
            }

            drawFish(x, y, size, fishColors[i], painter);
        }
    }

    /**
     * Draws an individual fish.
     */
    public static void drawFish(
        double x,
        double y,
        double size,
        Color color,
        PainterAPI painter
    ) {

        // Fish body.
        drawOvalShape(
            x,
            y,
            size,
            size * 0.65,
            color,
            painter
        );

        // Fish tail.
        drawTriangleShape(
            x - size * 0.65,
            y,
            size * 0.55,
            size * 0.65,
            color,
            false,
            painter
        );

        // Fish eye.
        drawOvalShape(
            x + size * 0.25,
            y + size * 0.08,
            size * 0.10,
            size * 0.10,
            black,
            painter
        );
    }

    /**
     * Draws a shark facing either left or right.
     */
    public static void drawShark(
        double x,
        double y,
        double size,
        boolean facingRight,
        PainterAPI painter
    ) {

        Color sharkColor = new Color(95, 110, 125);

        double direction = facingRight ? 1 : -1;

        // Shark body.
        drawOvalShape(
            x,
            y,
            125 * size,
            55 * size,
            sharkColor,
            painter
        );

        // Shark tail.
        drawTriangleShape(
            x - direction * 85 * size,
            y,
            50 * size,
            65 * size,
            sharkColor,
            !facingRight,
            painter
        );

        // Top fin.
        drawTriangleShape(
            x,
            y + 30 * size,
            45 * size,
            35 * size,
            sharkColor,
            true,
            painter
        );

        // Bottom fin.
        drawTriangleShape(
            x,
            y - 30 * size,
            35 * size,
            25 * size,
            sharkColor,
            true,
            painter
        );

        // Shark eye.
        drawOvalShape(
            x + direction * 48 * size,
            y + 12 * size,
            8 * size,
            8 * size,
            black,
            painter
        );
    }

    /**
     * Draws seaweed using repeated shapes.
     */
    public static void drawSeaweed(
        double x,
        double y,
        double height,
        PainterAPI painter
    ) {

        Color green = new Color(35, 140, 65);

        for (int i = 0; i < 4; i++) {

            drawTriangleShape(
                x + i * 12,
                y + height / 2,
                25,
                height,
                green,
                true,
                painter
            );
        }
    }

    /**
     * Draws pink coral using multiple shapes.
     */
    public static void drawCoral(
        double x,
        double y,
        PainterAPI painter
    ) {

        Color pink = new Color(255, 105, 180);

        drawTriangleShape(
            x,
            y + 35,
            35,
            80,
            pink,
            true,
            painter
        );

        drawTriangleShape(
            x - 22,
            y + 55,
            25,
            65,
            pink,
            false,
            painter
        );

        drawTriangleShape(
            x + 22,
            y + 55,
            25,
            65,
            pink,
            true,
            painter
        );

        drawOvalShape(
            x,
            y + 100,
            18,
            18,
            pink,
            painter
        );

        drawOvalShape(
            x - 22,
            y + 90,
            15,
            15,
            pink,
            painter
        );

        drawOvalShape(
            x + 22,
            y + 90,
            15,
            15,
            pink,
            painter
        );
    }

    /**
     * Draws bubbles that rise continuously during the animation.
     */
    public static void drawBubbles(
        PainterAPI painter,
        int frame
    ) {

        Color bubbleColor = new Color(225, 250, 255);

        for (int i = 0; i < 16; i++) {

            // Spread bubbles across the aquarium.
            double x = -350 + i * 45;

            // Move bubbles upward and restart them at the bottom.
            double y = -260 + ((i * 37 + frame * 5) % 520);

            // Vary bubble sizes.
            double size = 14 + (i % 3) * 4;

            drawOvalShape(
                x,
                y,
                size,
                size,
                bubbleColor,
                painter
            );
        }
    }

    /**
     * Draws a filled oval using the Painter API.
     */
    public static void drawOvalShape(
        double x,
        double y,
        double width,
        double height,
        Color color,
        PainterAPI painter
    ) {

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
     * Draws a filled triangle pointing left or right.
     */
    public static void drawTriangleShape(
        double x,
        double y,
        double width,
        double height,
        Color color,
        boolean pointRight,
        PainterAPI painter
    ) {

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