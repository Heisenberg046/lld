package com.example.lldTicTacToe.factory;
import com.example.lldTicTacToe.model.PlayingPeice;
import com.example.lldTicTacToe.model.PlayingPeiceO;
import com.example.lldTicTacToe.model.PlayingPeiceX;

public class PlayingPeiceFactory {
	
	

	public PlayingPeice getPeice(String peice) {
		  if (peice.equalsIgnoreCase("X")) {
	            return new PlayingPeiceX();
	        } else if (peice.equalsIgnoreCase("O")) {
	            return new PlayingPeiceO();
	        }   else {
            throw new IllegalArgumentException("Invalid symbol: " + peice);
    }
		
	}

}
