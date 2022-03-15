package tec.dk.demo1.datasource;

import tec.dk.demo1.models.Person;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDB {
    private static int nextId = 1000;
    private static List<Person> personList;
    private static InMemoryDB instance;

    private static int getNextId(){
        return nextId++;
    }

    private InMemoryDB() {
        personList = new ArrayList<>();
        personList.add(new Person(getNextId(), "Johnny Silverspoon", "jsp@aether.com", "He be cool i guess"));
    }

    public static synchronized InMemoryDB getInstance() {
        if(instance == null){
            instance = new InMemoryDB();
        }
        return instance;
    }
    public static List<Person> getAllPersons(){
        return personList;
    }
    public Person getPerson(int personId){
        for (Person person: personList){
            if(person.getId() == personId){
                return person;
            }
        }
        //Failcase if no person exist with that id.
        return null;
    }

    public int updatePerson(Person person){
        for (Person p: personList){
            if(p.getId() == person.getId()){
                personList.set(personList.indexOf(p), person);
                return 1;
            }
        }
        return 0;
    }
    public static int deleteFromPersonList(int personId){
        for(Person p: personList){
            if(p.getId() == personId){
                personList.remove(p);
                return 1;
            }
        }
        return 0;
    }
    public int insertIntoPersonList(Person person){
        person.setId(getNextId());
        personList.add(person);
        return 1;
    }
}
