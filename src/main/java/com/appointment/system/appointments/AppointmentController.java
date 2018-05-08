package com.appointment.system.appointments;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private AppointmentRepository repository;

	public AppointmentController(AppointmentRepository appointmentRepository){
		this.repository = appointmentRepository;
	}

	@GetMapping("/{id}")
	public Appointment getAppointment(@PathVariable(name = "id") Long id){
		return repository.findOne(id);
	}

	@GetMapping("/all")
	public Iterable<Appointment> getAppointments(){
		return repository.findAll();
	}

	@PutMapping("/update")
	public void updateAppointment(@RequestBody Appointment appointment){
		repository.save(appointment);
	}

	@PostMapping("/create")
	public Appointment createAppointment(@RequestBody Appointment appointment){
		repository.save(appointment);
		return appointment;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAppointment(@PathVariable(name = "id") Long id){
		repository.delete(id);
	}

}
