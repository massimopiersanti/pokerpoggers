package com.example.pokerpoggers.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


@Service
public class GameService {
    private List<Integer> deck = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Integer> boardCards = new ArrayList<>();
    private int deckIndex = 0;
    private int numPlayers = 0;


    public void initDeck() {
        deck.clear();
        boardCards.clear();
        clearPlayerHands();
        for (int i = 0; i < 52; i++) {
            deck.add(i);
        }
        deckIndex = 0;
    }

    private void clearPlayerHands() {
        players.forEach(Player::clearCards);
    }

    public void shuffleDeck() {

        Random rand = new Random();
        for (int i = 51; i > 0; i--) {
            int j = rand.nextInt(i);
            //exchange values of index i,j. This is a modified version of fisher-yates shuffle from wikipedia
            // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
            int temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }

    }

    public void printDeck() {
        System.out.println(deck.toString());
    }


    //rework entire player creation process
    public void initPlayers(Integer numPlayers, List<Integer> stacks) {
        this.numPlayers = numPlayers;
        IntStream.range(0, numPlayers).forEach(n -> {
            players.add(new Player(stacks.get(n)));
        });
    }


    public void printPlayers() {
        for (Player player : players) {
            player.printPlayer();
        }
    }

    public void dealHoleCards() {

        List<Integer> cards = deck.stream().limit((numPlayers * 2)).toList();
        deckIndex = numPlayers * 2;

        for (int i = 0; i < cards.size(); i++) {
            //System.out.println("for loop entered ");
            int playerToGive = i % numPlayers;
            //System.out.println(String.format("assigning player %s card %d \n", playerToGive, cards.get(i)));
            players.get(playerToGive).dealCard(cards.get(i));
        }

    }

    public void setBoardCards() {
        List<Integer> cards = deck.subList(deckIndex, deckIndex + 5);
        boardCards.addAll(cards);
        deckIndex += 5;
    }


    public void playHand() {
        initDeck();
        shuffleDeck();
        dealHoleCards();
        setBoardCards();
        calculateHandValues();
        // create an object to return to front end
    }

    private void calculateHandValues() {
    }

    public void printGameState() {
        System.out.printf("Deck: %s \n", deck.toString());
        System.out.println("Players: ");
        printPlayers();
        System.out.printf("BoardCards: %s \n", boardCards.toString());


    }
}
