# Java Alert

Emulating JavaScript alert() popup with suspending the current thread execution. Made for easier debugging and just for fun 😄

<img width="512" alt="Screenshot" src="https://user-images.githubusercontent.com/24415165/194646735-9ad2b31e-82da-4fa2-a2c8-113ac80afb6f.png">

The best way of using Java Alert is to statically import `alert()` method from the `JavaAlert` class

```java
import static io.github.dmytrozinkevych.javaalert.JavaAlert.alert;
```

and then simply call `alert()` method with your messsage as an argument 

```java
alert("Your message");
```
