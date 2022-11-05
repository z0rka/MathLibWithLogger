package ua.hillelit.lms.logger;

import static java.lang.System.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ua.hillelit.lms.logger.enums.LoggingLevel;
import ua.hillelit.lms.logger.exeptions.FileMaxSizeReached;
import ua.hillelit.lms.logger.interfaces.Loggable;

/**
 * Logger for files
 */
public class FileLogger implements Loggable {

  private static final String SET_HIGHER_LEVEL = "Set higher logging level";

  private FileLoggerConfiguration loggerConfiguration = new FileLoggerConfiguration(
      "logger.txt", LoggingLevel.DEBUG);

  /**
   * Method writes new message to file ,if size of file ends calls method to create new one
   *
   * @param message      - our message
   * @param loggingLevel - level of log
   */
  private void writerToFile(String message, LoggingLevel loggingLevel) {

    loggerConfiguration.setRecordingFormat(message, loggingLevel);

    String fullReport = loggerConfiguration.recordingFormat;

    try {
      checkBytesInFile(fullReport);
    } catch (FileMaxSizeReached e) {
      exceptionRecord(e.getMessage());
    }

    try (FileWriter writer = new FileWriter(loggerConfiguration.getFileName(), true)) {

      writer.write("\n" + fullReport);
      writer.flush();

    } catch (IOException ex) {
      out.println(ex.getMessage());

    }
  }

  /**
   * Method checks if file is full , if yes - calls method to change it
   *
   * @param fullReport out message, we want to write down
   */

  private void checkBytesInFile(String fullReport) throws FileMaxSizeReached {
    File file = new File(loggerConfiguration.getFileName());

    if (file.length() + fullReport.length() > loggerConfiguration.getFileSize()) {
      NewFileCreator.setNewFileName(loggerConfiguration);

      throw new FileMaxSizeReached(loggerConfiguration.getFileName() + " Max size is " +
          loggerConfiguration.getFileSize() + " Size we got " + file.length());
    }
  }

  /**
   * Method that writes down exceptions to specific file
   *
   * @param message to write down
   */
  private void exceptionRecord(String message) {
    try (FileWriter writer = new FileWriter("exceptions.txt", true)) {

      writer.write("\n" + message);
      writer.flush();

    } catch (IOException ex) {
      out.println(ex.getMessage());

    }
  }

  public FileLogger() {
    super();
  }

  /**
   * Method for Logging debug
   *
   * @param message to write down
   */
  @Override
  public void debug(String message) {
    if (loggerConfiguration.loggingLevel != LoggingLevel.DEBUG) {

      out.println();
      return;
    }

    writerToFile(message, LoggingLevel.DEBUG);
  }

  /**
   * Method for Logging info
   *
   * @param message to write down
   */
  @Override
  public void info(String message) {
    if (loggerConfiguration.loggingLevel != LoggingLevel.DEBUG
        && loggerConfiguration.loggingLevel != LoggingLevel.INFO) {
      out.println(SET_HIGHER_LEVEL);
      return;
    }
    writerToFile(message, LoggingLevel.INFO);
  }

  /**
   * Method for Logging warning
   *
   * @param message to write down
   */
  @Override
  public void warning(String message) {
    if (loggerConfiguration.loggingLevel != LoggingLevel.DEBUG
        && loggerConfiguration.loggingLevel != LoggingLevel.INFO
        && loggerConfiguration.loggingLevel != LoggingLevel.WARNING) {
      out.println(SET_HIGHER_LEVEL);
      return;
    }
    writerToFile(message, LoggingLevel.WARNING);
  }

  /**
   * Method for Logging error
   *
   * @param message to write down
   */
  @Override
  public void error(String message) {
    writerToFile(message, LoggingLevel.ERROR);
  }

  /**
   * Method to load configuration
   */
  public void loadConfiguration() {
    loggerConfiguration = FileLoggerConfigurationLoader.load();
  }

  /**
   * Method to set  LoggingLevel
   */
  public void setLoggingLevel(LoggingLevel loggingLevel) {
    loggerConfiguration.loggingLevel = loggingLevel;
  }
}
