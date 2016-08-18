package org.lordofthejars.cubedrone;

import org.arquillian.cube.CubeIp;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Arquillian.class)
public class HelloWorldTest {

    public static final int EXPOSED_PORT = 80;
    // Enrich with webdriver configured to connect to remote browser
    @Drone
    WebDriver webDriver;

    // Enrich with helloworld container ip
    @CubeIp(containerName = "helloworld")
    String ip;

    @Test
    public void shouldShowHelloWorld() throws MalformedURLException, InterruptedException {
        // Constructs url that browser should connect
        URL url = new URL("http", ip, EXPOSED_PORT, "/");
        // Typical test using WebDriver
        webDriver.get(url.toString());
        final String message = webDriver.findElement(By.tagName("h1")).getText();
        assertThat(message).isEqualTo("Hello World");
    }

}
