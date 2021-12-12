package com.example.weather.dao;

import com.example.weather.model.WeatherCity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class WeatherCityDaoImpl implements WeatherCityDao {

    private final SessionFactory sessionFactory;

    public WeatherCityDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(WeatherCity weatherCity) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(weatherCity);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(WeatherCity weatherCity) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                WeatherCity wc = session.get(WeatherCity.class, weatherCity.getCityName());
                wc.setWeatherCity(weatherCity);
                session.update(wc);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public WeatherCity get(String city) throws PersistenceException {
        WeatherCity weatherCity;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                weatherCity = session.createQuery(
                        "from WeatherCity as city where city.cityName='"+city+"'", WeatherCity.class
                ).getSingleResult();
                transaction.commit();
            } catch (PersistenceException e) {
                if (transaction != null) transaction.rollback();
                throw e;
            }
        }

        return weatherCity;
    }

    @Override
    public List<WeatherCity> getAll() {
        List<WeatherCity> weatherCity;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                weatherCity = session.createQuery("FROM WeatherCity", WeatherCity.class).list();
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) transaction.rollback();
                throw e;
            }
        }

        return weatherCity;
    }
}
