package tec.dk.demo1.dataservice;

import tec.dk.demo1.datasource.MariaDB;
import tec.dk.demo1.models.Person;
import java.util.List;

public class PersonDataService {
    public Person getPerson(int personId) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.getPerson(personId);
    }

    public List<Person> getAllPersons() {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.getAllPersons();
    }

    public int addPerson(Person person){
        MariaDB dbHandler = new MariaDB();
        return dbHandler.postPerson(person);
    }

    public int updatePerson(Person person) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.putPerson(person);
    }

    public int deletePerson(int personId) {
        MariaDB dbHandler = new MariaDB();
        return dbHandler.deletePerson(personId);
    }
}