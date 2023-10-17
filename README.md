# comp416-project1

Game Description

The game application is named Hot and Cold and is played by two players.
Player 1 will be the server and player 2 will be the client. Player 1, as
a server, opens a welcoming socket and waits for the client to connect. After
the client connects, the server will generate two random values, x and y. Each
random value will be between 0 and 255, inclusive. The server will ask player 1
to enter his name and then send a message to the client to enter his name as
well. The user at the server, player one, will enter a guess value for x and y and
then send a message to the client (player 2) to guess x and y using sockets. The
server then calculates the distances between the randomly generated values and
the guesses and adds one point to the player with the closest guess. The game is
played for three rounds and at the end, the server announces the winner and
closes the connection.
