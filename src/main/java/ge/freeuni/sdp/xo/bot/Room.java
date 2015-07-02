package ge.freeuni.sdp.xo.bot;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/*
 * copied from xo-room repository
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
	
	@XmlElement
	private String id;
	
	@XmlElement(nillable=true)
	private String x_user;
	
	@XmlElement(nillable=true)
	private String o_user;
	
	
	public Room(){
		//Dummy
	}
	
	/**
	 * @param id
	 * @param x_user
	 * @param o_user
	 */
	public Room(String id, String x_user, String o_user) {
		this.id = id;
		this.x_user = x_user;
		this.o_user = o_user;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the x_user
	 */
	public String getx_user() {
		return x_user;
	}
	
	/**
	 * @param x_user the x_user to set
	 */
	public void setx_user(String x_user) {
		this.x_user = x_user;
	}
	
	/**
	 * @return the o_user
	 */
	public String geto_user() {
		return o_user;
	}
	
	/**
	 * @param o_user the o_user to set
	 */
	public void seto_user(String o_user) {
		this.o_user = o_user;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((o_user == null) ? 0 : o_user.hashCode());
		result = prime * result + ((x_user == null) ? 0 : x_user.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (o_user == null) {
			if (other.o_user != null)
				return false;
		} else if (!o_user.equals(other.o_user))
			return false;
		if (x_user == null) {
			if (other.x_user != null)
				return false;
		} else if (!x_user.equals(other.x_user))
			return false;
		return true;
	}

	
	
	
}