package TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests;

import TiaLU.Physics.JPhysicsLib.main.library.dynamics.World;
import TiaLU.Physics.JPhysicsLib.main.library.math.Vectors2D;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.TestBedWindow;

public class Car {
    public static final String[] text = {"Car:"};

    public static void load(TestBedWindow testBedWindow) {
        testBedWindow.setWorld(new World(new Vectors2D(0, 0)));
        World world = testBedWindow.getWorld();
        testBedWindow.setCamera(new Vectors2D(0, 0), 1.4);

    }
}
