package com.appointment.system.appointments;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import com.appointment.system.JsonDateSerializer;
import com.appointment.system.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Appointment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@JsonDeserialize(using = JsonDateSerializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime start_time;

	@JsonDeserialize(using = JsonDateSerializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime end_time;

	private String name;
	private String type;
	private String description;
	private String metadata;

	protected Appointment(){}

	public Appointment(LocalDateTime start_time, LocalDateTime end_time, String name, String type, String description, String metadata){
		this.start_time=start_time;
		this.end_time=end_time;
		this.name=name;
		this.type=type;
		this.description=description;
		this.metadata=metadata;
	}

	public Long getId(){
		return id;
	}

	public void setId(Long id){
		this.id=id;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Appointment that = (Appointment) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(start_time, that.start_time) &&
				Objects.equals(end_time, that.end_time) &&
				Objects.equals(name, that.name) &&
				Objects.equals(type, that.type) &&
				Objects.equals(description, that.description) &&
				Objects.equals(metadata, that.metadata);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, start_time, end_time, name, type, description, metadata);
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
