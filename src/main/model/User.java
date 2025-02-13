package main.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private int age;
    private double weight;
    private double height;

    public User(int id, String name, int age, double weight, double height) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    // Getters and Setters (Encapsulation)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    // Overriding equals(), hashCode(), and toString()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User user = (User) obj;
        return id == user.id && age == user.age &&
                Double.compare(user.weight, weight) == 0 &&
                Double.compare(user.height, height) == 0 &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, weight, height);
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', age=" + age +
                ", weight=" + weight + ", height=" + height + "}";
    }
}
