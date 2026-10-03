package oop.assignment_problems;

import java.util.ArrayList;

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        NoticeStudent asha = new NoticeStudent("Asha", "CSE");
        NoticeStudent ravi = new NoticeStudent("Ravi", "ECE");

        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        ravi.addChannel(new SmsChannel());

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        Notice notice1 =
                new Notice("Lab Closed Tomorrow");

        notice1.addDepartment("CSE");

        board.postNotice(notice1);

        Notice notice2 =
                new Notice("Fee Deadline Extended");

        notice2.addDepartment("CSE");
        notice2.addDepartment("ECE");

        board.postNotice(notice2);

        Notice notice3 =
                new Notice("Sports Day");

        board.postNotice(notice3);
    }
}

class NoticeStudent {

    private String name;
    private String department;
    private ArrayList<NotificationChannel> channels;

    public NoticeStudent(
            String name,
            String department) {

        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>();
    }

    public void addChannel(
            NotificationChannel channel) {

        channels.add(channel);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public ArrayList<NotificationChannel> getChannels() {
        return channels;
    }
}

class Notice {

    private String title;
    private ArrayList<String> departments;

    public Notice(String title) {

        this.title = title;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(String department) {

        if (department != null
                && !department.trim().isEmpty()
                && !departments.contains(department)) {

            departments.add(department);
        }
    }

    public boolean isValid() {

        return title != null
                && !title.trim().isEmpty()
                && !departments.isEmpty();
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getDepartments() {
        return departments;
    }
}

interface NotificationChannel {

    void send(
            NoticeStudent student,
            Notice notice
    );
}

class EmailChannel
        implements NotificationChannel {

    @Override
    public void send(
            NoticeStudent student,
            Notice notice) {

        System.out.println(
                "[Email ->"
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

class SmsChannel
        implements NotificationChannel {

    @Override
    public void send(
            NoticeStudent student,
            Notice notice) {

        System.out.println(
                "[SMS -> "
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

class AppChannel
        implements NotificationChannel {

    @Override
    public void send(
            NoticeStudent student,
            Notice notice) {

        System.out.println(
                "[App ->"
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

class WhatsAppChannel
        implements NotificationChannel {

    @Override
    public void send(
            NoticeStudent student,
            Notice notice) {

        System.out.println(
                "[WhatsApp -> "
                        + student.getName()
                        + "] "
                        + notice.getTitle()
        );
    }
}

class NoticeBoard {

    private ArrayList<NoticeStudent> students;

    public NoticeBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(NoticeStudent student) {
        students.add(student);
    }

    public void postNotice(Notice notice) {

        if (!notice.isValid()) {

            System.out.println(
                    "Cannot post notice: "
                            + "At least one target department is required."
            );

            return;
        }

        System.out.println(
                "Notice '"
                        + notice.getTitle()
                        + "' posted to "
                        + String.join(
                        ", ",
                        notice.getDepartments()
                )
                        + "."
        );

        for (NoticeStudent student : students) {

            if (notice.getDepartments()
                    .contains(student.getDepartment())) {

                for (NotificationChannel channel
                        : student.getChannels()) {

                    channel.send(
                            student,
                            notice
                    );
                }
            }
        }
    }
}