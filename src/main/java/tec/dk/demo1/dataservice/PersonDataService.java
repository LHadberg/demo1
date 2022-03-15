package tec.dk.demo1.dataservice;

import tec.dk.demo1.datasource.InMemoryDB;
import tec.dk.demo1.datasource.MariaDB;
import tec.dk.demo1.models.Person;
import java.util.List;

public class PersonDataService {
    public Person getPerson(int personId) {
        return InMemoryDB.getInstance().getPerson(personId);
    }

    public List<Person> getAllPersons() {
        MariaDB dbHandler = new MariaDB();
        return InMemoryDB.getInstance().getAllPersons();
    }

    public int addPerson(Person person) {
        return InMemoryDB.getInstance().insertIntoPersonList(person);
    }

    public int updatePerson(Person person) {
        return InMemoryDB.getInstance().updatePerson(person);
    }

    public int deletePerson(int personId) {
        return InMemoryDB.deleteFromPersonList(personId);
    }
}