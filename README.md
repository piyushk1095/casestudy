# Priceservice

    This is a Spring Boot application that generates prices for a given accommodation from partners 100 and 200 when requested. The endpoint that outputs the price service supports the one parameter:
      1. accommodationId  


# Requirements
         1. Java 17
         2. Maven

# Setup

    1. Build the project using the following command:
        mvn clean install
    2. Run the application using the following command:
        mvn spring-boot:run
    3. Open a web browser and navigate to http://localhost:8080/prices/{accommodationId}
    4. To run the test case 
        mvn test


# output


            http://localhost:8080/prices/0

            [
                {
                "currency": "EUR",
                "price": "1262.18"
                }
            ]

# Answers 
        • How could a partner with a potentially slow REST interface be integrated?

            Here are some suggestions:
            1. Asynchronous Communication: Consider using asynchronous communication to interact with the slow partner service. This way, your application can send requests to the partner service and continue processing other tasks while waiting for the response. Spring provides support for asynchronous programming through features like DeferredResult, CompletableFuture, or WebFlux (if you prefer a reactive approach).
            2. Connection Timeout and Retry Mechanism: Set an appropriate connection timeout for your REST client when communicating with the slow partner service. If the connection takes longer than the specified timeout, you can handle it gracefully by implementing a retry mechanism. You can use libraries like Spring Retry or implement custom retry logic to resend the request after a certain delay.
            3. Caching: If the data returned by the partner service is relatively static or doesn't change frequently, you can consider implementing a caching mechanism. By caching the responses, you can serve subsequent requests from the cache instead of making repeated requests to the slow partner service. Spring provides caching support through the @Cacheable annotation and caching libraries like Ehcache or Redis.

        • How could your solution scale for multiple thousand requests per second?
            1. Load Balancing: Implement a load balancer to distribute incoming requests across multiple instances of your Spring Boot application. This allows you to horizontally scale your application by adding more instances to handle the increased load. Popular load balancing solutions include Nginx, HAProxy, or cloud-based load balancers offered by cloud providers like AWS Elastic Load Balancer or Azure Load Balancer.
            2. Connection Pooling: Configure connection pooling for your REST client to efficiently manage and reuse connections to the partner service. This helps reduce the overhead of establishing new connections for each request and improves overall performance. Libraries like Apache HttpClient or OkHttp provide connection pooling capabilities that can be integrated into your Spring Boot application.
            3. Auto-Scaling: Consider implementing auto-scaling capabilities that automatically adjust the number of instances based on predefined metrics, such as CPU utilization or request queue length. This allows your application to dynamically scale up or down based on the current demand, ensuring efficient resource utilization.





