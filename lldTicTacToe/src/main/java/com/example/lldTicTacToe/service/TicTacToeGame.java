package com.example.lldTicTacToe.service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.example.lldTicTacToe.factory.PlayingPeiceFactory;
import com.example.lldTicTacToe.model.Board;
import com.example.lldTicTacToe.model.PeiceType;
import com.example.lldTicTacToe.model.Player;

public class TicTacToeGame {
	Deque<Player> players;
    Board gameBoard;
    UtilityActions util;

	public void initializeGame(){
		System.out.print("enter player names and synbol of each player ");
        Scanner inputScanner = new Scanner(System.in);
        String s = inputScanner.nextLine();
          String[] values = s.split(",");
          String name1= values[0];
          String symbol1= values[1];
          String name2= values[2];
          String symbol2= values[3];
          players = new LinkedList<>();
          gameBoard = new Board(3);
          util= new UtilityActions(gameBoard);
          
          PlayingPeiceFactory factory = new PlayingPeiceFactory();
          Player player1 = new Player(name1, factory.getPeice(symbol1));
          
          Player player2 = new Player(name2, factory.getPeice(symbol2));
          
          players.add(player1);
          players.add(player2);
          
          
	}
	public String startGame(){

        boolean noWinner = true;
        while(noWinner){

            //take out the player whose turn is and also put the player in the list back
            Player playerTurn = players.removeFirst();

            //get the free space from the board
           util.printBoard();
           List<Map.Entry<Integer, Integer>> freeSpaces =  util.getFreeCells();
            if(freeSpaces.isEmpty()) {
                noWinner = false;
                continue;
            }

            //read the user input
            System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);


            //place the piece
            boolean pieceAddedSuccessfully = util.addPiece(inputRow,inputColumn, playerTurn.peice);
            if(!pieceAddedSuccessfully) {
                //player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorredt possition chosen, try again");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean winner = isThereWinner(inputRow, inputColumn, playerTurn.peice.type);
            if(winner) {
            	util.printBoard();
                return playerTurn.name;
            }
        }

        return "tie";
    }

    public boolean isThereWinner(int row, int column, PeiceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[row][i] == null || gameBoard.board[row][i].type != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<gameBoard.size;i++) {

            if(gameBoard.board[i][column] == null || gameBoard.board[i][column].type != pieceType) {
                columnMatch = false;
            }
        }

        //need to check diagonals
        for(int i=0, j=0; i<gameBoard.size;i++,j++) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].type != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=gameBoard.size-1; i<gameBoard.size;i++,j--) {
            if (gameBoard.board[i][j] == null || gameBoard.board[i][j].type != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}

