# java-ee-parallel-and-concurrency


Requires

* Java 8+
* Maven 3+

Test 

```
mvn test
```

Run

```
mvn wildfly:run
```

[http://localhost:8080](http://localhost:8080)



Stress Test

```
time curl -s "http://localhost:8080/EntityManagerServlet?[1-1000]"
```



References

* [java-ee-concurrency-api-tutorial](https://www.javacodegeeks.com/2014/07/java-ee-concurrency-api-tutorial.html)

* [injection-of-entitymanager](http://tomee.apache.org/examples-trunk/injection-of-entitymanager/README.html)

* [javaee7-samples](github.com/javaee-samples/javaee7-samples)