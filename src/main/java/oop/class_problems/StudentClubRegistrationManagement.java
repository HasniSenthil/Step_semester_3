package oop.class_problems;

import java.util.*;

public class StudentClubRegistrationManagement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<ClubStudent> members = new HashSet<>();

        while (sc.hasNext()) {
            String operation = sc.next();
            String rollNumber = sc.next();
            String name = sc.next();

            ClubStudent student = new ClubStudent(rollNumber, name);

            if (operation.equals("ADD")) {
                if (members.add(student)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (operation.equals("CONTAINS")) {
                System.out.println(
                        "contains: " + members.contains(student)
                );
            }
        }

        System.out.println("member count " + members.size());
        sc.close();
    }
}

class ClubStudent {
    private String rollNumber;
    private String name;

    public ClubStudent(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ClubStudent)) {
            return false;
        }

        ClubStudent other = (ClubStudent) obj;
        return rollNumber.equals(other.rollNumber);
    }

    @Override
    public int hashCode() {
        return rollNumber.hashCode();
    }
}