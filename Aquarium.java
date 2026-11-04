
import edu.cymbal.graphics.CanvasAPI;
import edu.cymbal.graphics.Graphics;
import edu.cymbal.graphics.PainterAPI;
import java.awt.Color;

public class Aquarium {

    public static void main(String[] args) {
        CanvasAPI canvas = Graphics.createCanvas(800, 600, "My Aquarium");

        PainterAPI painter = canvas.getPainter();
        painter.setSpeed(10);

        // Three different PainterAPI method calls
        painter.forward(100);
        painter.turnRight(90);
        painter.circle(50);
    }

    /**
     * Organizes all the elements in the aquarium scene.
     */
    public static void drawAquarium() {
        // Calls drawSchoolOfFish
        // Calls drawShark
        // Calls drawSeaweed
        // Calls drawCoral
        // Calls drawBubbles
    }

    /**
     * Draws a school of six fish in different sizes and colors.
     */
    public static void drawSchoolOfFish() {
        // Calls drawFish multiple times
    }

    /**
     * Draws an individual fish.
     *
     * @param xPosition the horizontal position of the fish
     * @param yPosition the vertical position of the fish
     * @param size the overall size of the fish
     * @param bodyColor the color of the fish
     */
    public static void drawFish(int xPosition, int yPosition,
                                int size, Color bodyColor) {
        // Calls drawOvalShape
        // Calls drawTriangleShape
    }

    /**
     * Draws a shark facing the specified direction.
     *
     * @param xPosition the horizontal position of the shark
     * @param yPosition the vertical position of the shark
     * @param size the overall size of the shark
     * @param facingRight determines which direction the shark faces
     */
    public static void drawShark(int xPosition, int yPosition,
                                 int size, boolean facingRight) {
        // Calls drawOvalShape
        // Calls drawTriangleShape
    }

    /**
     * Draws seaweed decorations along the bottom of the aquarium.
     */
    public static void drawSeaweed() {
        // Calls drawTriangleShape
    }

    /**
     * Draws pink coral decorations.
     */
    public static void drawCoral() {
        // Calls drawTriangleShape
        // Calls drawOvalShape
    }

    /**
     * Draws bubbles throughout the aquarium.
     */
    public static void drawBubbles() {
        // Calls drawOvalShape
    }

    /**
     * Draws an oval shape using the specified color and dimensions.
     *
     * @param fillColor the color of the oval
     * @param xPosition the horizontal position of the oval
     * @param yPosition the vertical position of the oval
     * @param width the width of the oval
     * @param height the height of the oval
     */
    public static void drawOvalShape(Color fillColor, int xPosition,
                                     int yPosition, int width, int height) {
        // Draws an oval using PainterAPI methods
    }

    /**
     * Draws a triangle shape using the specified color and dimensions.
     *
     * @param fillColor the color of the triangle
     * @param xPosition the horizontal position of the triangle
     * @param yPosition the vertical position of the triangle
     * @param width the width of the triangle
     * @param height the height of the triangle
     * @param pointRight determines whether the triangle points right
     */
    public static void drawTriangleShape(Color fillColor, int xPosition,
                                         int yPosition, int width, int height,
                                         boolean pointRight) {
        // Draws a triangle using PainterAPI methods
    }
}
