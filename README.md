# Shortener URL

### About the project:

Backend application to shorten and manage URLs.

You will receive a link and transform it into a shorter one that is easier to remember and lets you know the number of hits.
### Examples:
#### - (POST) Shortening the URL
```
  Input:
  url: https://github.com/leilanyaragao
  
  Output:
  "hgyjhouyn"
```

#### - (GET) Accessing the shortened URL
```
  Input:
  localhost:8080/hgyjhouyn
  
  Output:
  https://github.com/leilanyaragao
```
> [!NOTE]
> So by accessing this code at the url localhost:8080/hgyjhouyn , you will be automatically redirected to https://github.com/leilanyaragao

#### - (GET) Displaying the average number of hits to the shortened url
```
  Input:
  localhost:8080/all/hgyjhouyn
  
  Output:
  3
```

#### - (GET) Displaying the average number of hits per specific day of the shortened url
```
  Input:
  localhost:8080/all/hgyjhouyn/date/2024-06-06
  
  Output:
  2
```

Technologies used:
* Commons apache lang
* Docker
* Java
* JPA
* Junit
* Lombok
* MongoDB

## Installation

### Pre-requisites

* Postman or another endpoint testing application. 

* Some IDE that runs Java, in the project we used Intellij. 

* Docker to be able to upload the MongoDB database.
> [!NOTE]
> - To follow the creation and have access to the database information, you can download MongoDB Compass.
> 
> - You can also test the applications using JUnit tests.


### Installation

1. Get the repository link https://github.com/leilanyaragao/shortenerURL
2. Clone the repository
   ```https
   git clone https://github.com/leilanyaragao/shortenerURL.git
   ```
3. Open the project in your preferred IDE

4. Open the terminal in the docker folder and run - docker compose up - so that the database is created.

5. In the IDE run the file ShortenerUrlApplication.java

8. In postman (or another application of your choice) test the endpoints at localhost:8080

```
    JSON example :
    {
    “url”: “https://github.com/leilanyaragao”
    }
 ```

   ```JS
   POST /url - to create the shortened url
   
   GET {id} - Redirects to the page link with the code generated when sending the URL
   
   GET /all/{searchUrl} - Displays the average number of hits on the shortened url

   GET /all/{searchUrl}/date/{localDate} - Displays the average number of hits per day of the shortened url

   ```
