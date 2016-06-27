package user.model;

public class Machineinfo {

	private int id;
	private String time;
	private String temperature;
	private String humidity;
	private String smog;
	private String power;
	private String waterlog;
	
	
	public Machineinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Machineinfo(int id, String time, String temperature,String humidity, 
			String smog, String power,String waterlog) {
		super();
		this.id = id;
		this.time = time;
		this.temperature = temperature;
		this.humidity = humidity;
		this.smog = smog;
		this.power = power;
		this.waterlog = waterlog;
	}
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getSmog() {
		return smog;
	}
	public void setSmog(String smog) {
		this.smog = smog;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getWaterlog() {
		return waterlog;
	}
	public void setWaterlog(String waterlog) {
		this.waterlog = waterlog;
	}
}