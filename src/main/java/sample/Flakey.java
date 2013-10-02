package sample;


import org.apache.camel.Body;

public class Flakey {
    static int count = 0;
    public String enhance(@Body String msg) {
        count++;
        if(count % 4 == 0) {
            throw new Error("Whoops!");
        }

        return "Enhanced -->" + msg;
    }
}
