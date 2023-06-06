package com.App.GitRepositoryApp.controller;

import com.App.GitRepositoryApp.dto.RepositoryDetails;
import com.App.GitRepositoryApp.service.RepositoryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepositoryController.class)
public class RepositoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;

    private static RepositoryDetails repositoryOne;


    @BeforeAll
    public static void setUp() {

        repositoryOne = new RepositoryDetails();
        repositoryOne.setFullName("test-repo");
        repositoryOne.setDescription("Test repository");
        repositoryOne.setCreatedAt(ZonedDateTime.parse("2017-07-02T11:51:34Z"));
        repositoryOne.setCloneUrl("test-clone-url");
        repositoryOne.setForked(false);
        repositoryOne.setForks(0);
    }

    @Test
    public void testGetRepositoryByRepositoryName() throws Exception {

        when(repositoryService.getRepositoryByName(anyString(), anyString())).thenReturn(repositoryOne);

        mockMvc.perform(get("/repositories/{owner}/{repository-name}", "test-user", "test-repo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.full_name", is("test-repo")))
                .andExpect(jsonPath("$.description", is("Test repository")))
                .andExpect(jsonPath("$.created_at", is("2017-07-02T11:51:34Z")))
                .andExpect(jsonPath("$.clone_url", is("test-clone-url")))
                .andExpect(jsonPath("$.fork", is(false)))
                .andExpect(jsonPath("$.forks_count", is(0)));

    }
}
