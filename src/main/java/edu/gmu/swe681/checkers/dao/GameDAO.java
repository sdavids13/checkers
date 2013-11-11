package edu.gmu.swe681.checkers.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.gmu.swe681.checkers.model.Game;

@Repository
public class GameDAO extends BaseDAO<Game> {

	@SuppressWarnings("unchecked")
	public List<Game> getAvailableGames() {
		return (List<Game>) this.entityManager
				.createQuery("from Game g where g.redPlayer = null or g.blackPlayer = null")
				.setMaxResults(50).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Game> getMyActiveGames(String username) {
		return (List<Game>) this.entityManager
				.createQuery("from Game g where g.winner = null and (g.redPlayer.username=:username or g.blackPlayer.username=:username)")
				.setParameter("username", username).setMaxResults(50).getResultList();
	}

}
