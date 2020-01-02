package org.apache.struts.crud.dao;

import org.apache.struts.crud.model.CarModel;
import org.apache.struts.crud.model.Country;
import org.apache.struts.crud.model.CountryEntity;
import org.apache.struts.crud.model.CountryRepository;
import org.apache.struts.crud.model.Person;
import org.apache.struts.crud.model.PersonEntity;
import org.apache.struts.crud.model.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class JDBCPersonDAO implements PersonDao {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Person getPerson(Integer id) {
        return copyPersonPropertiesFromEntity(personRepository.findById(id).get());
    }

    @Override
    public Person[] getAllPersons() {
        long size = personRepository.count();
        Person[] people = new Person[(int) size];
        Iterable<PersonEntity> personEntityIterable = personRepository.findAll();
        Iterator<PersonEntity> personEntityIterator = personEntityIterable.iterator();
        int count =0;
        while (personEntityIterator.hasNext())
        {
            people[count] = copyPersonPropertiesFromEntity(personEntityIterator.next());
            count++;
        }
        return people;
    }

    @Override
    public void updatePerson(Person personBean) {

    }

    @Override
    public void insertPerson(Person personBean) {
        personRepository.save(copyPersonPropertiesToEntity(personBean));
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    private PersonEntity copyPersonPropertiesToEntity(Person person)
    {
        CountryEntity countryEntity = countryRepository.findByCountryId(person.getCountry().getCountryId());
        if(countryEntity==null)
        {
            countryEntity = new CountryEntity();
            countryEntity.setCountryId(person.getCountry().getCountryId());
            countryEntity.setCountryName(person.getCountry().getCountryName());
        }
        List<CarModel> carModels = new ArrayList<>();
        for(String carModelString : person.getCarModels()) {
            CarModel carModel = new CarModel();
            carModel.setModelName(carModelString);
            carModels.add(carModel);
        }



        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(person.getFirstName());
//        personEntity.setCountry( countryEntity);
        personEntity.setEmail(person.getEmail());
        personEntity.setGender(person.getGender());
        personEntity.setLastName(person.getLastName());
        personEntity.setOver21(person.isOver21());
        personEntity.setPhoneNumber(person.getPhoneNumber());
        personEntity.setSport(person.getSport());
//        personEntity.setCarModels(carModels);

        return personEntity;
    }



    private Person copyPersonPropertiesFromEntity(PersonEntity personEntity)
    {
        Person person = new Person();
        person.setFirstName(personEntity.getFirstName());
//        person.setCountry( new Country(personEntity.getCountry().getCountryId(),personEntity.getCountry().getCountryName()));
        person.setEmail(personEntity.getEmail());
        person.setGender(personEntity.getGender());
        person.setLastName(personEntity.getLastName());
        person.setOver21(personEntity.isOver21());
        person.setPersonId(personEntity.getPersonId());
        person.setPhoneNumber(personEntity.getPhoneNumber());
        person.setSport(personEntity.getSport());
//        person.setCarModels(getCarModels(personEntity.getCarModels()));

        return person;
    }

    private String[] getCarModels(List<CarModel> carModels)
    {
        String[] carModelsArray;
        if(carModels!=null && carModels.size()>0)
        {
            carModelsArray = new String [carModels.size()];
            int count=0;
            for(CarModel carModel: carModels)
            {
                carModelsArray[count] = carModel.getModelName();
                count++;
            }
        }
        else {
            carModelsArray = new String [0];
        }

        return carModelsArray;
    }



}
