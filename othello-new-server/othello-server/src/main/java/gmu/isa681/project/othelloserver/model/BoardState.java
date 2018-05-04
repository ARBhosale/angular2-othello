package gmu.isa681.project.othelloserver.model;

import java.util.ArrayList;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.game.BoardEntity;

public class BoardState {

	private static final String POSTION_NOT_EMPTY = "No place there!";
	private static final String NO_DISCS_FLIPPED = "None of opponents's disc is flipped";
	private static final String NO_MOVES_POSSIBLE = "There are no moves possible for ";

	private GameEntity game;
	private Board board;

	public BoardState(GameEntity game) {
		super();
		this.game = game;
	}

	public Board setBoardPiece(int rowNum, int colNum) {
		// if the board position is empty
		if (board.getValues().get(rowNum).get(colNum).getDiscType() != null) {
			board.setMoveResultMessage(BoardState.POSTION_NOT_EMPTY);
			return null;
		}

		// if the move can outflank an opponent disc
		ArrayList<Disc> outFlankedDiscs = board.getOutflankedDiscs(rowNum, colNum);
		if (outFlankedDiscs.isEmpty()) {
			board.setMoveResultMessage(BoardState.NO_DISCS_FLIPPED);
			return null;
		}
		// setting board piece
		Board newBoard = board.getCopy();
		newBoard.getValues().get(rowNum).set(colNum, new Disc(this.game.getCurrentTurn(), rowNum, colNum));
		newBoard.flipDiscs(newBoard.getSameDiscs(outFlankedDiscs), this.game.getCurrentTurn());
		this.game.updateScores(newBoard);
		this.game.updatePlayerTurns();

		this.performNoMoveActions(newBoard);

		return newBoard;
	}

	private void performNoMoveActions(Board board) {
		if (!board.isAnyMovePossible()) {
			this.game.setCurrentPlayerHasMoves(false);
		}
	}

	public Board initialize(BoardEntity boardToInitializeWith) {
		this.board = new Board(this.game, null);
		if (null == boardToInitializeWith) {
			board.initializeStartingPositions();
		} else {
			board.loadValuesFromString(boardToInitializeWith.getValues(), board.getValues());
		}
		return board;
	}

	public void goToPreviousState() {
		// this.game.undoMove()
		// .then((updatedGame) => {
		// let board = this.getPreviousState();
		// this.game.updateScores(board);
		// this.game.updatePlayerTurns();
		// this.game = updatedGame;
		// })
		// .catch((error) => {
		// board.actionResultMessage = error;
		// });
	}

	public GameEntity getGame() {
		return game;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setGame(GameEntity game) {
		this.game = game;
	}

}
