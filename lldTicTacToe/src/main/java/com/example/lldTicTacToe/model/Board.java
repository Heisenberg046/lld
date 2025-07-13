package com.example.lldTicTacToe.model;

public class Board {

	public int size;
	public PlayingPeice[][] board;

	public Board(int size) {
		super();
		this.size = size;
		this.board = new PlayingPeice[size][size];
	}

}
