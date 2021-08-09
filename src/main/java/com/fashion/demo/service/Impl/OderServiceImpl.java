package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.OderEntity;
import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Enum.OrderStatus;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.OrderRepository;
import com.fashion.demo.Repository.UserRepository;
import com.fashion.demo.service.OderService;
import org.apache.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static com.fashion.demo.constant.ApplicationConstant.INPUT_NOT_FOUND;

@Service(value = "orderService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OderServiceImpl implements OderService {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(OderServiceImpl.class);
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public OderServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void createNewOrder(long user_id) {
        LOGGER.info("Execute method getAllUsers ");
        try {
            //find user
            Optional<UserEntity> user = userRepository.findById(user_id);
            if(user.isPresent()){
                OderEntity oderEntity = new OderEntity();
                oderEntity.setOrderedDate(new Date());
                oderEntity.setUserEntity(user.get());
                oderEntity.setOrderStatus(OrderStatus.PENDING);
                orderRepository.save(oderEntity);
            }
             else{
                throw new ServiceException(INPUT_NOT_FOUND,"User is Not Authorized!");
            }
        }catch (Exception e) {
            LOGGER.error("create New Order : " + e.getMessage(), e);
            throw e;
        }
    }
}
