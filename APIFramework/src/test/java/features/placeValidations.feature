Feature: Validating Place API's

Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add place payLoad with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "Post" http request
Then The API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify palce_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
   |name    |language   |address         |
   |Laura   |English    |45 Rosanna Rd   |
#  |Sujitha |Spanish    |68 Ferndale Road|
