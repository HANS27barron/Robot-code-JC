// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import com.ctre.phoenix.ButtonMonitor;

//import javax.swing.text.AbstractDocument.LeafElement;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.PS4Controller.Button;
//import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   private VictorSPX frontLeft = new VictorSPX (1);
   private VictorSPX frontRight = new VictorSPX(2);
   private VictorSPX backLeft = new VictorSPX(3);
   private VictorSPX backRight = new VictorSPX(4);

   

   private Joystick joy1 = new Joystick(0);
   private double startTime;

   //Joystick buttons 
   public class OI {
    //Joystick leftJoy = new Joystick(0);
    JoystickButton button1 = new JoystickButton(joy1, 1);
    JoystickButton button2 = new JoystickButton(joy1, 2);
    JoystickButton button3 = new JoystickButton(joy1, 3);
    JoystickButton button4 = new JoystickButton(joy1, 4);
 }


  @Override
  public void robotInit() {
    backLeft.follow(frontLeft);
    backRight.follow(frontRight);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    
  //RIGHT es el que jala bien :)
  //6.2 = Giro completo en mismo x,y

  double theTime = time - startTime;

  if (theTime < 2) {
      frontRight.set(VictorSPXControlMode.PercentOutput, -0.25);
      frontLeft.set(VictorSPXControlMode.PercentOutput, 0.25);
      backLeft.set(VictorSPXControlMode.PercentOutput, 0.25);
      backRight.set(VictorSPXControlMode.PercentOutput, -0.25);
  } 
  else if (theTime > 2 && theTime < 5.75) {
      frontRight.set(VictorSPXControlMode.PercentOutput, -0.2);
      frontLeft.set(VictorSPXControlMode.PercentOutput, -0.25);
      backLeft.set(VictorSPXControlMode.PercentOutput, -0.25);
      backRight.set(VictorSPXControlMode.PercentOutput, -0.2);
    } 

    else if (theTime> 5.75 && theTime < 7.5) {
      frontRight.set(VictorSPXControlMode.PercentOutput, -0.25);
      frontLeft.set(VictorSPXControlMode.PercentOutput, 0.25);
      backLeft.set(VictorSPXControlMode.PercentOutput, 0.25);
      backRight.set(VictorSPXControlMode.PercentOutput, -0.25);
    }

    else if (theTime > 7.5 && theTime < 10.75) {
      frontRight.set(VictorSPXControlMode.PercentOutput, -0.2);
      frontLeft.set(VictorSPXControlMode.PercentOutput, -0.25);
      backLeft.set(VictorSPXControlMode.PercentOutput, -0.25);
      backRight.set(VictorSPXControlMode.PercentOutput, -0.2);
    } 

  else {
      frontLeft.set(VictorSPXControlMode.PercentOutput, 0);
      frontRight.set(VictorSPXControlMode.PercentOutput, 0);
      backLeft.set(VictorSPXControlMode.PercentOutput, 0);
      backRight.set(VictorSPXControlMode.PercentOutput, 0);
    }

  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    double speed = -joy1.getRawAxis(1) * 0.4;
    double turn = joy1.getRawAxis(4) * 0.3 ;

    double left = speed + turn;
    double right = speed - turn;

                    frontLeft.set(VictorSPXControlMode.PercentOutput, left);
/*     leftMotor2.set(VictorSPXControlMode.PercentOutput, left);
 */                 frontRight.set(VictorSPXControlMode.PercentOutput, -right);
/*     rightMotor2.set(VictorSPXControlMode.PercentOutput, -right);
 */

 //Izquierda con button     


  }  

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
