package edu.gmu.swe681.checkers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.gmu.swe681.checkers.controller.GamesController;
import edu.gmu.swe681.checkers.dao.BaseDAO;
import edu.gmu.swe681.checkers.dao.GameDAO;
import edu.gmu.swe681.checkers.model.Game;

@Service
public class GameService extends BaseService<Game> {
	
	private static final Logger LOG = LoggerFactory.getLogger(GamesController.class);
	
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
	
	public List<Game> getMyCompletedGames(String username) {
		return dao.getMyCompletedGames(username);
	}
	
	@Scheduled(fixedDelay=60000)
	public void forfeitAbandonedGames() {
		LOG.info("Checking to see if there are any abandoned games that need to be forfeited.");
		for(Game expiredGame : dao.getExpiredGames()) {
			expiredGame.expireGame();
			this.save(expiredGame);
			LOG.info("Just expired game: " + expiredGame.getId() + " with winner: " + expiredGame.getWinner().getUsername());
		}
	}

}
