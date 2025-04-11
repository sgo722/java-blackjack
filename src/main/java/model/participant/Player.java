package model.participant;

public class Player extends Participant {
    // 카드의 합이 21이하라면 카드를 뽑는다.
    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public static Player create(String name){
        return new Player(new Name(name));
    }

    public String getName() {
        return name.getName();
    }
}
