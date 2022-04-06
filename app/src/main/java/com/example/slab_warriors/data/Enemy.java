package com.example.slab_warriors.data;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemy {
    private Integer id;
    private String name, type, details;
    private int level, attack, hp;
    private static List<Enemy> enemyList;

    public Enemy(Integer id, String name, String type, String details, int level, int attack, int hp) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.details = details;
        this.level = level;
        this.attack = attack;
        this.hp = hp;
    }
    public Enemy(String name, String type, String details, int level, int attack, int hp) {
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
    public static List<Enemy> getEnemies(String in){
        Gson converter = new Gson();
        Enemy[] enemies = converter.fromJson(in,Enemy[].class);
        enemyList = Arrays.asList(enemies);
        return enemyList;
    }
    public static Enemy getRandomEnemy(int level){
        if (level < 0 || enemyList == null) return new Enemy("No Name", "No class", "No details", 0,0,0);
        Random r = new Random();
        Enemy random = enemyList.get(r.nextInt(enemyList.size()));
        random.setLevel(level);
        random.setHp(random.getHp() + random.getHp() * level / 10);
        random.setAttack(random.getAttack() + random.getAttack() * level / 10);
        return random;
    }
}