# API Test Java Canal+_2020

## Java Version

	Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
	Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode)

## Configuration BDD H2

	Pilote JDBC: org.h2.Driver
	URL JDBC: jdbc:h2:mem:testdb
	Nom d'utilisateur: sa
	Mot de passe :

# Lancer l'API & TEST sur POSTMAN

## Créer un nouvel abonné

### Request

`POST /register`

	http://localhost:8080/register

### RequestBody

	{
	  "nom" : "testNom",
	  "prenom" : "testPrenom"
	}

### Response

    HTTP/1.1 201 Created
    Status: 201 Created

## Crée une nouvelle adresse

### Request

`POST /adress`

	http://localhost:8080/address

### RequestBody
	
	{
          "rue": "1, place du Spectacle", 
          "postalCode": "92 863",
          "city": "ISSY LES MOULINEAUX",
          "country": "France"
	}

### Response
	
	HTTP/1.1 201 Created
	Status: 201 Created
   
	{
         "id": 2,
         "rue": "1, place du Spectacle", 
         "postalCode": "92 863",
         "city": "ISSY LES MOULINEAUX",
         "country": "France"
         "creationDate": "2020-12-15T18:49:41.659+00:00",
         "updatedDate": "2020-12-15T18:49:41.659+00:00"
	}

## Crée un contrat

### Request

`Post /contrat`

	http://localhost:8080/contrat

### Response

	HTTP/1.1 201 Created
	Status: 201 Created

	{
	  "adresse": {
		"id": 2,
		"rue": "1, place du Spectacle", 
		"postalCode": "92 863",
		"city": "ISSY LES MOULINEAUX",
		"country": "France"
		"creationDate": "2020-12-15T18:49:41.659+00:00",
		"updatedDate": "2020-12-15T18:49:41.659+00:00"
	    },
	    "creationDate": "2020-12-15T18:49:46.468+00:00",
	    "updatedDate": "2020-12-15T18:49:46.468+00:00",
	    "id": 3
	}

## Récupérer les informations de l'abonné 

### Request

`GET /abonne`

	http://localhost:8080/abonne

### Response

	HTTP/1.1 200 OK
	Status: 200 OK
    
	[
	    {
		"nom": "testNom",
		"prenom": "testPrenom",
		"adresse": {
		    "id": 2,
         	    "rue": "1, place du Spectacle", 
                    "postalCode": "92 863",
                    "city": "ISSY LES MOULINEAUX",
                    "country": "France",
		    "creationDate": "2020-12-15T18:49:41.659+00:00",
		    "updatedDate": "2020-12-15T18:50:03.425+00:00"
		},
		"contrats": [
		    {
		        "adresse": {
		            "id": 2,
         		    "rue": "1, place du Spectacle", 
                            "postalCode": "92 863",
                            "city": "ISSY LES MOULINEAUX",
                            "country": "France",
		            "creationDate": "2020-12-15T18:49:41.659+00:00",
		            "updatedDate": "2020-12-15T18:50:03.425+00:00"
		        },
		        "creationDate": "2020-12-15T18:49:46.468+00:00",
		        "updatedDate": "2020-12-15T18:49:46.468+00:00",
		        "id": 3
		    }
		],
		"creationDate": "2020-12-15T18:49:15.051+00:00",
		"updatedDate": "2020-12-15T18:49:41.888+00:00",
		"id": 1
	    }
	]
    

## Modifier l'adresse de l'abonné

### Request

`PUT /address/2`

	http://localhost:8080/address/2

### RequestBody

	{
	  "rue": "1, place du Spectacle", 
	  "postalCode": "75000",
	  "city": "Paris",
	  "country": "France"
	}

### Response

    HTTP/1.1 200 OK
    Status: 200 OK

	[
	  {
            "id": 2,
            "rue": "1, place du Spectacle", 
            "postalCode": "75000",
            "city": "Paris",
            "country": "France"
            "creationDate": "2020-12-15T18:49:41.659+00:00",
            "updatedDate": "2020-12-15T19:19:35.659+00:00"
	  }
	]

## Récupérer l’historique des mouvements de modification effectués par l'abonné 

### Request

`GET /mouvement/MvtByAbonne`

    http://localhost:8080/mouvement/MvtByAbonne

### Response

    HTTP/1.1 200 OK
    Status: 200 OK
[
 {
       "id": 4,
       "abonne": {
        	"nom": "user",
            	"prenom": "thiva",
            	"adresse": {
               	"id": 2,
            	"rue": "1, place du Spectacle", 
            	"postalCode": "75000",
            	"city": "Paris",
            	"country": "France"
            	"creationDate": "2020-12-15T18:49:41.659+00:00",
            	"updatedDate": "2020-12-15T19:19:35.659+00:00"
            	},
            	"contrats": [
                	{
                    	"adresse": {
			        "id": 2,
		    		"rue": "1, place du Spectacle", 
		    		"postalCode": "75000",
		    		"city": "Paris",
		    		"country": "France"
		    		"creationDate": "2020-12-15T18:49:41.659+00:00",
		    		"updatedDate": "2020-12-15T19:19:35.659+00:00"
                    		},
                 	creationDate": "2020-12-15T18:49:46.468+00:00",
                    	"updatedDate": "2020-12-15T18:49:46.468+00:00",
                    	"id": 3
                	}
            	],
            	"creationDate": "2020-12-15T18:49:15.051+00:00",
            	"updatedDate": "2020-12-15T18:49:41.888+00:00",
            	"id": 1
	},
	"contrats": [
	{
	  "adresse": {
	          "id": 2,
            	  "rue": "1, place du Spectacle", 
            	  "postalCode": "75000",
	          "city": "Paris",
		  "country": "France"
		  "creationDate": "2020-12-15T18:49:41.659+00:00",
		  "updatedDate": "2020-12-15T19:19:35.659+00:00"
		  },
		  "creationDate": "2020-12-15T18:49:46.468+00:00",
		  "updatedDate": "2020-12-15T18:49:46.468+00:00",
		  id": 3
	}
	],
	"oldValue": "\n[id=2, rue=1, place du Spectacle, postalCode=92 863, city=ISSY LES MOULINEAUX, country=France, creationDate=2020-12-15T18:49:41.659, updateUser=2020-12-15T18:49:41.659 ",
	"newValue": "\n[id=2, rue=1, place du Spectacle, postalCode=75000, city=Paris, country=France, creationDate=2020-12-15T18:49:41.659, updateUser=2020-12-15T19:19:35.659 ",
	"type": "MOUVEMENT_ADRESSE",
	"creationDate": "2020-12-15T19:50:03.430+00:00"
 }
]
##
