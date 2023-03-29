package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {

  private final CANSparkMax m_frontLeftMotor =
      new CANSparkMax(DrivetrainConstants.FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax m_backLeftMotor =
      new CANSparkMax(DrivetrainConstants.BACK_LEFT_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax m_frontRightMotor =
      new CANSparkMax(DrivetrainConstants.FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);
  private final CANSparkMax m_backRightMotor =
      new CANSparkMax(DrivetrainConstants.BACK_RIGHT_MOTOR_ID, MotorType.kBrushless);

  private final MotorControllerGroup m_leftControllerGroup =
      new MotorControllerGroup(m_frontLeftMotor, m_backLeftMotor);
  private final MotorControllerGroup m_rightControllerGroup =
      new MotorControllerGroup(m_frontRightMotor, m_backRightMotor);

  private final DifferentialDrive m_differentialDrive =
      new DifferentialDrive(m_leftControllerGroup, m_rightControllerGroup);

  private DataLog log = DataLogManager.getLog();
  private DoubleLogEntry l_frontLeftMotorCurrent =
      new DoubleLogEntry(log, "/custom/drivetrain/frontleft/current");
  private DoubleLogEntry l_frontLeftMotorTemp =
      new DoubleLogEntry(log, "/custom/drivetrain/frontleft/temp");
  private DoubleLogEntry l_backLeftMotorCurrent =
      new DoubleLogEntry(log, "/custom/drivetrain/backleft/current");
  private DoubleLogEntry l_backLeftMotorTemp =
      new DoubleLogEntry(log, "/custom/drivetrain/backleft/temp");
  // private DoubleLogEntry l_topLeftMotorCurrent = new DoubleLogEntry(log,
  // "/custom/drivetrain/topleft/current");
  // private DoubleLogEntry l_topLeftMotorTemp = new DoubleLogEntry(log,
  // "/custom/drivetrain/topleft/temp");
  private DoubleLogEntry l_frontrightMotorCurrent =
      new DoubleLogEntry(log, "/custom/drivetrain/frontright/current");
  private DoubleLogEntry l_frontrightMotorTemp =
      new DoubleLogEntry(log, "/custom/drivetrain/frontright/temp");
  private DoubleLogEntry l_backrightMotorCurrent =
      new DoubleLogEntry(log, "/custom/drivetrain/backright/current");
  private DoubleLogEntry l_backrightMotorTemp =
      new DoubleLogEntry(log, "/custom/drivetrain/backright/temp");
  // private DoubleLogEntry l_toprightMotorCurrent = new DoubleLogEntry(log,
  // "/custom/drivetrain/topright/current");
  // private DoubleLogEntry l_toprightMotorTemp = new DoubleLogEntry(log,
  // "/custom/drivetrain/topright/temp");

  /** Creates a new Drivetrain. */
  public Drivetrain() {}

  public void arcadeDrive(double speed, double rotation) {
    m_leftControllerGroup.setInverted(true);
    m_differentialDrive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    logDrivetrainMotors();
  }

  private void logDrivetrainMotors() {
    l_frontLeftMotorCurrent.append(m_frontLeftMotor.getOutputCurrent());
    l_frontLeftMotorTemp.append(m_frontLeftMotor.getMotorTemperature());
    l_backLeftMotorCurrent.append(m_backLeftMotor.getOutputCurrent());
    l_backLeftMotorTemp.append(m_backLeftMotor.getMotorTemperature());
    //   l_topLeftMotorCurrent.append(m_topLeftMotor.getOutputCurrent());
    //   l_topLeftMotorTemp.append(m_topLeftMotor.getMotorTemperature());
    l_frontrightMotorCurrent.append(m_frontRightMotor.getOutputCurrent());
    l_frontrightMotorTemp.append(m_frontRightMotor.getMotorTemperature());
    l_backrightMotorCurrent.append(m_backRightMotor.getOutputCurrent());
    l_backrightMotorTemp.append(m_backRightMotor.getMotorTemperature());
    //   l_toprightMotorCurrent.append(m_topRightMotor.getOutputCurrent());
    //   l_toprightMotorTemp.append(m_topRightMotor.getMotorTemperature());
  }
}
