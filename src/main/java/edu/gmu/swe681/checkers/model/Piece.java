package edu.gmu.swe681.checkers.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Piece {
	private static Logger LOG = LoggerFactory.getLogger(Piece.class);

	@Id
	@GeneratedValue//(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Column(nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Player player;
	
	@NotNull
	@Column(nullable = false, updatable = false)
	private Boolean kinged = false;

	@Valid
	@NotNull
	@Embedded
	private Coordinate coordinate;
	
	Piece() {
		//Needed for binding/JPA
	}
	
	public Piece(Player player, Coordinate coord) {
		setPlayer(player);
		setCoordinate(coord);
	}
	
	public Piece(Boolean kinged, Player player, Coordinate coordinate) {
		setKinged(kinged);
		setPlayer(player);
		this.coordinate = coordinate;
	}
		
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setKinged(Boolean kinged) {
		this.kinged = kinged;
	}

	public Boolean getKinged() {
		return kinged;
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "id");
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id");
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
