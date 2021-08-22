* A - Atomicity
* C - Consistency
* I - Isolation
* D - Durability

**Problems in/during transactions**
* ***Dirty Read (Deposit)*** - If we get a modified result for a transaction that should not be available 
for another transaction until a transaction is committed.
* ***Non-repeatable Read*** - If we get different results every time we try to read something similar from 
the database.
* ***Phantom Read (Delete or Insert)*** - If we get different number of rows when we execute same query on 
the database.

**Transaction Isolation Levels**
* ***Read Uncommitted*** - Data which is modified by one transaction that should not be available for another 
transaction until that transaction is committed. Dirty Read is the violation if this rule. This rule does not 
solve any of the problems we face during a transaction.

* ***Read Committed*** - No other transaction will be able to read the data only if it is committed by another 
transaction. Because there will be a lock on all the values that is being changed until it is committed. 
We solve the dirty read problem with the read committed isolation level.

* ***Repeatable Read*** - This will lock not just the values during the transaction, but also on all the rows 
we read during a particular transaction. We solve the dirty read and non-repeatable read problems with the read 
committed isolation level.

* ***Serializable*** - We solve all the problems associated with a transaction. Here we have a lock everything 
that matches a certain constraint present in the query. Any other transactions, which is trying to modify data 
that satisfies this constraint will not be allowed since there will be a table lock based on this specific 
constraint.

**NOTE:** Typically the transaction isolation level, which is used by most of the applications is Read Committed. 
It gives you enough guarantees about the quality of data and also ensures that the performance of the system is 
good in a decently consistent way.

**3 things to decide while implementing transaction management**
There are 2 @Transactional annotations. One from Spring Framework and the other from JPA. 

* What is the difference between the two?
1. Assume, in a single transaction you are making a change to 2 databases, and also making a change over an MQ. In this
scenario we need complicated transaction management. @Transactional annotation from JPA will be able to manage
transactions over a single database. However, if you would want to manage transactions across multiple databases and
queues, then the recommended one to use is the Spring transactional annotation.
2. Also, with the Spring version of @Transactional annotation, we can decide the isolation level

