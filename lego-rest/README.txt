

/*****************  REST Controller for Category    ***********************/
@author Tobias Kamenicky <tobias.kamenicky@gmail.com>


* Create category with given parametres
*
* example:
* curl -X POST -H "Accept:application/json" -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/categories/create
* -d '{"name":"Star Wars","description":"Category for star wars models."}'
* | python -m json.tool


* Update Category with given id.
*
* example:
* curl -X PUT -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/categories/1
* -d '{"name":"Cars","description":"Category for all cars."}'


* Delete Category defined by its id
*
* example:
* curl -X DELETE http://localhost:8080/pa165/rest/categories/1


* get Category defined by its id. If identifier is not numeric it tries to find Category by its name.
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/categories/1 | python -m json.tool

* get all categories from the system
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/categories | python -m json.tool





/**********************  REST Controller for Lego sets.  *********************************/
@author Ondrej Velisek <ondrejvelisek@gmail.com>


* Create legoSet with given parametres
*
* example:
* curl -X POST -H "Accept:application/json" -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/lego-sets/create
* -d '{"name":"Bionicle","price":120,"categoryId":1}'
* | python -m json.tool


* Update LegoSet with given id.
*
* example:
* curl -X PUT -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/lego-sets/1
* -d '{"name":"Dark side","price":150,"categoryId":2}'


* add model with given modelId to legoset defined by id
*
* example:
* curl -X PUT http://localhost:8080/pa165/rest/lego-sets/1/addModel?modelId=1


* remove model with given modelId from legoset defined by id
*
* example:
* curl -X PUT http://localhost:8080/pa165/rest/lego-sets/1/removeModel?modelId=1


* Delete LegoSet defined by its id
*
* example:
* curl -X DELETE http://localhost:8080/pa165/rest/lego-sets/1


* get LegoSet defined by its id. If identifier is not numeric it tries to find LegoSet by its name.
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/lego-sets/1 | python -m json.tool


* get all legosets from the system
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/lego-sets | python -m json.tool





/****************************  REST Controller for Models.  *****************************/
@author Marek Abaffy <abaffy.m@gmail.com>


* Create model with given parameters.
*
* example:
* curl -X POST -H "Accept:application/json" -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/models/create
* -d '{"name":"House","price":100, "ageLimit": 12, "categoryId":2}'
* | python -m json.tool


* Update model with given id.
*
* example:
* curl -X PUT -H "Content-Type:application/json"
* http://localhost:8080/pa165/rest/models/1
* -d '{"name":"House","price":120, "ageLimit":90, "categoryId":2}'


* Delete model defined by its id.
*
* example:
* curl -X DELETE http://localhost:8080/pa165/rest/models/1


* Get model by id.
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/models/1
* Get all models.
*
* example:
* curl -H "Accept:application/json" http://localhost:8080/pa165/rest/models






/**************************  REST Controller for PieceTypes.  ***************************************/
@author Sona Mastrakova <sona.mastrakova@gmail.com>


* Create pieceType with given parameters
*
* example: curl -X POST -H "Accept:application/json" -H
* "Content-Type:application/json"
* http://localhost:8080/pa165/rest/piecetypes/create -d '{ "name": "Arm",
* "colors": [ "YELLOW", "GREEN" ] }' | python -m json.tool


* Update pieceType with given parameters
*
* example: curl -X PUT -H "Accept:application/json" -H
* "Content-Type:application/json"
* http://localhost:8080/pa165/rest/piecetypes/7 -d '{ "name": "Arm",
* "colors": [ "YELLOW" ] }' | python -m json.tool


* Delete pieceType defined by its id
*
* example: curl -X DELETE http://localhost:8080/pa165/rest/piecetypes/7


* Get pieceType defined by its id. If identifier is not numeric it tries to
* find LegoSet by its name.
*
* example: curl -H "Accept:application/json"
* http://localhost:8080/pa165/rest/piecetypes/1 | python -m json.tool


* Get all pieceTypes from the system
*
* example: curl -H "Accept:application/json"
* http://localhost:8080/pa165/rest/piecetypes | python -m json.tool