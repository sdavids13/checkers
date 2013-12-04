package edu.gmu.swe681.checkers.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.gmu.swe681.checkers.model.Game;

@Repository
public class GameDAO extends BaseDAO<Game> {

	public List<Game> getAvailableGames() {
		return this.entityManager
				.createQuery("from Game g where g.redPlayer = null or g.blackPlayer = null", Game.class)
				.setMaxResults(50).getResultList();
	}

	public List<Game> getMyActiveGames(String username) {
		return this.entityManager
				.createQuery("from Game g where g.winner = null and (g.redPlayer.username=:username or g.blackPlayer.username=:username)", Game.class)
				.setParameter("username", username).setMaxResults(50).getResultList();
	}

	public List<Game> getExpiredGames() {
		TypedQuery<Game> q = this.entityManager
				.createQuery("from Game g where g.winner = null and g.board.prevMoveDate < :expirationDate", Game.class);
		Calendar expireGamesBefore = Calendar.getInstance();
		expireGamesBefore.add(Calendar.MINUTE, -5);
		q.setParameter("expirationDate", expireGamesBefore.getTime());

		return q.getResultList();
	}

	public List<Game> getMyCompletedGames(String username) {
		return this.entityManager
			.createQuery("from Game g where g.winner is not null and (g.redPlayer.username=:username or g.blackPlayer.username=:username) order by g.board.prevMoveDate desc", Game.class)
			.setParameter("username", username).getResultList();
	}

}
