package advent_of_code_2020.day_2;

public class PasswordPattern {

    private final String[] characterOccurrence;
    private final String character;
    private final String password;

    public PasswordPattern(String[] characterOccurrence, String character, String password) {
        this.characterOccurrence = characterOccurrence;
        this.character = character;
        this.password = password;
    }


    public int getFirstOccurrenceIndex(){
        return Integer.parseInt(this.characterOccurrence[0]);
    }

    public int getLastOccurrenceIndex(){
        return Integer.parseInt(this.characterOccurrence[1]);
    }

    public String getCharacter() {
        return character;
    }

    public String getPassword() {
        return password;
    }
}
