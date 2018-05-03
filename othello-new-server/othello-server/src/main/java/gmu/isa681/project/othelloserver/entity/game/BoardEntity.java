package gmu.isa681.project.othelloserver.entity.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Board")
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String values;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

}
