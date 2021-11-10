import java.util.Random;
import java.util.stream.IntStream;

class DnDCharacter {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int hitpoints;
    private Random random = new Random();

    public DnDCharacter() {
        strength = ability();
        dexterity = ability();
        constitution = ability();
        intelligence = ability();
        wisdom = ability();
        charisma = ability();
        hitpoints = 10;
    }

    int ability() {
        return IntStream.generate(() -> random.nextInt(6) + 1).limit(3).sum();
    }

    int modifier(int input) {
        int value = (input - 10);
        return value < 0 ? (value - 1) / 2 : value / 2;
    }

    int getStrength() {
        return strength;
    }

    int getDexterity() {
        return dexterity;
    }

    int getConstitution() {
        return constitution;
    }

    int getIntelligence() {
        return intelligence;
    }

    int getWisdom() {
        return wisdom;
    }

    int getCharisma() {
        return charisma;
    }

    int getHitpoints() {
        return hitpoints + modifier(constitution);
    }

}
