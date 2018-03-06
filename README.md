## Important note

spring-cloud-stream requires spring-boot-actuator. 😡

```
	compile('org.springframework.cloud:spring-cloud-stream')
	compile('org.springframework.cloud:spring-cloud-stream-binder-kafka')
	compile("org.springframework.boot:spring-boot-starter-actuator")
```

