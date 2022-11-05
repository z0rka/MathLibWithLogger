package ua.hillelit.lms.logger.exeptions;

/**
 * exception if max file size for file logger reached
 */
public class FileMaxSizeReached extends Throwable {

  public FileMaxSizeReached(String message) {
    super(message);
  }
}
