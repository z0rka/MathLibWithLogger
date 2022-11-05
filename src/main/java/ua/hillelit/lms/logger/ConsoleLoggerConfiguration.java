package ua.hillelit.lms.logger;

import ua.hillelit.lms.logger.enums.LoggingLevel;

/**
 * @author Kostiantyn Kvartyrmeister on 05.11.2022 class that makes configuration for console logger
 */
public class ConsoleLoggerConfiguration extends AbstractLoggerConfiguration {

  public ConsoleLoggerConfiguration(LoggingLevel loggingLevel) {
    super(loggingLevel);
  }
}
