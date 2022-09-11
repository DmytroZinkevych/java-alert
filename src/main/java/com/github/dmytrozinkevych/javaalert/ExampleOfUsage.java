package com.github.dmytrozinkevych.javaalert;

import static com.github.dmytrozinkevych.javaalert.JavaAlert.alert;

public class ExampleOfUsage {
    public static void main(String[] args) {
        System.err.println("===================== Program started =====================");
        alert("Hello, alert! Hello, alert! Hello, alert! Hello, alert!\nHello, alert! Hello, alert! Hello, alert!");
        System.err.println("===================== Program finished =====================");
    }
}
