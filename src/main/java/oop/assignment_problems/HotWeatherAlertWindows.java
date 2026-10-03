package oop.assignment_problems;

public class HotWeatherAlertWindows {

    public static void main(String[] args) {

        int[] readings = {
                2, 2, 2, 2, 5, 5, 5, 8
        };

        int k = 3;
        int threshold = 4;

        int result =
                countAlerts(
                        readings,
                        k,
                        threshold
                );

        System.out.println(result);
    }

    public static int countAlerts(
            int[] readings,
            int k,
            int threshold) {

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        int alerts = 0;

        if (sum >= k * threshold) {
            alerts++;
        }

        for (int i = k; i < readings.length; i++) {

            sum += readings[i];
            sum -= readings[i - k];

            if (sum >= k * threshold) {
                alerts++;
            }
        }

        return alerts;
    }
}