package com.tolochko.periodicals.model.service.impl;

import com.tolochko.periodicals.model.dao.connection.AbstractConnection;
import com.tolochko.periodicals.model.dao.factory.DaoFactory;
import com.tolochko.periodicals.model.dao.factory.impl.MySqlDaoFactory;
import com.tolochko.periodicals.model.dao.pool.ConnectionPool;
import com.tolochko.periodicals.model.dao.pool.ConnectionPoolProvider;
import com.tolochko.periodicals.model.domain.subscription.Subscription;
import com.tolochko.periodicals.model.service.SubscriptionService;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private static final Logger logger = Logger.getLogger(SubscriptionServiceImpl.class);
    private static final SubscriptionServiceImpl instance = new SubscriptionServiceImpl();
    private DaoFactory factory = MySqlDaoFactory.getFactoryInstance();
    private ConnectionPool pool = ConnectionPoolProvider.getPool();


    private SubscriptionServiceImpl(){}

    public static SubscriptionServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Subscription> findAllByUserId(long id) {
        try (AbstractConnection conn = pool.getConnection()) {
            logger.info("try to findAll subscription by id: " + id);

            return factory.getSubscriptionDao(conn)
                    .findAllByUser(factory.getUserDao(conn).findOneById(id));
        }
    }
}