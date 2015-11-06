package stock;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Stock {
	private String id;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;
	@NotNull
	private float open;
	//private float low;
	//private float high;
	@NotNull
	private float volume;
	@NotNull
	private String type;
	@NotNull
	private String age;
	@NotNull
	private String competition;
	
	Stock() {}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public float getOpen() {
		return open;
	}
	
	public void setOpen(float open) {
		this.open = open;
	}
	
	
	public float getVolume() {
		return volume;
	}
	
	public void setVolume() {
		this.volume = volume;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType() {
		this.type = type;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge() {
		this.age = age;
	}
	
	public String getCompetition() {
		return competition;
	}
	
	public void setCompetition() {
		this.competition = competition;
	}
	
	@Override
	public String toString() {
		return "Stock [ id: "+id+"date:"+date+"open:"+open+"volume:"+volume+"age:"+age+"competition:"+competition+"]";
		
	}

}
