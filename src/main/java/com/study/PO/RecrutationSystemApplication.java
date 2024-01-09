package com.study.PO;

import com.study.PO.entities.wydzial.Wydzial;
import com.study.PO.repositories.WydzialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class RecrutationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecrutationSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner init(WydzialRepository wydzialRepository) {
		return args -> {
			List<String> nazwyWydzialow = List.of("Wydział Architektury", "Wydział Budownictwa", "Wydział Informatyki i Telekomunikacji");
			int i = 1;
			for (String nazwa : nazwyWydzialow) {
				Wydzial wydzial = new Wydzial(nazwa, "W" + i);
				i++;
				wydzialRepository.save(wydzial);
			}
			wydzialRepository.findAll().forEach(System.out::println);
		};
	}

}
