package gmu.isa681.othello.entity;

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
	private Long gameId;
	
	@NotNull
	private PlayerEntity playerBlack;
	
	@NotNull
	private PlayerEntity playerWhite;
	
	@NotNull
	private int scoreForBlack;
	
	@NotNull
	private int scoreForWhite;
	
	public GameEntity() {
		super();
	}
	
	public GameEntity(@NotNull PlayerEntity playerBlack, @NotNull PlayerEntity playerWhite, @NotNull int scoreForBlack,
			@NotNull int scoreForWhite) {
		super();
		this.playerBlack = playerBlack;
		this.playerWhite = playerWhite;
		this.scoreForBlack = scoreForBlack;
		this.scoreForWhite = scoreForWhite;
	}
	
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public PlayerEntity getPlayerBlack() {
		return playerBlack;
	}
	public void setPlayerBlack(PlayerEntity playerBlack) {
		this.playerBlack = playerBlack;
	}
	public PlayerEntity getPlayerWhite() {
		return playerWhite;
	}
	public void setPlayerWhite(PlayerEntity playerWhite) {
		this.playerWhite = playerWhite;
	}
	public int getScoreForBlack() {
		return scoreForBlack;
	}
	public void setScoreForBlack(int scoreForBlack) {
		this.scoreForBlack = scoreForBlack;
	}
	public int getScoreForWhite() {
		return scoreForWhite;
	}
	public void setScoreForWhite(int scoreForWhite) {
		this.scoreForWhite = scoreForWhite;
	}
}
