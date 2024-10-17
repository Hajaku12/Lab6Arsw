/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")

public class BlueprintAPIController {
    @Autowired
    private BlueprintsServices blueprintsServices;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrints() {
        try {
            return new ResponseEntity<>(blueprintsServices.getAllBlueprints(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/{author}",method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrintByAuthor(@PathVariable String author){
        try{
            return  new ResponseEntity<>(blueprintsServices.getBlueprintsByAuthor(author),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> getSpecificBluePrint(@PathVariable String author, @PathVariable String bpname) {

        try{
            return  new ResponseEntity<>(blueprintsServices.getSpecificBlueprint(author,bpname),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addBlueprint(@RequestBody Blueprint blueprint){
        try {
            blueprintsServices.addNewBlueprint(blueprint);
            return new ResponseEntity<>("Created ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Created ", HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<?> setBluePrint(@PathVariable String author, @PathVariable String bpname) {

        try{
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }

}

