package com.example.pokerpoggers.services;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Integer stackSize;
    private List<Integer> cards = new ArrayList<>();

    // this can be improved
    int cardCounter = 0;

    public Player(Integer stackSize) {
        this.stackSize = stackSize;
    }

    public void printPlayer() {
        System.out.println(String.format("stack: %s \n card1: %d \n card2 : %d \n", stackSize, cards.get(0), cards.get(1)));
    }

    public void dealCard(Integer card) {
        // TODO no more than 2 cards can be held
        cards.add(card);
    }

    public void clearCards() {
        cards.clear();
    }
}
