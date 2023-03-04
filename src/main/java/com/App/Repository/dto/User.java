package com.App.Repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class User {

    private List<RepositoryDetails> usersRepositories;
}