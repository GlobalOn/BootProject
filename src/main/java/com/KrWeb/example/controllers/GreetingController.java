package com.KrWeb.example.controllers;

import com.KrWeb.example.Repository.MessageRepository;
import com.KrWeb.example.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class GreetingController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String home(@RequestParam(name="name", required = false, defaultValue = "New User") String name,
                       Map<String, Object> model)
    {
        model.put("name", name);
        return "HomePage";
    }

    @GetMapping("/Home")
    public String get(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "MainPage";
    }

    @PostMapping("/Home")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "MainPage";
    }

    @GetMapping("deleteAll")
    public String deleteAll(Map<String, Object> model) {
      messageRepository.deleteAll();
          return "MainPage";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else
            messages = messageRepository.findAll();
        model.put("messages", messages);
        return "MainPage";
    }
}