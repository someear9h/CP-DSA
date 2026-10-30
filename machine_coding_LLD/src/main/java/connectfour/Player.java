package connectfour;

enum DiscColor {
    RED, BLUE
}

public record Player(String name, DiscColor color) {}