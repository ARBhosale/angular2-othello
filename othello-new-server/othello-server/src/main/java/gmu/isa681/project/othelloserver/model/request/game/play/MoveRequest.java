package gmu.isa681.project.othelloserver.model.request.game.play;

public class MoveRequest {
	private Long gameId;
	private Long playerId;
	private int insertAtRow;
	private int insertAtColumn;

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public int getInsertAtRow() {
		return insertAtRow;
	}

	public void setInsertAtRow(int insertAtRow) {
		this.insertAtRow = insertAtRow;
	}

	public int getInsertAtColumn() {
		return insertAtColumn;
	}

	public void setInsertAtColumn(int insertAtColumn) {
		this.insertAtColumn = insertAtColumn;
	}

}
