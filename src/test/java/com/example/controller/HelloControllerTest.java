package com.example.controller;

import com.example.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HelloControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HelloController helloController;

    @Mock
    private HelloService helloService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }

    @Test
    public void testHello() throws Exception {
        // Given
        String username = "batman";
        String path = "/hello/" + username;

        String response = "Hello batman from Spring-boot app";
        Mockito.doReturn(response).when(helloService).hello(eq(username));

        // When
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andExpect(header().doesNotExist("error"));

        // Then
        Mockito.verify(helloService, Mockito.times(1)).hello(username);
    }

    @Test
    public void testHelloWithServerError() throws Exception {
        // Given
        String username = "batman";
        String path = "/hello/" + username;

        String errorMessage = "Force a server error";
        Mockito.doThrow(new RuntimeException(errorMessage)).when(helloService).hello(eq(username));

        // When
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(""))
                .andExpect(header().string("error", errorMessage));

        // Then
        Mockito.verify(helloService, Mockito.times(1)).hello(username);
    }

}
