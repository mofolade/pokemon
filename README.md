Pokemon

*************
*** USER ****
*************
GET
localhost:8080/api/v1/users

SEARCH
localhost:8080/api/v1/users/5f947426e760519adc4d7cc9
localhost:8080/api/v1/users/filter?name=ra&city=u&username=a&reg_year=2019

UPDATE
PUT localhost:8080/api/v1/users/5f947426e760519adc4d7cc9

DELETE
localhost:8080/api/v1/users/5f946ef2e760519adc4d7cc8
localhost:8080/api/v1/users/all/

***************
*** POKEMON ***
***************
SAVE
localhost:8080/api/v1/pokemons/list?offset=100
next: localhost:8080/api/v1/pokemons/list?offset=200
localhost:8080/api/v1/pokemons?name=delcatty

SEARCH
localhost:8080/api/v1/pokemons/search?name=it
localhost:8080/api/v1/pokemons/filter?name=it&baseexperience=159&height=15&weight=498

DELETE
localhost:8080/api/v1/pokemons?name=marowak
localhost:8080/api/v1/pokemons?id=5f93ddb38ce2957968b33dae
localhost:8080/api/v1/pokemons/all/

************
*** GAME ***
************
SAVE
localhost:8080/api/v1/games?name=generation-i
localhost:8080/api/v1/games?name=1

SEARCH
localhost:8080/api/v1/games/search?name=er

DELETE
localhost:8080/api/v1/games?name=generation-i
localhost:8080/api/v1/games/all/

*************
*** BERRY ***
*************
SAVE
localhost:8080/api/v1/berry?name=cheri
localhost:8080/api/v1/berry?name=1

SEARCH
localhost:8080/api/v1/berry/search?name=er
localhost:8080/api/v1/berry/filter?name=er&max_harvest=5&size=20&growth_time=3

DELETE
localhost:8080/api/v1/berry?name=generation-i
localhost:8080/api/v1/berry/all/
