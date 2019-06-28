package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetClinicService {

	@Autowired
	private VetRepository vetRepository;

	@Autowired
	private SpecialityRepository specialityRepository;

	public Vet altaVeteriario(String firstName, String lastName, String specialtyName) {

		Vet vet = new Vet();
		List<Vet> lista = findByfirstNameAndLastName(firstName, lastName);

		if (lista == null || lista.size() == 0) {

			vet.setFirstName(firstName);
			vet.setLastName(lastName);

			vet = vetRepository.save(vet);

			Vet vetConsultado = vetRepository.findOne(vet.getId());
			System.out.println(vetConsultado.getFirstName());

			Specialty spe = specialityRepository.findFirstByName("radiology");

			vet.addSpecialty(spe);
			vet = vetRepository.save(vet);
		} else {

		}
		return vet;
	}

	public List<Vet> findBySpecialityName(String name) {
		return vetRepository.findBySpecialityName(name);
	}

	public List<Vet> findByfirstNameAndLastName(String firstName, String lastName) {
		return vetRepository.findByfirstNameAndLastName(firstName, lastName);
	}

	public List<Vet> findAll() {
		return vetRepository.findAll();
	}

}
