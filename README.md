# gravahal
Test project to demonstrate my coding skills

This project uses:
- Spring Boot Framework (core, web, mvc)
- JSP
- JUnit Framework
- Mockito

As future features I see:
- complete coverage with unit tests
- solve multi-threading issues
- separate front-end client from back-end server-side and form it as pure HTML/JS/CSS page
- use RESTful web-services and request/poll to them using JSON as DTO objects
- move GameSession from singleton scope, use session or prototype instead.
It will let to provide parallel game sessions for multiple pairs of players.

After starting, this application will public such web-services:
Get current game state (show game board):

http://localhost:8080/gravahal/game

Make next turn:

http://localhost:8080/gravahal/turn?pitIndex=<integer_pit_index_to_pick_from>
