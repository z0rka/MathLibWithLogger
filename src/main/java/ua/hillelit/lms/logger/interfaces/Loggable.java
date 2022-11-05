package ua.hillelit.lms.logger.interfaces;

/**
 * Api for loggers
 */
public interface Loggable {

  void debug(String message);

  void info(String message);

  void warning(String message);

  void error(String message);

}
