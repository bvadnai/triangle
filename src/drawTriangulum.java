import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class drawTriangulum {

  private static final int NUMBER_OF_POINTS = 50;

  public static void mainDraw(Graphics graphics) {
    graphics.setColor(Color.blue);
    java.util.List<Point> points = drawRandomPoints(graphics);
//    List<Point> sortedPoints = sortPoints(points);
    drawMaxPerimeter(graphics, points);
  }

  private static java.util.List<Point> drawRandomPoints(Graphics graphics) {
    java.util.List<Point> points = new ArrayList<>();
    Random r = new Random();
    for (int i = 0; i < 6; i++) {
      int x = Math.abs((r.nextInt()) % WIDTH);
      int y = Math.abs((r.nextInt()) % HEIGHT);
      Point point = new Point(Math.abs(x), Math.abs(y));
      points.add(point);
      graphics.drawLine(x, y, x, y);
    }
    return points;
  }

  private static void drawMaxPerimeter(Graphics graphics, List<Point> points) {
    int maxArea = Integer.MIN_VALUE;
    System.out.println("points in dras" + points);
//    List<Point> sortedPoints = sortPoints(points);
    List<Point> triangleCoordinates = new ArrayList<>();
    List<Point> maxTriangleCoordinates = new ArrayList<>();
    for (int i = 0; i < points.size() - 2; i++) {
      for (int j = i + 1; j < points.size() - 1; j++) {
        if (points.get(i).getX() == points.get(j).getX()) {
          continue;
        }
        for (int k = j; k < points.size(); k++) {
//          Point first = new Point(points.get(i).getX(), points.get(i).getY());
//          triangleCoordinates.add(first);
//          System.out.println("first " + first);
//          Point second = new Point(points.get(j).getX(), points.get(j).getY());
//          triangleCoordinates.add(second);
//          System.out.println("second " + second);
//          Point third = new Point(points.get(k).getX(), points.get(k).getY());
//          triangleCoordinates.add(third);
//          System.out.println("third " + third);
          triangleCoordinates.add(new Point(points.get(i).getX(), points.get(i).getY()));
          triangleCoordinates.add(new Point(points.get(j).getX(), points.get(j).getY()));
          triangleCoordinates.add(new Point(points.get(k).getX(), points.get(k).getY()));
          System.out.println("trinaglecoord" + triangleCoordinates);
          int currentArea = calculateArea(triangleCoordinates);
          System.out.println("currentArea " + currentArea);
          if (maxArea < currentArea) {
            maxArea = currentArea;
            System.out.println("maxArea " + maxArea);
            maxTriangleCoordinates.clear();
            System.out.println("maxtriangle" + maxTriangleCoordinates);
            maxTriangleCoordinates.addAll(triangleCoordinates);
            System.out.println("max" + maxTriangleCoordinates);
//            triangleCoordinates.removeAll(triangleCoordinates);
            System.out.println("trinaglecoord ures" + triangleCoordinates);
//            maxArea = maxArea(maxArea, currentArea, maxTriangleCoordinates);
          }
        }
      }
    }
    System.out.println("second maxTriab=ngle " + maxTriangleCoordinates);
    drawTriangle(graphics, maxTriangleCoordinates);
  }

  private static int calculateArea (List < Point > points) {
    return Math.abs(points.get(0).getX() * (points.get(1).getY() - points.get(2).getY()) + points.get(1).getX() * (points.get(2).getY() - points.get(0).getY()) + points.get(2).getX() * (points.get(0).getY() - points.get(1).getY())) / 2;
  }

  private static void drawTriangle(Graphics graphics, java.util.List<Point> points) {
    graphics.drawPolygon(new int[]{points.get(0).getX(), points.get(1).getX(), points.get(2).getX()},
        new int[]{points.get(0).getY(), points.get(1).getY(), points.get(2).getY()}, 3);
  }


//  private static List<Point> sortPoints(List<Point> points) {
//    points.sort(new Comparator<Point>() {
//      public int compare(Point a, Point b) {
//        int result = Integer.compare((int) a.getX(), (int) b.getX());
//        if (result == 0) {
//          result = Integer.compare((int) a.getY(), (int) b.getY());
//        }
//        return result;
//      }
//    });
//
//    List<Point> convertedPtList = new ArrayList<>();
//    int smallestX = (int) points.get(0).getX();
//    int smallestY = (int) points.get(0).getY();
//    for (int i = 1; i < points.size(); i++) {
//      int x = ((int) points.get(i).getX() - smallestX);
//      int y = ((int) points.get(i).getY() - smallestY);
//      convertedPtList.add(new Point(x, y));
//    }
//    return convertedPtList;
//  }

//  private static int calculateArea(Integer[] X, Integer[] Y) {
//    return Math.abs(X[0] * (Y[1] - Y[2]) + X[1] * (Y[2] - Y[0]) + X[2] * (Y[0] - Y[1])) / 2;
//  }

    private static int WIDTH = 320;
    private static int HEIGHT = 320;

    public static void main (String[]args){
      JFrame jFrame = new JFrame("Drawing");
      jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
      ImagePanel panel = new ImagePanel();
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      jFrame.add(panel);
      jFrame.setLocationRelativeTo(null);
      jFrame.setVisible(true);
      jFrame.pack();
    }

    static class ImagePanel extends JPanel {
      @Override
      protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        mainDraw(graphics);
      }
    }
  }
