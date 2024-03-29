package com.exercise.DiningPhilosophersProblem;

import java.util.Random;

public class Philosopher implements Runnable {

    private final int id;
    private volatile boolean isFull;
    private final Chopstick leftChopstick;
    private final Chopstick rightChopstick;
    private final Random random;
    private int eatingCounter = 0;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    /**
     *
     */
    @Override
    public void run() {
        try {
            while (!isFull) {
                think();
                if (leftChopstick.pickUp(this, State.LEFT)) {
                    if (rightChopstick.pickUp(this, State.RIGHT)) {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Think for a random time [0, 1000]
     */
    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    /**
     * Eat for a random time [0, 1000]
     */
    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public boolean isFull() {
        return isFull;
    }

    public int getEatingCounter() {
        return eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                ", isFull=" + isFull +
                ", eatingCounter=" + eatingCounter +
                '}';
    }

}
