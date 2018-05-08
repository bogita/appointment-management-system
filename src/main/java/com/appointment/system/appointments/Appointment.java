package com.appointment.system.appointments;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name = "start_time", columnDefinition="DATETIME")
	private Date start_time;

	@Column(name = "end_time", columnDefinition="DATETIME")
	private Date end_time;

	private String name;
	private String type;
	private String description;
	private String metadata;

	protected Appointment(){}

	public Appointment(Date start_time, Date end_time, String name, String type, String description, String metadata){
		this.start_time=start_time;
		this.end_time=end_time;
		this.name=name;
		this.type=type;
		this.description=description;
		this.metadata=metadata;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", start_time=" + start_time +
				", end_time=" + end_time +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", description='" + description + '\'' +
				", metadata='" + metadata + '\'' +
				'}';
	}
}
