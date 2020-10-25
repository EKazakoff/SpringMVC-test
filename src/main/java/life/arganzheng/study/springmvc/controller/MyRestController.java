package life.arganzheng.study.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AccountEntityCRUD;
//import service.HelloTextOutput;

@RestController
@RequestMapping("/rest")
public class MyRestController {
/*    @Autowired
    HelloTextOutput hto;*/

    @Autowired
    AccountEntityCRUD accountEntityCRUD;

// TODO: преобразовать метод так что бы он возвращал JSON(см.ниже). Использовать Jackson.
    @GetMapping(path = "/get/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAccount(@RequestParam Integer id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper(); // Jackson ObjectMapper
        //Object to JSON in String
        String jsonInString = mapper.writeValueAsString(accountEntityCRUD.read(id));
        return ResponseEntity.ok().body(jsonInString);
    }

/*
    @GetMapping(path = "/get/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getHellotest() {
        return ResponseEntity.ok().body(hto.sayHello());
    }
*/

    // Handler method to produce text response
    @GetMapping(path = "/get/text", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getText() {
        return ResponseEntity.ok().body("Spring MVC - REST Controller Hello World example.");
    }

    // Handler method to produce JSON response
    @GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getJSON() {
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Fife");

        return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(list);
    }

    // Handler method to produce XML response
    @GetMapping(path = "/get/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getXML() {
        String xml = "<user><id>12</id><name>John</name></user>";
        return ResponseEntity.ok().header("myheader", "myvalue") // add custom headers
                .body(xml);
    }

    // Handler method to consume JSON request and produce text response
    @PostMapping(path = "/post/json",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> postJSON(@RequestBody List<String> body) {
        System.out.println(body);
        // Process request
        // ....
        return ResponseEntity.ok().body("Done");
    }

    // Handler method to consume XML request and produce text response
    @PostMapping(path = "/post/xml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> postJSON(@RequestBody String body) {
        System.out.println(body);
        // Process request
        // ....
        return ResponseEntity.ok().body("Done");
    }
}
