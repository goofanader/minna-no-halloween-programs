/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animehalloweenprograms.animetrivia;

import animehalloweenprograms.BeginAnimeProgram;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Phyllis
 */
public class AnimeTriviaGameBoard implements Runnable {

    private Controller test;
    private AnimeTriviaHost host;
    private boolean isExitingProgram, isPlaying, isQuestion, isAnswer, isCategory, isPaused;
    private long lastFrame, lastFPS;
    private int fps, maxFPS, width, height;
    private TextureLoader textureLoader;
    private Sprite questionableLogo;
    private static final int MAX_FPS = 120;
    private static final int MIN_FPS = 60;
    private static final int INIT_WIDTH = 800;
    private static final int INIT_HEIGHT = 600;

    public AnimeTriviaGameBoard(AnimeTriviaHost host) {
        this.host = host;
        test = null;
        isExitingProgram = isQuestion = isAnswer = isCategory = isPlaying = isPaused = false;

        maxFPS = MAX_FPS;

        lastFrame = lastFPS = fps = 0;
        width = INIT_WIDTH;
        height = INIT_HEIGHT;
    }

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setResizable(true);
            Display.setTitle("Anime Trivia (FPS: " + maxFPS + ")");
            Display.create();

            Controllers.create();

            int controllerIndex = -1;
            test = null;

            if (Controllers.getControllerCount() > 0) {
                for (int i = 0; i < Controllers.getControllerCount(); i++) {
                    String name = Controllers.getController(i).getName();
                    System.out.print(name);

                    if (i != Controllers.getControllerCount() - 1) {
                        System.out.print(", ");
                    }

                    if (controllerIndex == -1
                            && (/*name.toLowerCase().contains("playstation") || */name.toLowerCase().contains("xbox"))) {
                        controllerIndex = i;
                    }
                }

                System.out.println("======");

                if (controllerIndex != -1) {
                    test = Controllers.getController(controllerIndex);

                    System.out.println("Controller chosen is " + test.getName());
                    System.out.println("It has " + test.getButtonCount() + " buttons.");
                    System.out.println("Button names:");
                    for (int i = 0; i < test.getButtonCount(); i++) {
                        System.out.println("   " + test.getButtonName(i));
                    }
                } else {
                    System.out.println("Please connect a controller!");
                    //System.exit(1);
                }
            }

        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        // init OpenGL here
        initOpenGL();

        runBoard();

        Controllers.destroy();
        Display.destroy();
        System.exit(0);
    }

    private void initOpenGL() {
        //enable textures since we're going to use these for our sprites
        glEnable(GL_TEXTURE_2D);

        //disable the OpenGL depth test since we're rendering 2D graphics
        glDisable(GL_DEPTH_TEST);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0, width, height, 0, -1, 1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glViewport(0, 0, width, height);

        textureLoader = new TextureLoader();

        //we'd put sound here as per the Space Invaders example game
        //do it later, though.

        questionableLogo = getSprite("animehalloweenprograms/images/FakeQuestionableLogo.png");
    }

    /**
     * Create or get a sprite which displays the image that is pointed to in the
     * classpath by "ref"
     *
     * @param ref A reference to the image to load
     * @return A sprite that can be drawn onto the current graphics context.
     */
    public Sprite getSprite(String ref) {
        return new Sprite(textureLoader, ref);
    }

    private void runBoard() {
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        while (!Display.isCloseRequested() && !isExitingProgram) {
            int delta = getDelta();
            updateFPS();
            
            width = Display.getWidth();
            height = Display.getHeight();

            // render OpenGL here

            pollInput();
            
            int drawWidth = (width / 2) - (questionableLogo.getWidth() / 2);
            if (drawWidth < 0) {
                drawWidth = 0;
            }
            
            int drawHeight = (height / 2) - (questionableLogo.getHeight() / 2);
            if (drawHeight < 0) {
                drawHeight = 0;
            }
            
            questionableLogo.draw(drawWidth, drawHeight);
            Display.update();
            Display.sync(maxFPS); // cap fps to 60fps
        }
    }

    /**
     * Calculate how many milliseconds have passed since last frame.
     *
     * @return milliseconds passed since last frame
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }

    /**
     * Get the accurate system time
     *
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            Display.setTitle("Anime Trivia (FPS: " + fps + ")");
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    public void pollInput() {

        /*if (Mouse.isButtonDown(0)) {
         int x = Mouse.getX();
         int y = Mouse.getY();
			
         System.out.println("MOUSE DOWN @ X: " + x + " Y: " + y);
         }
		
         if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
         System.out.println("SPACE KEY IS DOWN");
         }
		
         while (Keyboard.next()) {
         if (Keyboard.getEventKeyState()) {
         if (Keyboard.getEventKey() == Keyboard.KEY_A) {
         System.out.println("A Key Pressed");
         }
         if (Keyboard.getEventKey() == Keyboard.KEY_S) {
         System.out.println("S Key Pressed");
         }
         if (Keyboard.getEventKey() == Keyboard.KEY_D) {
         System.out.println("D Key Pressed");
         }
         } else {
         if (Keyboard.getEventKey() == Keyboard.KEY_A) {
         System.out.println("A Key Released");
         }
         if (Keyboard.getEventKey() == Keyboard.KEY_S) {
         System.out.println("S Key Released");
         }
         if (Keyboard.getEventKey() == Keyboard.KEY_D) {
         System.out.println("D Key Released");
         }
         }
         }*/
        //if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
        //System.out.println("Controller count: " + Controllers.getControllerCount());
        //}

        if (test != null) {
            for (int i = 0; i < test.getButtonCount(); i++) {
                if (test.isButtonPressed(i)) {
                    System.out.println(test.getButtonName(i) + "\n======");
                }
            }
        }
    }

    public void endDisplay() {
        isExitingProgram = true;
    }

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        // TODO code application logic here
        //AnimeHalloweenPrograms animeProgram = new AnimeHalloweenPrograms();
        //animeProgram.start();

        BeginAnimeProgram startFrame = new BeginAnimeProgram();
        startFrame.setVisible(true);
    }*/

    @Override
    public void run() {
        start();
    }
}
