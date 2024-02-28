# VisableCodingChallenge

Description
-------------

1. This messaging application provides APIs where we can send messages and view messages.

    A message is text message sent from user A to user B. Users have unique nick names and are identified by their ids.

2. API with resource path as "/user/account" is used to create a new user account. (we can send a nick name in the json request)

3.  API with resource path as "/chat/message" is used to send message.
   
   we give 'MessageRequest' in json request body. It contains senderId,receiverId and message.

4. API with resource path as "/chat/message/sender/5" is used to view the messages which i sent using my userid (eg : sender id is 5)
   
5.  API with resource path as "/chat/message/receiver/4" s used to view the messages which i received using my userid (eg : receiver id is 4)
   
6.   As a user i can view messages which are received from a particular user with this uri "/message/receiver/{senderId}/{receiverId}" 
     
7. RabbitMQ concurrency is leveraged to enhance the perfomance.
     
     Current implementation makes use of 4 concurrent consumers which can be modified according to the load expected
   
8. Added junit test cases.


Technologies Used
-----------------

1. Java SE 17

2. Spring boot 3.2.3

4. RabbitMQ

5. Spring boot test

Improvements
------------
1. Scope for Docker file or similar to run the application in a container. ( Added a draft file, yet to test it in the local environment)

2. To imporve the latency and throughput,any other kind of asynchronus event processing architectures could be introduced. eg Kafka, LAMAX Disruptor
 
3. Scope for better test coverage

4. Extensive documentation
 
5. Option for better exception handling

 
 Thank you!

