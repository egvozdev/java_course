package ru.course;

public class hello {
    public static void main(String[] srgs) {
        String s = "some";
        System.out.println("Hello world");
        System.out.println(2+"2");
        Point p1 =  new Point(-1,2);
        Point p2 =  new Point(2,1);

        System.out.println(distance(p1, p2));
        System.out.println(p1.distance(p2));
    }
    public static double distance(Point p1, Point p2) {
        return Math.sqrt( (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
    }
}
