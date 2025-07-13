package com.example.lldTicTacToe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.lldTicTacToe.service.TicTacToeGame;

@SpringBootApplication
public class LldTicTacToeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LldTicTacToeApplication.class, args);
		
		  TicTacToeGame game = new TicTacToeGame();
	        game.initializeGame();
	        System.out.println("game winner is: " + game.startGame());

	}

}
