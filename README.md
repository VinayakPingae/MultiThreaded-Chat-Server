# MultiThreaded-Chat-Server


Plagarism Notice: Do not copy paste. Please Refer.

Multithreaded client-server application

CS542 Design Patterns

Spring 2015

PROJECT ASSIGNMENT NUMBER 02 README FILE

Due Date: Tuesday, March 9 2015

Submission Date: Tuesday, March 9 2015


Design Server-side code that opens a socket connection on the localhost. The port number for the server should be read from the command line. The server-code should accept connections on the socket. Each connection should be handled by a new thread. This ServerWorker thread should read the following from the socket: the client name and an integer value. The server should check if the integer value is greater than a threshold "THRESHOLD". The "THRESHOLD" value should be defined as a constant, 3, in the Server code. The client input could be in the following format, or any other format that you choose:
