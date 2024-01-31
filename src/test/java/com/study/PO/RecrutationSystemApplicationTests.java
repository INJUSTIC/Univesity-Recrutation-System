package com.study.PO;

import com.study.PO.controllers.DzialRekrutacjiController;
import com.study.PO.entities.dokument.Dokument;
import com.study.PO.entities.kandydat.Kandydat;
import com.study.PO.entities.wniosek.Wniosek;
import com.study.PO.repositories.KandydatRepository;
import com.study.PO.services.KandydatService;
import com.study.PO.services.WniosekService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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


}
