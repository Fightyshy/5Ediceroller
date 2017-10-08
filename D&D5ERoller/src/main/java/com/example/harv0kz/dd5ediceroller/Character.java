package com.example.harv0kz.dd5ediceroller;

/**
 * Created by Harv0kz on 8/10/2017.
 */

public class Character {
    int _id;
    String _name;
    int _level;
    int _strength;
    int _dexterity;
    int _constitution;
    int _intelligence;
    int _wisdom;
    int _charisma;
    String _skillProfs;
    String _expertSkills;
    String _toolProfs;
    String ignore;

    public Character(){

    }

    public Character(int id, String name, int level, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, String skillProfs, String expertSkills, String toolProfs){
        this._id = id;
        this._name = name;
        this._level = level;
        this._strength = strength;
        this._dexterity = dexterity;
        this._constitution = constitution;
        this._intelligence = intelligence;
        this._wisdom = wisdom;
        this._charisma = charisma;
        this._skillProfs = skillProfs;
        this._expertSkills = expertSkills;
        this._toolProfs = toolProfs;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public void set_name(String name) {
        this._name = name;
    }

    public void set_level(int level) {
        this._level = level;
    }

    public void set_strength(int strength) {
        this._strength = strength;
    }

    public void set_dexterity(int dexterity) {
        this._dexterity = dexterity;
    }

    public void set_constitution(int constitution) {
        this._constitution = constitution;
    }

    public void set_intelligence(int intelligence) {
        this._intelligence = intelligence;
    }

    public void set_wisdom(int wisdom) {
        this._wisdom = wisdom;
    }

    public void set_charisma(int charisma) {
        this._charisma = charisma;
    }

    public void set_skillProfs(String skillProfs) {
        this._skillProfs = skillProfs;
    }

    public void set_expertSkills(String expertSkills) {
        this._expertSkills = expertSkills;
    }

    public void set_toolProfs(String toolProfs) {
        this._toolProfs = toolProfs;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public int get_level() {
        return _level;
    }

    public int get_strength() {
        return _strength;
    }

    public int get_dexterity() {
        return _dexterity;
    }

    public int get_constitution() {
        return _constitution;
    }

    public int get_intelligence() {
        return _intelligence;
    }

    public int get_wisdom() {
        return _wisdom;
    }

    public int get_charisma() {
        return _charisma;
    }

    public String get_skillProfs() {
        return _skillProfs;
    }

    public String get_expertSkills() {
        return _expertSkills;
    }

    public String get_toolProfs() {
        return _toolProfs;
    }
}
