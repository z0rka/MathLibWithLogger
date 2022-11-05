package ua.hillelit.lms.math;

import ua.hillelit.lms.logger.interfaces.Loggable;

/**
 * @author Kostiantyn Kvartyrmeister on 04.11.2022
 */
public class MatrixHandler {

  /**
   * Logger that logs errors
   */
  private final Loggable logger;

  /**
   * Method check if matrix 1 and matrix 2 equals in size
   *
   * @param matrix  one
   * @param matrix2 two
   * @return equals?
   */
  private boolean matrixEquals(double[][] matrix, double[][] matrix2) {
    return matrix.length == matrix2.length && matrix[0].length == matrix2[0].length;
  }

  /**
   * Method checks if matrix is null
   *
   * @param matrix to check
   * @return boolean
   */
  private boolean matrixIsNull(double[][] matrix) {
    if (null == matrix) {
      logger.error("Matrix is null!!");

      return true;
    }
    return false;
  }

  /**
   * We can`t multiply every matrix on every matrix , there are some issues , this method checks
   * them
   */
  private boolean matrixMultipliable(double[][] matrix, double[][] matrix2) {
    return matrix[0].length == matrix2.length;
  }

  /**
   * Private method that finds det of matrix
   *
   * @param matrix our square matrix
   * @param n      size of matrix
   * @return det of matrix
   */

  private double det(double[][] matrix, int n) {
    //if matrix size == 1 , its det = el in cell [0][0]
    if (n == 1) {
      return matrix[0][0];
    }

    double detAns = 0;//our det
    double[][] buffMatrix = new double[n - 1][n - 1];//buff matrix that smaller for 1 row and coll
    int l = 1;//value for proper count , when we count matrix det - we divide it to smaller one
    // and to count , we have to multiply smaller matrix on 1, then on -1 ets

    //cycle of counting
    for (int i = 0; i < n; ++i) {

      //values for buffMatrix
      int x = 0;
      int y = 0;

      for (int j = 1; j < n; ++j) {
        for (int k = 0; k < n; ++k) {
          if (i == k) {
            continue;
          }

          //writing down smaller matrix
          buffMatrix[x][y] = matrix[j][k];
          ++y;

          if (y == n - 1) {
            y = 0;
            ++x;
          }
        }
      }
      //count our det of matrix with recursion call of method det
      detAns += l * matrix[0][i] * det(buffMatrix, buffMatrix.length);
      l *= (-1);
    }

    return detAns;
  }

  /**
   * Method multiply each el on multiplier
   *
   * @param matrix     our  matrix
   * @param n          rows of matrix
   * @param m          col of matrix
   * @param multiplier our multiply el
   */
  private void multiply(double[][] matrix, int n, int m, int multiplier) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] *= multiplier;
      }
    }
  }


  /**
   * Method multiply matrix on matrix
   *
   * @param matrix     matrix to multiply
   * @param multiplier matrix we multiply on
   * @return resultMatrix
   */
  private double[][] multiplyMatrix(double[][] matrix, double[][] multiplier) {
    int n = matrix.length;
    int m = matrix[0].length;
    double[][] resultMatrix = new double[n][m];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < multiplier.length; j++) {
        for (int k = 0; k < multiplier[j].length; k++) {
          resultMatrix[i][j] += matrix[i][j] * multiplier[k][j];
        }
      }
    }
    return resultMatrix;
  }

  /**
   * Method adds matrix to matrix
   *
   * @param matrix  first matrix
   * @param matrix2 second matrix
   * @return their sum
   */
  private double[][] addition(double[][] matrix, double[][] matrix2) {
    int n = matrix.length;
    int m = matrix[0].length;

    double[][] sumMatrix = new double[n][m];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        sumMatrix[i][j] += matrix[i][j] + matrix2[i][j];
      }
    }
    return sumMatrix;
  }

  /**
   * Method that makes transposition of matrix
   *
   * @param matrix to make transposition
   * @param n      size of rows
   * @param m      size of cols
   * @return matrix
   */
  private double[][] transposition(double[][] matrix, int n, int m) {
    double[][] trMatrix = new double[m][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        trMatrix[j][i] = matrix[i][j];
      }
    }
    return trMatrix;
  }


  /**
   * constructor to create Handler for work
   *
   * @param logger type of logger({@link ua.hillelit.lms.logger.FileLogger } or
   *               {@link ua.hillelit.lms.logger.ConsoleLogger}
   */
  public MatrixHandler(Loggable logger) {
    this.logger = logger;
  }

  /**
   * Public method that checks matrix at all criteria and calls finding det method
   *
   * @param matrix matrix to find det
   * @return det matrix
   */
  public double det(double[][] matrix) {

    if (matrixIsNull(matrix)) {
      return 0;
    }

    int n = matrix.length;
    int m = matrix[0].length;

    if (n != m) {
      logger.error("Matrix must be square!");
      return 0;
    } else if (n == 1) {
      logger.info("Det of your matrix is el in cell [0][0]");
      return 0;
    }

    return det(matrix, n);
  }

  /**
   * Checks matrix and call method to change el to opposite
   *
   * @param matrix to change
   */
  public void makeNegative(double[][] matrix) {
    if (matrixIsNull(matrix)) {
      return;
    }

    multiply(matrix, matrix.length, matrix[0].length, -1);
  }

  /**
   * Checks matrix and call method to multiply el
   *
   * @param matrix to change
   */
  public void multiply(double[][] matrix, int multiplier) {
    if (matrixIsNull(matrix)) {
      return;
    }

    multiply(matrix, matrix.length, matrix[0].length, multiplier);
  }

  /**
   * Method checks if params is null , if not calls
   * {@link MatrixHandler#multiplyMatrix(double[][], double[][])}
   *
   * @param matrix     matrix to multiply
   * @param multiplier matrix we multiply on
   * @return result matrix
   */

  double[][] multiply(double[][] matrix, double[][] multiplier) {
    if (matrixIsNull(matrix) || matrixIsNull(multiplier)) {
      return new double[4][4];
    } else if (!matrixMultipliable(matrix, multiplier)) {
      logger.info("Matrix don`t fit");
    }
    return multiplyMatrix(matrix, multiplier);
  }

  /**
   * Checks if and matrix is null or if they are not the same if everything is ok , calls
   * {@link MatrixHandler#addition(double[][], double[][])} method
   *
   * @param matrix  matrix one
   * @param matrix2 matrix two
   * @return result matrix
   */

  public double[][] plus(double[][] matrix, double[][] matrix2) {
    if (matrixIsNull(matrix) || matrixIsNull(matrix2)) {
      return new double[4][4];
    } else if (!matrixEquals(matrix, matrix2)) {
      logger.warning("Matrix are not the same");
      return new double[4][4];
    }

    return addition(matrix, matrix2);
  }

  /**
   * Checks if and matrix is null calls {@link MatrixHandler#transposition(double[][], int, int)}
   * method
   *
   * @param matrix to transpose
   */
  public double[][] transpose(double[][] matrix) {
    if (matrixIsNull(matrix)) {
      return new double[4][4];
    }
    return transposition(matrix, matrix.length, matrix[0].length);
  }
}
