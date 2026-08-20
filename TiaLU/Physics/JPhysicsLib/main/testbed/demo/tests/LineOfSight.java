package TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests;

import TiaLU.Physics.JPhysicsLib.main.library.rays.ShadowCasting;
import TiaLU.Physics.JPhysicsLib.main.library.dynamics.World;
import TiaLU.Physics.JPhysicsLib.main.library.math.Vectors2D;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.TestBedWindow;

import java.awt.*;

public class LineOfSight {
    public static final String[] text = {"Line of sight:", "Mouse: Move mouse to change position of raycast"};
    public static boolean active = false;
    public static ShadowCasting b;

    public static void load(TestBedWindow testBedWindow) {
        testBedWindow.setWorld(new World(new Vectors2D(0, -9.81)));
        testBedWindow.setCamera(new Vectors2D(-120, 20), 3.3);
        active = true;

        testBedWindow.generateBoxOfObjects();

        b = new ShadowCasting(new Vectors2D(-1000, 0), 11000);
        testBedWindow.add(b);
    }

    public static void drawInfo(Graphics2D g, int x, int y) {
        g.drawString("No of rays: " + b.getNoOfRays(), x, y);
    }
}
