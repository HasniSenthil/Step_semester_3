package oop.class_problems;

import java.util.HashSet;

public class PairWithTargetSumUnsortedArray {

    public static void main(String[] args) {

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;

        int[] nums2 = {3, 4, 6};
        int target2 = 20;

        System.out.println(
                hasPairWithSum(nums1, target1)
        );

        System.out.println(
                hasPairWithSum(nums2, target2)
        );
    }

    public static boolean hasPairWithSum(
            int[] nums,
            int target) {

        HashSet<Integer> seen =
                new HashSet<>();

        for (int num : nums) {

            int complement = target - num;

            if (seen.contains(complement)) {
                return true;
            }

            seen.add(num);
        }

        return false;
    }
}