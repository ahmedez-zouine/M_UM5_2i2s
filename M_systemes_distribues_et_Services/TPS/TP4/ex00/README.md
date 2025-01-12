This implementation provides a complete chat system with authentication and private chat rooms. Here's how it works:

Authentication:

Users' credentials are stored in a **credentials.txt** file in format 
```
Save Password Like:

username:password


EX:
Azouine:passWord

```
Each client must authenticate before joining a chat room


Chat Rooms:

Users can join rooms using the command 

```
/join roomname

```
Only users in the same room can see each other's messages
Messages in each room are saved to separate files in the messages directory


Message Persistence:

All messages are saved to files named **roomname.txt** in the messages directory
When a user joins a room, they see the previous messages



To use the system:

Create a credentials.txt file with user credentials in the format:

```
Copyuser1:password1
user2:password2

# Start the server:

javac Server.java
java Server

# Start multiple clients:

javac Client.java
java Client

```
For each client:

Enter username and password when prompted
Use **/join roomname to join a chat room**

Start chatting!



The system provides:

- Secure authentication
- Private chat rooms
- Message persistence
- Multiple concurrent users
- Clean separation of concerns

