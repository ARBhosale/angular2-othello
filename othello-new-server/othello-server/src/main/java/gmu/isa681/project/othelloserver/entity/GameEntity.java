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

	private Integer timeLimitInMinutes;

	public GameEntity(@NotNull Long playerWhoCreatedGameId) {
		super();
		this.gameCreatorPlayerId = playerWhoCreatedGameId;
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

	public Long getPlayerWhoCreatedGame() {
		return gameCreatorPlayerId;
	}

	public void setPlayerWhoCreatedGame(Long playerWhoCreatedGame) {
		this.gameCreatorPlayerId = playerWhoCreatedGame;
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
