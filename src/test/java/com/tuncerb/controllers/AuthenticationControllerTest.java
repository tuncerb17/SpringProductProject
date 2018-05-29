package com.tuncerb.controllers;

import com.tuncerb.commands.UserCommand;
import com.tuncerb.config.CustomUserDetailsService;
import com.tuncerb.domain.User;
import com.tuncerb.exceptions.ControllerExceptionHandler;
import com.tuncerb.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by tuncer on 29/05/2018.
 */
public class AuthenticationControllerTest {
    @Mock
    UserService userService;
    @Mock
    CustomUserDetailsService customUserDetailsService;

    AuthenticationController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/");
        viewResolver.setSuffix(".html");

        controller = new AuthenticationController(userService, customUserDetailsService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void testGetLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testLogoutRequest() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    public void testLogoutRequestWithUser() throws Exception {
        Assert.assertTrue(false);
        //Authentication error
    }

    @Test
    public void testGetRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void testPostRegister() throws Exception {
        Assert.assertTrue(false);
        //Authentication error

        UserCommand command = new UserCommand();
        command.setId(1L);
        command.setUsername("testuser");
        command.setPassword("password");

        User u = new User();
        u.setId(1L);
        u.setUsername("testuser");
        u.setPassword("password");

        Optional<User> opt =  Optional.of(u);

        when(userService.saveUserCommand(any())).thenReturn(command);
        when(userService.findByUsername(any())).thenReturn(opt);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "password")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
