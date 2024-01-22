package com.study.PO.DzialITTests;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.study.PO.controllers.DzialITController;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.services.KierunekService;

public class DzialITControllerTest {

    private MockMvc mockMvc;

    @Mock
    private KierunekService kierunekService;

    @InjectMocks
    private DzialITController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetOplatyRekrutacyjnePage() throws Exception {
        List<Kierunek> expectedKierunki = Arrays.asList(new Kierunek(), new Kierunek());
        when(kierunekService.getAllKierunek()).thenReturn(expectedKierunki);

        mockMvc.perform(get("/DzialIT/zarzadzanieOplatamiRekrutacyjnymi"))
                .andExpect(status().isOk())
                .andExpect(view().name("DzialIT/PU_1_wszytkieKierunki_page"))
                .andExpect(model().attribute("kierunki", expectedKierunki));
        
        verify(kierunekService, times(1)).getAllKierunek();
    }
}