DISCLAIMER :
I do not own and IDE, all the code written here is written using Notepad++ (might contain minor syntax errors)

The code is written in Java

============================================================================================

Vault_Dragon REST API Quiz - Coding Test Requirement

Build a version controlled key-value store with a HTTP API we can query that from. The API needs to be able to:

Accept a key(string) and value(some json blob/string) {"key" : "value"} and store them. If an existing key is sent, the value should be updated

Accept a key and return the corresponding latest value

When given a key AND a timestamp, return whatever the value of the key at the time was.

Assume only GET and POST requests for simplicity.

Example:

Method: POST
Endpoint: /object
Body: JSON: {mykey : value1}
Time: 6pm

Method: GET
Endpoint: /object/mykey
Response: value1

Method: POST
Endpoint: /object
Body: JSON: {mykey : value2}
Time: 6.05 pm

Method: GET
Endpoint: /object/mykey
Response: value2

Method: GET
Endpoint: /object/mykey?timestamp=1440568980 [6.03pm]
Response: value1

All timestamps are unix timestamps according UTC timezone.

Guidelines:

Use NodeJS for scripting the backend.

Deploy this to a server and give us the URL

Push the code to a public git repository and give us the URL.

You are free to use any open source database.

A good submission will have the following characteristics:

Clean and extendable code

Handle a wide variety of edge cases

Not break under a reasonable load

We are NOT judging for algorithmic brilliance or cool one liners. We want to see if you can write production quality code. So please build this assuming you are building a production quality service.