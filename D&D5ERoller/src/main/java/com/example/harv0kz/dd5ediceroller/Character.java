package com.example.harv0kz.dd5ediceroller;

/**
 * Created by Harv0kz on 8/10/2017.
 */

public class Character {
    int _id;
    String _name;
    int _maxProfBonus;
    int[] _statline;
    String[] _skillProfs;
    String[] _expertSkills;
    String[] _toolProfs;

    public Character(){

    }

    public Character(int id, String name, int maxProfBonus, int[] statline, String[] skillProfs, String[] expertSkills, String[] toolProfs){
        this._id = id;
        this._name = name;
        this._maxProfBonus = maxProfBonus;
        this._statline = statline;
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

    public void set_maxProfBonus(int maxProfBonus) {
        this._maxProfBonus = maxProfBonus;
    }

    public void set_statline(int[] statline){
        this._statline = statline;
    }

    public void set_skillProfs(String[] skillProfs) {
        this._skillProfs = skillProfs;
    }

    public void set_expertSkills(String[] expertSkills) {
        this._expertSkills = expertSkills;
    }

    public void set_toolProfs(String[] toolProfs) {
        this._toolProfs = toolProfs;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public int get_maxProfBonus() {
        return _maxProfBonus;
    }

    public int[] get_statline(){
        return _statline;
    }

    public String[] get_skillProfs() {
        return _skillProfs;
    }

    public String[] get_expertSkills() {
        return _expertSkills;
    }

    public String[] get_toolProfs() {
        return _toolProfs;
    }
}
