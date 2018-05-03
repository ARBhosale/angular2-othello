package gmu.isa681.project.othelloserver.convertor.game;

import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.entity.game.BoardEntity;
import gmu.isa681.project.othelloserver.model.Board;

public class BoardToBoardEntityConverter implements Converter<Board, BoardEntity> {

	@Override
	public BoardEntity convert(Board board) {

		String values = board.getValuesAsString(board.getValues());

		BoardEntity boardEntity = new BoardEntity();
		boardEntity.setValues(values);

		return boardEntity;
	}

}
