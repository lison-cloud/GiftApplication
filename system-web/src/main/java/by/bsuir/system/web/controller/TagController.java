package by.bsuir.system.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import by.bsuir.system.dto.Tag;
import by.bsuir.system.dto.TagRequest;
import by.bsuir.system.service.TagService;
import by.bsuir.system.web.controller.exception.TagNotFound;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> tags() {
        return this.tagService.getAll();
    }

    @GetMapping("/{id:\\d+}")
    public Tag tag(@PathVariable long id) {
        return this.tagService.get(id).orElseThrow(TagNotFound::new);
    }

    @GetMapping("/{name:[a-zA-Z]+}")
    public Tag tag(@PathVariable String name) {
        return this.tagService.getByName(name).orElseThrow(TagNotFound::new);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Tag> saveTag(@RequestBody TagRequest tagRequest, UriComponentsBuilder ucb) {
        Tag savedTag = this.tagService.save(tagRequest).orElseThrow(TagNotFound::new);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/tag/")
                .path(String.valueOf(savedTag.getName()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<Tag> entity = new ResponseEntity<>(savedTag, headers, HttpStatus.CREATED);
        return entity;
    }

}
