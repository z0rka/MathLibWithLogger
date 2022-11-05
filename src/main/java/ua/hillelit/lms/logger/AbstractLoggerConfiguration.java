package ua.hillelit.lms.logger;

import java.util.Date;
import ua.hillelit.lms.logger.enums.LoggingLevel;

/**
 * abstract logger configuration
 */
public abstract class AbstractLoggerConfiguration {

  protected LoggingLevel loggingLevel;
  protected Date date = new Date();
  protected String recordingFormat =
      "|" + date + "|" + loggingLevel + "|" + " Message : ";

  protected AbstractLoggerConfiguration() {
  }

  protected AbstractLoggerConfiguration(LoggingLevel loggingLevel) {
    this.loggingLevel = loggingLevel;
  }

  /**
   * Method to set recording format
   */
  public void setRecordingFormat(String message, LoggingLevel loggingLevel) {
    this.recordingFormat = "|" + date.toString() + "|" + loggingLevel
        + "|" + " Message : " + message;
  }

}
