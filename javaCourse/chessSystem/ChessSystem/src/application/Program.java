package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Origem: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("Alvo: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() != null) {
					System.out.print("Entre com a peça a ser promovida (B/C/Q/T): ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("Q") && !type.equals("T") && !type.equals("C") && !type.equals("B")) {
						System.out.print("Valor inválido! Entre com a peça a ser promovida (B/C/Q/T): ");
						type = sc.nextLine().toUpperCase();
					}
					chessMatch.replacePromotedPiece(type);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage() + "\nPressione Enter para continuar");
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage() + "\nPressione Enter para continuar");
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
		sc.close();
	}

}
