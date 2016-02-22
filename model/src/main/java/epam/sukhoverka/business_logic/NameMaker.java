package epam.sukhoverka.business_logic;

public class NameMaker {
    public static String createShortName(String fullName){
        String shortName = fullName.charAt(0) + ".";
        return shortName;
    }
}
