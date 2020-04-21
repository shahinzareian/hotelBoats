package com.example.demo;

import com.example.demo.controllers.BoatControllers;
import com.example.demo.models.Boats;
import com.example.demo.repositories.BoatsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestRowingBoatController {
    @InjectMocks
    private BoatControllers boatControllers;
    @Mock
    private BoatsRepository boatsRepository;
    private MockMvc mockMvc;
    @Before
    public void setup(){
        mockMvc= MockMvcBuilders.standaloneSetup(boatControllers).build();

    }
    @Test
    public void dummyTest(){
        assertTrue(true);
    }

        @Test
    public void testGet() throws Exception{
        List<Boats> result =new ArrayList<>();
        result.add(new Boats(1L,"r1","rowing",3,30,true,0));

        when(boatsRepository.findAll()).thenReturn(result);

        mockMvc.perform(get("/api/Boatss"))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].boatNumber", is("r1")))
                .andExpect(jsonPath("$.[0].boatType", is("rowing")))
                .andExpect(jsonPath("$.[0].numberOfSeat", is(3)))
                .andExpect(jsonPath("$.[0].minPrice", is(30)))
                .andExpect(jsonPath("$.[0].available", is(true)))
                .andExpect(jsonPath("$.[0].usageNumber", is(0)))



                .andExpect(status().isOk());

    }


}
