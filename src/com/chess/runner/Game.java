package com.chess.runner;

import java.util.Scanner;

import com.chess.board.Board;
import com.chess.common.File;
import com.chess.common.Location;
import com.chess.squares.Square;

public class Game {
	public static void main(String[] args) {
		Board board = new Board();
		
		System.out.println("\nWelcome to Chess!");
		System.out.println("Here is an example move: E2->E4");
		
		board.printBoard();

		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				try {
					String line = scanner.nextLine();
					String[] fromTo = line.split("->");
					File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
					int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
					File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
					int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));

					Square fromSq = board.getLocationSquareMap().get(new Location(fromFile, fromRank));
					Square toSq = board.getLocationSquareMap().get(new Location(toFile, toRank));

					fromSq.getCurrentPiece().makeMove(board, toSq);

					board.printBoard();
				} catch (NumberFormatException e){}
			}
		}
	}
}
