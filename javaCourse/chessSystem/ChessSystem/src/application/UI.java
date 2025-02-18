package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String WHITE = "\u001B[37m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro, valores válidos de a1 a h8");
		}
	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turno: " + chessMatch.getTurn());
		if (!chessMatch.getCheckMate()) {
			System.out.println("Aguardando jogador " + traduzir(chessMatch.getCurrentPlayer()));
			if (chessMatch.getCheck()) {
				System.out.println("XEQUE!");
			}
		} else {
			System.out.println("XEQUEMATE!");
			System.out.println("Vencedor: " + chessMatch.getCurrentPlayer());
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces[i].length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(YELLOW_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(WHITE + piece + RESET);
			} else {
				System.out.print(RED + piece + RESET);
			}
		}
		System.out.print(RESET + " ");
	}

	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		System.out.println("Peças Capturadas:");
		System.out.print("Branco: ");
		System.out.print(WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(RESET);
		System.out.print("Preto: ");
		System.out.print(RED);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(RESET);

	}
	
	private static String traduzir(Color color) {
		return color == Color.WHITE ? "branco" : "preto";
	}
}
