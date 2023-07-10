# Java Chat System: Server and Client

This repository contains two Java classes, `TCPServer` and `TCPClient`, that together form a basic chat system implemented using socket programming.

## TCPServer

This class represents the server-side of the chat system. It listens for incoming connections from clients and handles message exchange between them. The server processes client requests, capitalizes the received messages, and sends them back to the clients. It gracefully manages disconnections and allows clients to quit the chat session.

## TCPClient

This class represents the client-side of the chat system. It enables users to connect to the server by providing the server's IP address. Once connected, users can send messages to the server and receive capitalized responses. The client provides a command-line interface for interaction and ensures a smooth real-time conversation.

Together, these classes provide the foundation for a simple chat system built with Java. You can further enhance and customize the functionality to suit your requirements or use it as a starting point for developing more advanced chat applications.

## Usage

1. Compile the Java source files.
2. Start the server by running the `TCPServer` class.
3. Launch the client application by running the `TCPClient` class.
4. Write the server IP in the client terminal.
5. Type "CONNECT" in the client terminal. -This will establish the connection.

Feel free to explore, modify, and experiment with this Java Chat System. Share your improvements and contribute to the development of this repository.
