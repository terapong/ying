package toto.xdev.modet;

public class Message {
	private String name;
	private Long temperature;
	private Long humidity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTemperature() {
		return temperature;
	}
	public void setTemperature(Long temperature) {
		this.temperature = temperature;
	}
	public Long getHumidity() {
		return humidity;
	}
	public void setHumidity(Long humidity) {
		this.humidity = humidity;
	}
}
