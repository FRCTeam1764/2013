/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.commands.Track;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 *
 * @author bigcompy
 */
public class Camera extends Subsystem {
    
    AxisCamera camera;
    CriteriaCollection cc;
    
    public boolean trigger = false;
    
    public ParticleAnalysisReport[] particles;
    public RobotDrive drive;
    
    public int x = 0;
    public int y = 0;
    public int height = 0;
    
    protected void initDefaultCommand() {
    }
    
    //Begin Score Class.
        public class Scores 
        {
            int center_mass_x;
            int center_mass_y;
            int boundingRectHeight;
        }
    //End Score Class.
    
    private static Camera instance = null;

    public static Camera getInstance() {
        if(instance == null) {
            instance = new Camera();
            instance.setDefaultCommand(new Track());
        }
        return instance;
    }
    
    private Camera() {
        camera = AxisCamera.getInstance();
    }
    
    public void track(boolean go) {
        if(go) {
            try {
                ColorImage image;
                image = camera.getImage();
                BinaryImage thresholdImage = image.thresholdRGB(0, 100, 230, 255, 230, 255);
                particles = thresholdImage.getOrderedParticleAnalysisReports();
                x = particles[0].center_mass_x;
                y = particles[0].center_mass_y;
                height = particles[0].boundingRectHeight;
                image.free();
                thresholdImage.free();
            } catch (AxisCameraException ex) {
            } catch (NIVisionException ex) {
            }
        }
    }
}