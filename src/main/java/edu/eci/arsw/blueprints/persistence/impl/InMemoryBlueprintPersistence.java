/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>, Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(150, 150), new Point(125, 125)};
        Blueprint bp = new Blueprint("john_doe", "blueprint_one", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

        pts = new Point[]{new Point(20, 30), new Point(30, 20)};
        bp = new Blueprint("alice", "amazingPlan", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

        pts = new Point[]{new Point(18, 22), new Point(20, 22)};
        bp = new Blueprint("alice", "greatPlan", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

        pts = new Point[]{new Point(20, 18), new Point(20, 20)};
        bp = new Blueprint("bob", "averagePlan", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
    }
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }
    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint bp = blueprints.get(new Tuple<>(author, bprintname));
        if (bp == null){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author or this Blueprint name");
        }else {
           return bp;
        }

    }

    @Override
    public Set<Blueprint> getBlueprintByAuthor(String author) throws BlueprintNotFoundException{
        Set<Blueprint> authorBp = new HashSet<>();

        for(Map.Entry<Tuple<String, String>, Blueprint> tupleToSearch : blueprints.entrySet()){
            if (tupleToSearch.getValue().getAuthor().equals(author) ){
                authorBp.add(tupleToSearch.getValue());
            }

        }
        if (authorBp.isEmpty()){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author.");
        }else{
            return authorBp;

        }


    }

    @Override
    public Set<Blueprint> getSpecificBlueprint(String author, String bpName) throws BlueprintNotFoundException{
        Set<Blueprint> authorBp = new HashSet<>();

        for(Map.Entry<Tuple<String, String>, Blueprint> tupleToSearch : blueprints.entrySet()){
            if (tupleToSearch.getValue().getAuthor().equals(author) && tupleToSearch.getValue().getName().equals(bpName) ){
                authorBp.add(tupleToSearch.getValue());
            }
        }
        if (authorBp.isEmpty()){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author.");
        }else{
            return authorBp;

        }

    }
    public void setSpecificBlueprint(String author, String bpname) throws BlueprintNotFoundException {
        ArrayList<Blueprint> authorBp = new ArrayList<>();
        for(Map.Entry<Tuple<String, String>, Blueprint> tupleToSearch : blueprints.entrySet()){
            if (tupleToSearch.getValue().getAuthor().equals(author) && tupleToSearch.getValue().getName().equals(bpname) ){
                authorBp.add(tupleToSearch.getValue());
            }
        }
        if (authorBp.isEmpty()){
            throw new  BlueprintNotFoundException("Not found BluePrint from this author.");
        }else {
            authorBp.get(0).setAuthor(author);
            authorBp.get(0).setAuthor(bpname);
        }

    }

    @Override
    public Set<Blueprint> getAllBlueprint() throws BlueprintNotFoundException{
        Set<Blueprint> allBluePrints = new HashSet<>(blueprints.values());

        if (allBluePrints.isEmpty()){
            throw new BlueprintNotFoundException("Not found BluePrint from this author.");
        }else {
            return allBluePrints;
        }

    }
}
