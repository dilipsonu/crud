package org.apache.struts.crud.controller;

import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.service.DefaultPersonService;
import org.apache.struts.crud.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/persons",produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    PersonService personService = new DefaultPersonService();

    @RequestMapping(method = {RequestMethod.GET})
    public ResponseEntity<Person[]> persons()
    {
        return new ResponseEntity(personService.getAllPersons(), HttpStatus.OK);
    }
}
