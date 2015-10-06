package cn.bzhang.dota2.abaddon.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Team {
    RADIANT(2),
    DIRE(3);

    private final Integer id;

    Team(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    private static final Map<Integer, Team> lookup = new HashMap<>();

    static {
        for (Team t : EnumSet.allOf(Team.class))
            lookup.put(t.getId(), t);
    }

    public static Team get(int id) {
        return lookup.get(id);
    }
}
