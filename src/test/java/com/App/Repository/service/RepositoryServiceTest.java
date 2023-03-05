package com.App.Repository.service;

import com.App.Repository.dto.RepositoryDetails;
import com.App.Repository.dto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RepositoryServiceTest {

    @InjectMocks
    private RepositoryService repositoryService;
    @Mock
    private GitHubClient gitHubClient;
    private final List<RepositoryDetails> repositories = new ArrayList<>();
    private User user;
    private RepositoryDetails repositoryOne;


    @Before
    public void setUp() {
        repositoryOne = new RepositoryDetails("user/repositoryOne", "My first repository",
                "https://github.com/user/repositoryOne", true, 3, ZonedDateTime.parse("2018-07-02T11:51:34Z"));

        RepositoryDetails repositoryTwo = new RepositoryDetails("user/repositoryTwo", "My second repository",
                "https://github.com/user/repositoryTwo", true, 2, ZonedDateTime.parse("2019-07-02T11:51:34Z"));

        RepositoryDetails repositoryThree = new RepositoryDetails("user/repositoryThree", "My third repository",
                "https://github.com/user/repositoryThree", true, 3, ZonedDateTime.parse("2017-07-02T11:51:34Z"));

        repositories.add(repositoryOne);
        repositories.add(repositoryTwo);
        repositories.add(repositoryThree);
        user = new User(repositories);
    }

    @Test
    public void shouldGetRepositoryByName() {
        //Given
        when(gitHubClient.getRepository("user", "repositoryOne")).thenReturn(repositoryOne);
        //When
        RepositoryDetails repositoryDetails = repositoryService.getRepositoryByName("user", "repositoryOne");
        //Then
        assertEquals("user/repositoryOne", repositoryDetails.getFullName());
    }

    @Test
    public void shouldGetAllUserRepositories() {
        //Given
        when(gitHubClient.getRepositories("user")).thenReturn(user);
        //When
        repositoryService.getAllUserRepositories("user");
        //Then
        assertEquals(3, user.getUsersRepositories().size());
    }

    @Test
    public void shouldGetOldestRepository() {
        //Given
        when(gitHubClient.getRepositories("user")).thenReturn(user);
        //When
        RepositoryDetails repositoryDetails = repositoryService.getOldestRepository("user");
        //Then
        assertEquals(ZonedDateTime.parse("2017-07-02T11:51:34Z"), repositoryDetails.getCreatedAt());
    }

    @Test
    public void shouldGetMostForkedRepository() {
        //Given
        when(gitHubClient.getRepositories("user")).thenReturn(user);
        //When
        RepositoryDetails repositoryDetails = repositoryService.getMostForkedRepository("user");
        //Then
        assertEquals(ZonedDateTime.parse("2018-07-02T11:51:34Z"), repositoryDetails.getCreatedAt());
    }
}