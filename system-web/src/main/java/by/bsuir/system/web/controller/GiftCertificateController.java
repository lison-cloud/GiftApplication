package by.bsuir.system.web.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import by.bsuir.system.dto.GiftCertificate;
import by.bsuir.system.dto.GiftCertificateRequest;
import by.bsuir.system.service.GiftCertificateService;
import by.bsuir.system.web.controller.exception.GiftCertificateNotFoundException;

@RestController
@RequestMapping("/certificate")
public class GiftCertificateController {

    private GiftCertificateService gcService;

    @Autowired
    public GiftCertificateController(GiftCertificateService gcService) {
        this.gcService = gcService;
    }

    @GetMapping
    public List<GiftCertificate> giftCertificates() {
        return this.gcService.getAll();
    }

    @GetMapping("/{id:\\d+}")
    public GiftCertificate giftCertificate(@PathVariable long id) {
        return this.gcService.get(id).orElseThrow(GiftCertificateNotFoundException::new);
    }

    @GetMapping("/{name:[a-zA-Z]+}")
    public GiftCertificate tag(@PathVariable String name) {
        return this.gcService.getByName(name).orElseThrow(GiftCertificateNotFoundException::new);
    }

    @PatchMapping("/{id:\\d+}")
    public ResponseEntity<GiftCertificate> updCertificate(@PathVariable long id,
            @RequestBody GiftCertificateRequest giftCertificateRequest, UriComponentsBuilder ucb) {
        GiftCertificate updatedGc = this.gcService.update(giftCertificateRequest, id)
                .orElseThrow(GiftCertificateNotFoundException::new);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/certificate/")
                .path(String.valueOf(updatedGc.getName()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<GiftCertificate> entity = new ResponseEntity<>(updatedGc, headers, HttpStatus.OK);
        return entity;
    }

    @DeleteMapping(value = "/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteGiftCertificate(@PathVariable long id) {
        this.gcService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiftCertificate> saveTag(@RequestBody GiftCertificateRequest giftCertificateRequest,
            UriComponentsBuilder ucb) {
        GiftCertificate savedGc = this.gcService.save(giftCertificateRequest)
                .orElseThrow(GiftCertificateNotFoundException::new);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/certificate/")
                .path(String.valueOf(savedGc.getName()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        ResponseEntity<GiftCertificate> entity = new ResponseEntity<>(savedGc, headers, HttpStatus.CREATED);
        return entity;
    }
}
