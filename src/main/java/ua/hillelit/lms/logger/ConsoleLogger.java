package ua.hillelit.lms.logger;


import ua.hillelit.lms.logger.enums.LoggingLevel;
import ua.hillelit.lms.logger.interfaces.Loggable;

/**
 * @author Kostiantyn Kvartyrmeister on 05.11.2022 class - logger for console
 */
public class ConsoleLogger implements Loggable {

  private static final String SET_HIGHER_LEVEL = "Set higher logging level";

  private final ConsoleLoggerConfiguration configuration = new ConsoleLoggerConfiguration(
      LoggingLevel.DEBUG);

  /**
   * Whiter to console
   *
   * @param message      to write
   * @param loggingLevel of log
   */
  private void writer(String message, LoggingLevel loggingLevel) {
    configuration.setRecordingFormat(message, loggingLevel);

    System.out.println(configuration.recordingFormat);
  }

  public ConsoleLogger() {
    //just empty constructor to create object
  }

  /**
   * Method for Logging debug
   *
   * @param message to write down
   */
  @Override
  public void debug(String message) {
    if (configuration.loggingLevel != LoggingLevel.DEBUG) {

      System.out.println(SET_HIGHER_LEVEL);
      return;
    }

    writer(message, LoggingLevel.DEBUG);
  }

  /**
   * Method for Logging info
   *
   * @param message to write down
   */
  @Override
  public void info(String message) {
    if (configuration.loggingLevel != LoggingLevel.DEBUG
        && configuration.loggingLevel != LoggingLevel.INFO) {
      System.out.println(SET_HIGHER_LEVEL);
      return;
    }
    writer(message, LoggingLevel.INFO);
  }

  /**
   * Method for Logging warning
   *
   * @param message to write down
   */
  @Override
  public void warning(String message) {
    if (configuration.loggingLevel != LoggingLevel.DEBUG
        && configuration.loggingLevel != LoggingLevel.INFO
        && configuration.loggingLevel != LoggingLevel.WARNING) {
      System.out.println(SET_HIGHER_LEVEL);
      return;
    }
    writer(message, LoggingLevel.WARNING);
  }

  /**
   * Method for Logging error
   *
   * @param message to write down
   */
  @Override
  public void error(String message) {
    writer(message, LoggingLevel.ERROR);
  }

  /**
   * Method to set logging level
   *
   * @param loggingLevel to set
   */
  public void setLoggingLevel(LoggingLevel loggingLevel) {
    configuration.loggingLevel = loggingLevel;
  }

}
