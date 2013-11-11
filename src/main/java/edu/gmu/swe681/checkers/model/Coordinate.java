package edu.gmu.swe681.checkers.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Embeddable
public class Coordinate {

	@Min(0)
	@Max(7)
	@NotNull
	@Column(nullable = false)
	private Integer x;
	
	@Min(0)
	@Max(7)
	@NotNull
	@Column(nullable = false)
	private Integer y;
	
	Coordinate() {
		//Needed for binding/JPA
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@AssertTrue(message = "a piece is in an unplayable spot")
	public boolean isPlayable() {
		return isPlayable(this);
	}
	
	protected static boolean isPlayable(Coordinate coord) {
		return (coord.x + coord.y) %2 == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
