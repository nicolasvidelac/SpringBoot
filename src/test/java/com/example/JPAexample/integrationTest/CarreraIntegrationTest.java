package com.example.JPAexample.integrationTest;


import com.example.JPAexample.JpAexampleApplication;
import com.example.JPAexample.models.Carrera;
import com.example.JPAexample.models.Universidad;
import com.example.JPAexample.repositories.interfaces.CarreraRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpAexampleApplication.class)
@ActiveProfiles("test")
public class CarreraIntegrationTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Autowired
    private CarreraRepository _carreraRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void tearUp() {
        _carreraRepository.deleteAll();
        _carreraRepository.flush();
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testGetAllCarrerasPositivo() throws Exception {
        this.mockMvc.perform(get("/api/v1/carreras")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testSaveCarreraPositivo() throws Exception {
        Universidad uni1 = new Universidad(1, "nombre", "calle", 12);
        Carrera cr1 = new Carrera(1, "nombre", "codigo", uni1);
        _carreraRepository.saveAndFlush(cr1);
        this.mockMvc.perform(get("/api/v1/carreras/1"))
//                TODO comparar el objeto que me trae con el que tengo
//                .andExpect(content().toString().equals(cr1.toString())))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testSaveCarreraNegativo() throws Exception {
        //Guardo el mismo objeto que ya esta guardado
        Carrera cr1 = new Carrera(1, "nombre", "codigo", null);
        _carreraRepository.saveAndFlush(cr1);
        this.mockMvc.perform(post("/api/v1/carreras/", cr1))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testGetSingleCarreraNegativo() throws Exception {
        this.mockMvc.perform(get("/api/v1/carreras/323")).andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testDeleteCarreraPositivo() throws Exception {
        Universidad uni1 = new Universidad(1, "nombre", "calle", 12);
        Carrera cr1 = new Carrera(1, "nombre", "codigo", uni1);
        cr1 = _carreraRepository.saveAndFlush(cr1);
        this.mockMvc.perform(get(String.format("/api/v1/carreras/%s", cr1.getId()))).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "carrera:read")
    public void testDeleteCarreraNegativo() throws Exception {
        Universidad uni1 = new Universidad(1, "nombre", "calle", 12);
        Carrera cr1 = new Carrera(1, "nombre", "codigo", uni1);
        cr1 = _carreraRepository.saveAndFlush(cr1);
        //pruebo con el id + 1 para no poner el id correcto
        this.mockMvc.perform(get(String.format("/api/v1/carreras/%s", cr1.getId() + 1))).andExpect(status().isNotFound());
    }


}
