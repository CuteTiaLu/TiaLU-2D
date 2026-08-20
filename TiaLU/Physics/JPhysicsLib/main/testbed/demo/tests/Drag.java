package TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests;

import TiaLU.Physics.JPhysicsLib.main.library.dynamics.Body;
import TiaLU.Physics.JPhysicsLib.main.library.dynamics.World;
import TiaLU.Physics.JPhysicsLib.main.library.geometry.Circle;
import TiaLU.Physics.JPhysicsLib.main.library.geometry.Polygon;
import TiaLU.Physics.JPhysicsLib.main.library.math.Vectors2D;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.TestBedWindow;

public class Drag {
    public static final String[] text = {"Drag:"};

    public static void load(TestBedWindow testBedWindow) {
        testBedWindow.setWorld(new World(new Vectors2D(0, -9.81)));
        World temp = testBedWindow.getWorld();

        for (int i = 0; i < 13; i++) {
            Body b1 = new Body(new Circle(10.0), -190 + (30 * i), 100);
            b1.linearDampening = 1.0 * i;
            temp.addBody(b1);
            b1.restitution = 0;
        }

        Body b4 = new Body(new Polygon(200.0, 10.0), 0, -100);
        b4.setDensity(0);
        b4.restitution = 1;
        temp.addBody(b4);
    }
}
