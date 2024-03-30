package kg.demo.dodo.util;

public enum Language {

    RU,
    EN;

    public static Language getLanguage(int ordinal) {
        switch(ordinal) {
            case 1 : return RU;

            case 2 : return EN;

            default: return RU;
        }
    }
}