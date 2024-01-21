package org.firstinspires.ftc.teamcode.helpers;

public class Common {
    /**
     * Bounds a number between a specified maximum and minimum
     *
     * @param num any integer
     * @param max minimum integer
     * @param min maximum integer
     * @return num bounded between max and min
     */
    public static int bound(int num, int max, int min){
        if (num > max) {
            return max;
        }
        else if (num < min) {
            return min;
        }
        else {
            return num;
        }
    }

    public static int wrap(int num, int max, int min){
        if (num > max) {
            return Math.abs(num-max);
        }
        else if (num < min) {
            return Math.abs(max-num);
        }
        else {
            return num;
        }
    }
}
