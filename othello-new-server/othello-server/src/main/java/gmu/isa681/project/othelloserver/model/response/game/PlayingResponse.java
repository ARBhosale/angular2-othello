package gmu.isa681.project.othelloserver.model.response.game;

import gmu.isa681.project.othelloserver.model.DiscType;
import gmu.isa681.project.othelloserver.model.Links;

public class PlayingResponse {

	private Long gameId;
	private Long playerBlackId;

	private Long playerWhiteId;

	private Integer playerBlackScore;

	private Integer playerWhiteScore;

	private Integer timeLimitInMinutes;

	private boolean currentPlayerHasMoves = true;

	private Long boardId;
	
	private DiscType currentTurn;

	private Links links;

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public DiscType getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(DiscType currentTurn) {
		this.currentTurn = currentTurn;
	}

	public boolean isCurrentPlayerHasMoves() {
		return currentPlayerHasMoves;
	}

	public void setCurrentPlayerHasMoves(boolean currentPlayerHasMoves) {
		this.currentPlayerHasMoves = currentPlayerHasMoves;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
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

	public Integer getTimeLimitInMinutes() {
		return timeLimitInMinutes;
	}

	public void setTimeLimitInMinutes(Integer timeLimitInMinutes) {
		this.timeLimitInMinutes = timeLimitInMinutes;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	public PlayingResponse(Long gameId) {
		super();
		this.gameId = gameId;
	}

	public PlayingResponse() {
		super();
	}

}
