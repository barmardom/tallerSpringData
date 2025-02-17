/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.repository.SpecialityRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.service.PetClinicService;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;

/**
 * PetClinic Spring Boot Application.
 * 
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {
	//@Autowired
	//VetRepository vetRepository;
	
	@Autowired
	PetClinicService petClinicService;

	private static final Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PetClinicApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoVetRepository(VetRepository vetRepository, SpecialityRepository specialityRepository) {
		return (args) -> {
			log.info("*****************************************************");
			log.info("BOOTCAMP - Spring y Spring Data - vetRepository");
			log.info("*****************************************************");
			
			String firstName = "Bartolome";
			String lastName = "Marquez";
			String specialtyName = "radiology";

			List<Vet> allVets = petClinicService.findAll();
			List<Vet> specialtyVets = petClinicService.findBySpecialityName(specialtyName);
			

			petClinicService.altaVeteriario(firstName, lastName, specialtyName);
			
			
			log.info("VETS CON UNA DETERMINADA ESPECIALIDAD");
			for (Vet vet : specialtyVets) {
				log.info(vet.toString());
			}
			
			log.info("TODOS LOS VETS");
			for (Vet vet : allVets) {
				log.info(vet.toString());
			}
		


		};
	}


}
