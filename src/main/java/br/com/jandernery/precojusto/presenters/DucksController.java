package br.com.jandernery.precojusto.presenters;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ducks")
public class DucksController {

    @RequestMapping("/quack")
    public String quack() {
        return "Quack!";
    }
}
