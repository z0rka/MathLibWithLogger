package ua.hillelit.lms.logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Properties;
import ua.hillelit.lms.logger.enums.LoggingLevel;
import ua.hillelit.lms.logger.enums.PropertiesForFile;

/**
 * Class that loads configuration
 */
class FileLoggerConfigurationLoader {

  /**
   * Method that loads configuration from Property file
   *
   * @return FileLoggerConfiguration
   */
  private static FileLoggerConfiguration loader() {
    FileLoggerConfiguration fileLoggerConfiguration = new FileLoggerConfiguration();

    try (InputStream input = Files.newInputStream(
        Paths.get("src/ua/ithillel/lms/homework7/model/resourse/config.properties"))) {

      Properties properties = new Properties();

      properties.load(input);

      fileLoggerConfiguration.setFileName(properties.getProperty(
          String.valueOf(PropertiesForFile.FILE)));

      fileLoggerConfiguration.loggingLevel = LoggingLevel.valueOf(properties.getProperty(
          String.valueOf(PropertiesForFile.LEVEL)));

      fileLoggerConfiguration.setFileSize(Integer.parseInt(properties.getProperty(
          String.valueOf(PropertiesForFile.MAX_SIZE))));

      String template = properties.getProperty(String.valueOf(PropertiesForFile.FORMAT));

      fileLoggerConfiguration.recordingFormat = String.format(template, LocalDateTime.now(),
          fileLoggerConfiguration.loggingLevel);


    } catch (IOException ex) {
      ex.printStackTrace();
    }

    return fileLoggerConfiguration;
  }

  private FileLoggerConfigurationLoader() {
  }

  /**
   * Method that calls private loader
   *
   * @return FileLoggerConfiguration
   */
  public static FileLoggerConfiguration load() {
    return loader();
  }

}
