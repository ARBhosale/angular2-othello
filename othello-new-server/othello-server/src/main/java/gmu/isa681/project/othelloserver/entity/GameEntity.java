package gmu.isa681.project.othelloserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

	public GameEntity(@NotNull Long playerWhoCreatedGameId) {
		super();
		this.gameCreatorPlayerId = playerWhoCreatedGameId;
	}

	public Long getId() {
		return id;
	}

	public Long getPlayerWhoCreatedGame() {
		return gameCreatorPlayerId;
	}

	public void setPlayerWhoCreatedGame(Long playerWhoCreatedGame) {
		this.gameCreatorPlayerId = playerWhoCreatedGame;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlayerBlack() {
		return playerBlackId;
	}

	public void setPlayerBlack(Long playerBlack) {
		this.playerBlackId = playerBlack;
	}

	public Long getPlayerWhite() {
		return playerWhiteId;
	}

	public void setPlayerWhite(Long playerWhite) {
		this.playerWhiteId = playerWhite;
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

	public GameEntity() {
		super();
	}

}
