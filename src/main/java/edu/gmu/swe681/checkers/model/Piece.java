package edu.gmu.swe681.checkers.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.*;
import org.slf4j.*;

@Entity
public class Piece {
	private static Logger LOG = LoggerFactory.getLogger(Piece.class);

	@Id
	@GeneratedValue//(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Player player;
	
	@NotNull
	@Column(nullable = false)
	private Boolean kinged = false;

	@NotNull
	@Embedded
	private Coordinate coordinate;
	
	Piece() {
		//Needed for binding/JPA
	}
	
	public Piece(Player player, Coordinate coord) {
		this.player = player;
		this.coordinate = coord;
	}
	
	public Piece(String player, Coordinate coord) {
		setPlayer(player);
		setCoordinate(coord);
	}
	
	public Piece(String kinged, String player, Coordinate coordinate) {
		setKinged(kinged);
		setPlayer(player);
		this.coordinate = coordinate;
	}
	
	public void setPlayer(String player) {
		//TODO: add field to Enum to base String arg match against
		try {
		Integer playerInt = Integer.parseInt(player);
			this.player =  playerInt == 2 ? Player.RED : Player.BLACK; 
		} catch (NumberFormatException e) {
			LOG.warn("trouble", e);
		}
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
	
	public void setKinged(String kinged) {
		if (StringUtils.isEmpty(kinged)) {
			LOG.warn("kinged arg is empty");
			return;
		}
		this.kinged = Boolean.parseBoolean(kinged);
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
