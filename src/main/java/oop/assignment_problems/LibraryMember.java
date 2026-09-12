package oop.assignment_problems;
public class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;
    public LibraryMember() {
    }
    public String getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(String id) {
        if (membershipId == null) {
            membershipId = id;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isPremiumMember() {
        return premiumMember;
    }
    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }
    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            securityAnswer = Integer.toString(answer.hashCode());
        }
    }
    public static void main(String[] args) {
    LibraryMember m = new LibraryMember();
    m.setMembershipId("LIB-8841");
    m.setName("Priya Nair");
    m.setPremiumMember(true);
    System.out.println(m.getMembershipId());
    m.setMembershipId("FAKE-0000");
    System.out.println(m.getMembershipId());
    System.out.println(m.isPremiumMember());
    m.setSecurityAnswer("BlueMountain");
}
}