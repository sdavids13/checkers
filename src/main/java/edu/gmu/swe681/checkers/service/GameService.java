package edu.gmu.swe681.checkers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gmu.swe681.checkers.dao.BaseDAO;
import edu.gmu.swe681.checkers.dao.GameDAO;
import edu.gmu.swe681.checkers.model.Game;

@Service
public class GameService extends BaseService<Game> {
	
	@Autowired
	private GameDAO dao;

	@Override
	protected BaseDAO<Game> getDao() {
		return dao;
	}

	@Override
	protected Class<Game> getModelClass() {
		return Game.class;
	}

	public List<Game> getAvailableGames() {
		return dao.getAvailableGames();
	}

	public List<Game> getMyActiveGames(String username) {
		return dao.getMyActiveGames(username);
	}

}
