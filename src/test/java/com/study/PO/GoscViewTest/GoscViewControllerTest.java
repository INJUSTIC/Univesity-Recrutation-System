package com.study.PO.GoscViewTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MvcResult;

import com.study.PO.controllers.GoscViewController;
import com.study.PO.entities.kierunek.Kierunek;
import com.study.PO.services.KierunekService;

public class GoscViewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private KierunekService kierunekService;

    @InjectMocks
    private GoscViewController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testPrzegladajKierunek() throws Exception {
        long id = 1;
        Kierunek expectedKierunek = new Kierunek();
        expectedKierunek.setId(id);
        when(kierunekService.getKierunek(id)).thenReturn(expectedKierunek);

        MvcResult result = mockMvc.perform(get("/gosc/przegladajkierunek/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("gosc/PU2_przegladaj_kierunek_page"))
                .andExpect(model().attribute("kierunek", expectedKierunek))
                .andReturn();

        Kierunek actualKierunek = (Kierunek) result.getModelAndView().getModel().get("kierunek");
        assertEquals(id, actualKierunek.getId());
        
        verify(kierunekService, times(1)).getKierunek(id);
    }
}
