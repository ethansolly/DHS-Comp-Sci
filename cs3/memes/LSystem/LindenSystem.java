package memes.LSystem;

import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import java.awt.*;

public class LindenSystem extends JFrame {
    public LindenSystem() {
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas3D = new Canvas3D(gc);
        add(canvas3D);

        BranchGroup scene = new BranchGroup();
        Appearance appearance = new Appearance();
        ColoringAttributes ca = new ColoringAttributes(new Color3f(204.0f, 204.0f, 204.0f), ColoringAttributes.FASTEST);
        appearance.setColoringAttributes(ca);



        scene.compile();
        SimpleUniverse simpleUniverse = new SimpleUniverse(canvas3D);
        simpleUniverse.getViewingPlatform().setNominalViewingTransform();
        simpleUniverse.addBranchGraph(scene);


    }

    public void paint(Graphics g) {

    }
}
