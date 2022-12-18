# KLibrary

## About The Project

<img align="right" src="https://user-images.githubusercontent.com/88390464/200192182-7f87b55a-0197-4b84-8f68-564b83a06920.png" height="200" width="200" alt="logo">

This is just a small collection of classes that will help you deal with <br> 
- [system paths](src/main/java/KLibrary/utils/SystemUtils.java)
- [databases](src/main/java/KLibrary/utils/SQLUtils.java)
- [sockets](src/main/java/KLibrary/wrapper/SocketWrapper.java) and [server sockets](src/main/java/KLibrary/wrapper/ServerSocketWrapper.java)
- classes of the North Rhine-Westphalia government
  - [List](src/main/java/KLibrary/wrapper/AbiturKlassenUtils/AbiListWrapper.java)
  - [Graphs](src/main/java/KLibrary/utils/AbiturKlassenUtils/GraphHandler.java)
- [encryption](src/main/java/KLibrary/utils/EncryptionUtils.java)
  - OTP
  - RSA
  - AES
  - Hashing

## Getting Started

Add the [JAR](out/artifacts/KLibrary_jar) to your project as shown [here](https://stackoverflow.com/questions/1051640/correct-way-to-add-external-jars-lib-jar-to-an-intellij-idea-project). That's it. Now you're able to import all classes.

Last stable version: **1.2.0**

### ServerSocketWrapper

Create a new class that inherits from [ServerSocketWrapper](src/main/java/KLibrary/wrapper/ServerSocketWrapper.java) in order to implement it's 
abstract methods: 

- ```onClientConnect``` - Triggered when a client connects to the server and (if necessary) finished a key handshake for encryption 
- ```onClientDisonnect``` - Triggered when the connection to a client is lost 
- ```onMessage``` - Triggered when the server receives a message from a client

Call ```acceptSockets()``` to start listening for sockets.

### SocketWrapper

A [SocketWrapper](src/main/java/KLibrary/wrapper/SocketWrapper.java) provides methods for sending/receiving encrypted (and unencrypted) messages.
You can connect it via one of the four constructors:
- ```SocketWrapper(Socket, SecretKey)``` - Takes an already connected socket and secret key shared with the server to encrypt/decrypt
- ```SocketWrapper(Socket)``` - Takes an already connected socket. You can only send unencrypted messages until you set a secret key (```setAESKey()```) or perform a key handshake with the server (```establishAES()```)
- ```SocketWrapper(String, int, SecretKey)``` - Takes a server address, port, and secret key shared with the server to encrypt/decrypt
- ```SocketWrapper(String, int)``` - Takes a server address and port. You can only send unencrypted messages until you set a secret key (```setAESKey()```) or perform a key handshake with the server (```establishAES()```)

### SQLUtils

### SortUtils

### SearchUtils

### EncryptionUtils

### SystemUtils
