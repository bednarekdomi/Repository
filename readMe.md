![github](https://user-images.githubusercontent.com/40741056/74937413-4304d980-53ec-11ea-8010-58655042feb5.jpg)

# About

##### REST API  microservice application uses Spring Boot and external gitHub API https://developer.github.com/v3

* getting details about owner's repository
* getting list of owner's repositories
* finding the oldest repository
* finding most forked repository, if more than one, return the most recent

* application tests

## Technologies

* Java
* Spring Boot
* REST API
* Lombok
* Mockito
* Maven

____________________________________________________________________________________________________________
####  Get_Repository_By_Repository_Name
##### Getting details about owner's repository
* GET
* Example Request:
````
http://localhost:8080/repositories/bednarekdomi/mountainCrudApp
````           
* Example JSON Body:
````	
{
    "full_name": "bednarekdomi/mountainCrudApp",
    "description": null,
    "clone_url": "https://github.com/bednarekdomi/mountainCrudApp.git",
    "fork": false,
    "forks_count": 0,
    "created_at": "2023-01-09T18:23:21Z"
}
````
* Succes Response: Code 200

#### Get_Repositories_By_User_Name
##### Getting list of owner's repositories:
* GET
* Example Request
````
http://localhost:8080/getUserRepositories/bednarekdomi
````
** Example JSON Body:
````	
[
    {
        "full_name": "bednarekdomi/mountainCrudApp",
        "description": null,
        "clone_url": "https://github.com/bednarekdomi/mountainCrudApp.git",
        "fork": false,
        "forks_count": 0,
        "created_at": "2023-01-09T18:23:21Z"
    },
    {
        "full_name": "bednarekdomi/WindsurfersForecast",
        "description": "Worldwide weather forecast Service for windsurfer's ",
        "clone_url": "https://github.com/bednarekdomi/WindsurfersForecast.git",
        "fork": false,
        "forks_count": 0,
        "created_at": "2023-02-21T09:27:01Z"
    }
]
````
* Succes Response: Code 200

___________________________________________________________________________________________________________________________________________
#### Get_Oldest_Repository
##### Finding the oldest repository
* GET
* Example Request:
````
http://localhost:8080/getOldestRepository/bednarekdomi
````
* Example JSON Body:
````	
{
    "full_name": "bednarekdomi/mountainCrudApp",
    "description": null,
    "clone_url": "https://github.com/bednarekdomi/mountainCrudApp.git",
    "fork": false,
    "forks_count": 0,
    "created_at": "2023-01-09T18:23:21Z"
}
````
* Succes Response: Code 200

#### Get_Most_Forked_Repository
##### Finding most forked repository, if more than one, return the most recent
* GET
* Example Request:
````
http://localhost:8080/getMostForkedRepository/bednarekdomi
````
* Example JSON Body:
````	
{
    "full_name": "bednarekdomi/WindsurfersForecast",
    "description": "Worldwide weather forecast Service for windsurfer's ",
    "clone_url": "https://github.com/bednarekdomi/WindsurfersForecast.git",
    "fork": false,
    "forks_count": 0,
    "created_at": "2023-02-21T09:27:01Z"
}
````