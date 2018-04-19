package gmu.isa681.othello.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Player")
public class PlayerEntity {
	
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	public PlayerEntity(@NotNull String firstName, @NotNull String lastName, GameEntity gameEntity) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gameEntity = gameEntity;
	}
	public PlayerEntity() {
		super();
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long playerId;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private GameEntity gameEntity;
	
	public GameEntity getGame() {
		return gameEntity;
	}
	public void setGame(GameEntity game) {
		this.gameEntity = game;
	}
}
