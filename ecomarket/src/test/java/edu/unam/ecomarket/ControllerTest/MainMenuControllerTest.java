package edu.unam.ecomarket.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import edu.unam.ecomarket.controller.MainMenuController;

@WebMvcTest(MainMenuController.class)
public class MainMenuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMenuPrincipal() throws Exception {
        mockMvc.perform(get("/mainMenu"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainMenu"));
    }

}
