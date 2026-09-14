package oop.class_problems;
public class BankTransactionReference {
    static String normalizeReference(String raw) {
        String reference = raw.trim();
        String bankCode = reference.substring(0, 3).toUpperCase();
        String remaining = reference.substring(3);
        return bankCode + remaining;
    }
    static String validateAndFormat(String reference) {
        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < reference.length(); i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }
        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9);
        StringBuilder result = new StringBuilder();
        result.append("[");
        result.append(bankCode);
        result.append("] DATE: ");
        result.append(date.substring(0, 2));
        result.append("/");
        result.append(date.substring(2, 4));
        result.append("/");
        result.append(date.substring(4, 6));
        result.append(" | SEQ: ");
        result.append(sequence);
        return result.toString();
    }
    public static void main(String[] args) {
        String raw = " hdf03022600042 ";
        String reference = normalizeReference(raw);
        System.out.println(validateAndFormat(reference));
        String invalid = normalizeReference("12F03022600042");
        System.out.println(validateAndFormat(invalid));
    }
}