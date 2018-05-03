package gmu.isa681.project.othelloserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import gmu.isa681.project.othelloserver.exception.InvalidOperationException;
import gmu.isa681.project.othelloserver.model.Board;
import gmu.isa681.project.othelloserver.model.BoardState;
import gmu.isa681.project.othelloserver.model.Disc;
import gmu.isa681.project.othelloserver.model.DiscType;

@Entity
@Table(name = "Game")
public class GameEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Long gameCreatorPlayerId;

	private Long playerBlackId;

	private Long playerWhiteId;

	private Integer playerBlackScore;

	private Integer playerWhiteScore;

	private Integer timeLimitInMinutes;

	private DiscType currentTurn;

	private boolean currentPlayerHasMoves = true;

	private Long currentBoardId;

	public GameEntity(@NotNull Long playerWhoCreatedGameId) {
		super();
		this.gameCreatorPlayerId = playerWhoCreatedGameId;
	}

	public void playMove(int row, int col, BoardState boardState) {
		boardState.setBoardPiece(row, col);
		String moveResultMessage = boardState.getBoard().getMoveResultMessage();
		if (!moveResultMessage.equals("")) {
			throw new InvalidOperationException(moveResultMessage);
		}
	}

	public void updatePlayerTurns() {
		if (this.currentTurn == DiscType.White) {
			this.currentTurn = DiscType.Black;
		} else {
			this.currentTurn = DiscType.White;
		}
	}

	public void updateScores(Board boardBeingPlayed) {
		this.playerBlackScore = 0;
		this.playerWhiteScore = 0;
		for (int i = 0; i < Board.NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < Board.NUMBER_OF_COLUMNS; j++) {
				Disc disc = boardBeingPlayed.getValues().get(i).get(j);
				if (null == disc) {
					continue;
				} else {
					if (DiscType.White == disc.getDiscType()) {
						this.playerWhiteScore++;
					} else {
						this.playerBlackScore++;
					}
				}
			}
		}
	}

	public String getMoveResultMessage(BoardState boardState) {
		return boardState.getBoard().getMoveResultMessage();
	}

	// public void initializeGameBoardState() {
	// this.boardState = new BoardState(this);
	// this.boardState.initialize();
	// }

	public DiscType getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(DiscType currentTurn) {
		this.currentTurn = currentTurn;
	}

	public Long getGameCreatorPlayerId() {
		return gameCreatorPlayerId;
	}

	public void setGameCreatorPlayerId(Long gameCreatorPlayerId) {
		this.gameCreatorPlayerId = gameCreatorPlayerId;
	}

	public Long getPlayerBlackId() {
		return playerBlackId;
	}

	public void setPlayerBlackId(Long playerBlackId) {
		this.playerBlackId = playerBlackId;
	}

	public Long getPlayerWhiteId() {
		return playerWhiteId;
	}

	public void setPlayerWhiteId(Long playerWhiteId) {
		this.playerWhiteId = playerWhiteId;
	}

	public Integer getTimeLimitInMinutes() {
		return timeLimitInMinutes;
	}

	public void setTimeLimitInMinutes(Integer timeLimitInMinutes) {
		this.timeLimitInMinutes = timeLimitInMinutes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPlayerBlackScore() {
		return playerBlackScore;
	}

	public void setPlayerBlackScore(Integer playerBlackScore) {
		this.playerBlackScore = playerBlackScore;
	}

	public Integer getPlayerWhiteScore() {
		return playerWhiteScore;
	}

	public void setPlayerWhiteScore(Integer playerWhiteScore) {
		this.playerWhiteScore = playerWhiteScore;
	}

	public boolean getCurrentPlayerHasMoves() {
		return currentPlayerHasMoves;
	}

	public void setCurrentPlayerHasMoves(boolean currentPlayerHasMoves) {
		this.currentPlayerHasMoves = currentPlayerHasMoves;
	}

	public Long getCurrentBoardId() {
		return currentBoardId;
	}

	public void setCurrentBoardId(Long currentBoardId) {
		this.currentBoardId = currentBoardId;
	}

	public GameEntity() {
		super();
	}

	public void addOtherPlayer(PlayerEntity otherPlayer) {
		if (id.equals(null) || gameCreatorPlayerId.equals(null)) {
			return;
		}

		if (null == playerWhiteId) {
			playerWhiteId = otherPlayer.getId();
		} else {
			playerBlackId = otherPlayer.getId();
		}
	}

}
