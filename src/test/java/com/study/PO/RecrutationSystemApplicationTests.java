package com.study.PO;

import com.study.PO.controllers.DzialRekrutacjiController;
import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.entities.kierunek.Opiekun;
import com.study.PO.entities.kierunek.StopienStudiow;
import com.study.PO.entities.kierunek.wskaznik.*;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.repositories.KandydatRepository;
import com.study.PO.repositories.KierunekRepository;
import com.study.PO.services.KandydatService;
import com.study.PO.services.KierunekService;
import com.study.PO.services.WniosekService;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RecrutationSystemApplicationTests {

	@Mock
	private WniosekService wniosekService;

	@InjectMocks
	private DzialRekrutacjiController dzialRekrutacjiController;

	@Mock
	private KandydatRepository repositoryKandydat;

	@InjectMocks
	private KandydatService kandydatService;

	@Mock
	private KierunekRepository repositoryKierunek;

	@InjectMocks
	private KierunekService kierunekService;


	//Test do metody DzialRekrutacjiController.getDokumenty()
	@Test
	public void testGetDokumenty() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(dzialRekrutacjiController).build();

		long wniosekId = 1L;
		Wniosek wniosek = new Wniosek();
		List<Dokument> documents = Arrays.asList(new Dokument(), new Dokument());
		wniosek.setDokumenty(documents);

		when(wniosekService.getWniosek(wniosekId)).thenReturn(wniosek);

		mockMvc.perform(MockMvcRequestBuilders.get("/dzialRekrutacji/przeprowadzenieRekrutacji/wnioski/{wniosekId}/dokumenty", wniosekId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("dokumentyWniosku"))
				.andExpect(MockMvcResultMatchers.model().attribute("dokumentyWniosku", documents));
	}

	//Test do metody kandydatService.getKandydat(kandydatId) (zwraca kandydata o podanym id)
	@Test
	void testGetKandydatWhenExists() {

		Long kandydatId = 1L;
		Kandydat expectedKandydat = new Kandydat();
		expectedKandydat.setId(kandydatId);
		when(repositoryKandydat.findById(kandydatId)).thenReturn(Optional.of(expectedKandydat));
		Kandydat result = kandydatService.getKandydat(kandydatId);
		assertNotNull(result);
		assertEquals(kandydatId, result.getId());
	}

	//Test do metody kandydatService.getKandydat(kandydatId) (zwraca null, gdy kandydat o podanym id nie istnieje)
	@Test
	void testGetKandydatWhenNotExists() {
		Long kandydatId = 1L;
		when(repositoryKandydat.findById(kandydatId)).thenReturn(Optional.empty());
		Kandydat result = kandydatService.getKandydat(kandydatId);
		assertNull(result);
	}

	void setAttributesOfExampleKierunki(Kierunek kierunek1, Kierunek kierunek2) {
		Opiekun opiekun1 = new Opiekun();
		opiekun1.setImie("Adam");
		opiekun1.setNazwisko("Kowalski");

		List<Integer> przeszleProgi1 = new ArrayList<>();
		przeszleProgi1.add(380);
		przeszleProgi1.add(450);

		kierunek1.setNazwa("Informatyka stosowana");
		kierunek1.setOpiekun(opiekun1);
		kierunek1.setPlanStudiow("sciezka/do/planu/studiow");
		kierunek1.setPrzeszProgi(przeszleProgi1);
		kierunek1.setProgPunktowy(480);
		kierunek1.setPrognLiczbaMiejsc(150);
		kierunek1.setLiczbaOsNaMiejsce(9);
		kierunek1.setCenaZaWniosek(85.0);
		kierunek1.setStopienStudiow(StopienStudiow.I);

		kierunek2.setStopienStudiow(StopienStudiow.I);
	}

	//Test do metody kierunekService.getAllKierunekByStopienStudiow. Sprawdza, czy zwraca oczekiwane kierunki, gdy te istnieja
	@Test
	void testGetAllKierunekByExistingStopienStudiow() {
		String stopienStudiow = "I";
		Kierunek kierunek1 = new Kierunek();
		Kierunek kierunek2 = new Kierunek();

		setAttributesOfExampleKierunki(kierunek1, kierunek2);

		List<Kierunek> expectedKierunki = Arrays.asList(kierunek1, kierunek2);

		when(repositoryKierunek.findKierunkiByStopienStudiow(stopienStudiow))
				.thenReturn(expectedKierunki);

		List<Kierunek> actualKierunki = kierunekService.getAllKierunekByStopienStudiow(stopienStudiow);

		assertNotNull(actualKierunki);
		assertEquals(expectedKierunki, actualKierunki);

		verify(repositoryKierunek).findKierunkiByStopienStudiow(stopienStudiow);
	}

	//Test do metody PrzelicznikFactory.getPrzelicznik sprawdzajacy czy stworzy poprawny przelicznik dla istniejacego kryterium
	@Test
	void testGetPrzelicznikMaturaPolska() {
		Kryterium maturaPolskaKryterium = new MaturalnyPrzedmiotDodatkowy(true, false, false, true, false);
		maturaPolskaKryterium.setNazwa("MATURA_POLSKA");

		PrzelicznikKryterium result = PrzelicznikFactory.getPrzelicznik(maturaPolskaKryterium);

		assertTrue(result instanceof PrzelicznikMaturaPolska);
	}

	//Test do metody PrzelicznikFactory.getPrzelicznik sprawdzajacy czy dla sztucznego kryterium zwroci null
	@Test
	void testGetPrzelicznikUnknownNull() {
		class OtherKryterium extends Kryterium {
			private String a;

			public OtherKryterium(String a) {
				this.a = a;
			}

			public String getA() {
				return a;
			}

			public void setA(String a) {
				this.a = a;
			}
		}
		Kryterium unknownKryterium = new OtherKryterium("testString");
		unknownKryterium.setNazwa("OtherKryterium");
		PrzelicznikKryterium result = PrzelicznikFactory.getPrzelicznik(unknownKryterium);
		assertNull(result);
	}


}
