package com.uxpsystems.assignment.controller;

import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void testList() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(get("/assignment/list")).
                andExpect(status().isOk()).andReturn().getModelAndView();
        assertEquals("user_list", modelAndView.getViewName());
    }

    @Test
    public void testAddUser() throws Exception {
        ModelAndView modelAndView = mockMvc.perform(get("/assignment/addUser")).
                andExpect(status().isOk()).andReturn().getModelAndView();
        assertEquals("user_form", modelAndView.getViewName());
    }

    @Test
    public void testSave() throws Exception {
        User mockUser = Mockito.mock(User.class);
        ModelAndView modelAndView = mockMvc.perform(post("/assignment/saveUser",mockUser)).
                andExpect(status().isFound()).andReturn().getModelAndView();
        assertEquals("redirect:/assignment/list", modelAndView.getViewName());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long mockUserId = 1L;
        ModelAndView modelAndView = mockMvc.perform(get("/assignment/deleteUser/"+mockUserId,mockUserId)).
                andExpect(status().isFound()).andReturn().getModelAndView();
        assertEquals("redirect:/assignment/list", modelAndView.getViewName());
    }

    @Test
    public void testEditUser() throws Exception {
        Long mockUserId = 1L;
        ModelAndView modelAndView = mockMvc.perform(get("/assignment/updateUser/"+mockUserId,mockUserId)).
                andExpect(status().isOk()).andReturn().getModelAndView();
        assertEquals("user_form", modelAndView.getViewName());
    }
}
