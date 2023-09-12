package com.example.pokerpoggers.controllers;


import com.example.pokerpoggers.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DeckController {


    @Autowired
    GameService gameService;


    @RequestMapping(value = "initPlayers")
    public void initPlayers() {
        // have front end pass object for
        System.out.println("initPlayers");
        List<Integer> stacks = new ArrayList<>();
        stacks.add(100);
        stacks.add(200);
        stacks.add(100);
        gameService.initPlayers(3,stacks);
    }

    @RequestMapping(value = "printPlayers")
    public void printPlayers() {
        System.out.println("printPlayers");
        gameService.printPlayers();
    }

    @RequestMapping(value = "/printDeck")
    public void printDeck() {
        System.out.println("printDeck");
        gameService.printDeck();

    }


    //every hand send an object to update data with results of previous hand and receive current hand info
    @RequestMapping(value = "/playHand")
    public PlayHandResponse playHand() {
        gameService.playHand();
        gameService.printGameState();

        return null;

    }

}
