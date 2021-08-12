package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.OderEntity;
import com.fashion.demo.Entity.UserEntity;
import com.fashion.demo.Enum.OrderStatus;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.OrderRepository;
import com.fashion.demo.Repository.UserRepository;
import com.fashion.demo.dto.Order.OrderDTO;
import com.fashion.demo.service.OderService;
import org.apache.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.Optional;

import static com.fashion.demo.constant.ApplicationConstant.INPUT_NOT_FOUND;
import static com.fashion.demo.constant.ApplicationConstant.UNAUTHORIZED;

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
                //find pending orders of user
                Optional<OderEntity> pending_order = orderRepository.findOrderByUserId(user_id);
                if(pending_order.isPresent()){
                    throw new ServiceException(UNAUTHORIZED,"Complete pending orders");
                }
                else{
                    OderEntity oderEntity = new OderEntity();
                    oderEntity.setOrderedDate(new Date());
                    oderEntity.setUserEntity(user.get());
                    oderEntity.setOrderStatus(OrderStatus.PENDING);
                    orderRepository.save(oderEntity);
                }
            }
             else{
                throw new ServiceException(INPUT_NOT_FOUND,"User is Not Authorized!");
            }
        }catch (Exception e) {
            LOGGER.error("create New Order : " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void addItemsToOrder(long user_id) {

    }

    @Override
    public OrderDTO getPendingOrder(long user_id) {
        LOGGER.info("Execute method getPendingOrder ");
        try {
            //find user
            Optional<UserEntity> user = userRepository.findById(user_id);
            if(user.isPresent()) {
                //find pending orders of user
                Optional<OderEntity> pending_order = orderRepository.findOrderByUserId(user_id);

                OrderDTO pending = new OrderDTO();
                OderEntity entity = pending_order.get();

                pending.setOrder_id(entity.getOrder_id());
                pending.setOrderedDate(entity.getOrderedDate());
                pending.setOrderStatus(entity.getOrderStatus());
                pending.setTotalPrice(entity.getTotalPrice());
                pending.setUser_id(user_id);

                return pending;
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
