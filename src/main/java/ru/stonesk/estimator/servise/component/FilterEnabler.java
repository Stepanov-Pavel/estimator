package ru.stonesk.estimator.servise.component;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Assistant for enabling and disabling filters for selecting entities marked for deletion
 */
@Component
@AllArgsConstructor
public class FilterEnabler {

    private final EntityManager entityManager;

    /**
     * Enabling filter for selecting entities marked for deletion by filter name
     *
     * @param filterName string containing name by which you want to enable filter
     * @param isDeleted  accepts TRUE or FALSE depends on you want selecting marked entities or not
     */
    public void enable(String filterName, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(filterName);
        filter.setParameter("isDeleted", isDeleted);
    }

    /**
     * Disabling filter for selecting entities marked for deletion by filter name
     *
     * @param filterName string containing name by which you want to disable filter
     */
    public void disable(String filterName) {
        Session session = entityManager.unwrap(Session.class);
        session.disableFilter(filterName);
    }
}