package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Enum.ItemCategory;
import com.fashion.demo.Enum.StockStatus;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.ItemRepository;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.ItemDTO;
import com.fashion.demo.service.ItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.boot.model.naming.ImplicitEntityNameSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fashion.demo.constant.ApplicationConstant.INPUT_NOT_FOUND;

@Service(value = "itemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private static final Logger LOGGER = LogManager.getLogger(ItemServiceImpl.class);

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void addItem(AddItemsReqDTO addItemsReqDTO) {
        LOGGER.info("Execute method addItem ");
        try {
            if(!addItemsReqDTO.equals(null)){
                ItemEntity itemEntity = new ItemEntity();
                itemEntity.setPrice(addItemsReqDTO.getPrice());
                itemEntity.setCategory(addItemsReqDTO.getCategory());
                itemEntity.setImage(addItemsReqDTO.getImage());
                itemEntity.setItem_name(addItemsReqDTO.getItem_name());
                itemEntity.setSize(addItemsReqDTO.getSize());
                itemEntity.setItem_stock(addItemsReqDTO.getItem_stock());
                itemEntity.setAddedDate(new Date());

                if(addItemsReqDTO.getItem_stock()<100 && addItemsReqDTO.getItem_stock()>0){
                    itemEntity.setStock_type(StockStatus.FINAL_STAGE);
                } else if(addItemsReqDTO.getItem_stock()==0){
                    itemEntity.setStock_type(StockStatus.SOLD_OUT);
                }else{
                    itemEntity.setStock_type(StockStatus.AVAILABLE);
                }

                itemRepository.save(itemEntity);
            } else{
                throw new ServiceException (INPUT_NOT_FOUND,"Input Not Found");
            }
        }catch (Exception e) {
            LOGGER.error("addItem : " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<ItemDTO> viewItemsByCategory(String category) {
        LOGGER.info("Execute items by category");
        try {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            List<ItemEntity> itemEntities = itemRepository.findByCategory(category);

            if(itemEntities.isEmpty()){
                 return itemDTOS;
            }
            else{
                for(ItemEntity i : itemEntities ){
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setCategory(i.getCategory());
                    itemDTO.setId(i.getItem_id());
                    itemDTO.setImage(i.getImage());
                    itemDTO.setItem_name(i.getItem_name());
                    itemDTO.setPrice(i.getPrice());
                    itemDTO.setSize(i.getSize());
                    itemDTO.setType(i.getType());

                    itemDTOS.add(itemDTO);
                }
                return itemDTOS;
            }

        }catch (Exception e) {
            LOGGER.error("viewItemsByCategory : " + e.getMessage(), e);
            throw e;
        }
    }
}
