package joaovitorlopes.com.github.books_api.service;

public class NameConversion {
    public static String formatName(String name) {
        String[] parts = name.split(", ");
        if(parts.length > 1) {
            return parts[1] + " " + parts[0];
        } else {
            return name;
        }
    }
}
