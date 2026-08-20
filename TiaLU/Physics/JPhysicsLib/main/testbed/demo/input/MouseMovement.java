package TiaLU.Physics.JPhysicsLib.main.testbed.demo.input;

import TiaLU.Physics.JPhysicsLib.main.library.explosions.ProximityExplosion;
import TiaLU.Physics.JPhysicsLib.main.library.math.Vectors2D;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.TestBedWindow;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests.LineOfSight;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests.ProximityExplosionTest;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests.RaycastExplosionTest;
import TiaLU.Physics.JPhysicsLib.main.testbed.demo.tests.SliceObjects;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMovement extends TestbedControls implements MouseMotionListener {
    public MouseMovement(TestBedWindow testBedWindow) {
        super(testBedWindow);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            Vectors2D pw = CAMERA.convertToWorld(new Vectors2D(e.getX(), e.getY()));
            Vectors2D diff = pw.subtract(CAMERA.getPointClicked());
            CAMERA.setCentre(CAMERA.centre.subtract(diff));
        } else {
            Vectors2D v = CAMERA.convertToWorld(new Vectors2D(e.getX(), e.getY()));
            if (ProximityExplosionTest.active) {
                ProximityExplosion p = (ProximityExplosion) TESTBED.getRayExplosions().get(0);
                p.setEpicentre(v);
            } else if (RaycastExplosionTest.active) {
                RaycastExplosionTest.r.setEpicentre(v);
            } else if (LineOfSight.active) {
                LineOfSight.b.setStartPoint(v);
            } else if (TESTBED.getSlicesSize() == 1 && !SwingUtilities.isRightMouseButton(e) && SliceObjects.active) {
                TESTBED.getSlices().get(0).setDirection(v);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!SwingUtilities.isRightMouseButton(e)) {
            Vectors2D v = CAMERA.convertToWorld(new Vectors2D(e.getX(), e.getY()));
            if (ProximityExplosionTest.active) {
                ProximityExplosion p = (ProximityExplosion) TESTBED.getRayExplosions().get(0);
                p.setEpicentre(v);
            } else if (RaycastExplosionTest.active) {
                RaycastExplosionTest.r.setEpicentre(v);
            } else if (LineOfSight.active) {
                LineOfSight.b.setStartPoint(v);
            } else if (TESTBED.getSlicesSize() == 1 && !SwingUtilities.isRightMouseButton(e) && SliceObjects.active) {
                TESTBED.getSlices().get(0).setDirection(v);
            }
        }
    }
}
