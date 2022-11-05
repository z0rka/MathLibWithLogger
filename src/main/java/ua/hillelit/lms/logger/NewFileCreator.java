package ua.hillelit.lms.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Package-private class that creates new file for {@link FileLogger}
 */
class NewFileCreator {

  private static int i = 1;

  private NewFileCreator() {
  }

  /**
   * Method sets file name for logger conf
   */
  static void setNewFileName(FileLoggerConfiguration loggerConfiguration) {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss");

    loggerConfiguration.setFileName(
        loggerConfiguration.getFileName() + i + "_" + formatter.format(date) + ".txt");
    i++;
  }
}
