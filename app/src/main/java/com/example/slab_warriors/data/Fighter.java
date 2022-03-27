package com.example.slab_warriors.data;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

public class Fighter {
    private Integer id;
    private String name, type, details;
    private int level, attack, hp;

    public Fighter(Integer id, String name, String type, String details, int level, int attack, int hp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.details = details;
        this.level = level;
        this.attack = attack;
        this.hp = hp;
    }
    public Fighter(String name, String type, String details, int level, int attack, int hp) {
        this.name = name;
        this.type = type;
        this.details = details;
        this.level = level;
        this.attack = attack;
        this.hp = hp;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public static List<Fighter> getFighters(String in){
        Gson converter = new Gson();
        Fighter[] fighters = converter.fromJson(in,Fighter[].class);
        return Arrays.asList(fighters);
    }
}