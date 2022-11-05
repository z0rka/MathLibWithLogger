package ua.hillelit.lms.logger;

import static java.lang.System.*;

import ua.hillelit.lms.logger.enums.LoggingLevel;

/**
 * Class that provides Configuration for {@link FileLogger}
 */
class FileLoggerConfiguration extends AbstractLoggerConfiguration {

  private String fileName;
  private int fileSize = 400;

  public FileLoggerConfiguration() {
  }

  /**
   * Constructor to set file name and logging level
   */
  public FileLoggerConfiguration(String fileName, LoggingLevel loggingLevel) {
    super(loggingLevel);
    this.fileName = fileName;
  }

  public int getFileSize() {
    return fileSize;
  }

  public String getFileName() {
    return fileName;
  }

  /**
   * File size setter
   */
  public void setFileSize(int fileSize) {
    if (fileSize < 1) {
      this.fileSize = 400;
      out.println("Incorrect file size , set as default (400)");
    }

    this.fileSize = fileSize;
  }

  /**
   * File name setter
   */
  protected void setFileName(String fileName) {
    this.fileName = fileName;
  }
}
