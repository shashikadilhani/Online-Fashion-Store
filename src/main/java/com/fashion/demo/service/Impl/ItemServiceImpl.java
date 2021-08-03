package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.StockStatus;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.ItemRepository;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.DistinctItemDTO;
import com.fashion.demo.dto.item.ItemDTO;
import com.fashion.demo.service.ItemService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
//                itemEntity.setItem_stock(addItemsReqDTO.getItem_stock());
                itemEntity.setAddedDate(new Date());

//                if(addItemsReqDTO.getItem_stock()<100 && addItemsReqDTO.getItem_stock()>0){
//                    itemEntity.setStock_type(StockStatus.FINAL_STAGE);
//                } else if(addItemsReqDTO.getItem_stock()==0){
//                    itemEntity.setStock_type(StockStatus.SOLD_OUT);
//                }else{
//                    itemEntity.setStock_type(StockStatus.AVAILABLE);
//                }

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
    public List<ItemType> viewItemTypesByCategory(String category) {
        LOGGER.info("Execute items by category");
        try {
            List<ItemType> itemTypes = new ArrayList<>();
            List<ItemType> itemEntities = itemRepository.findItemTypesByCategory(category);
            return itemEntities;
        }catch (Exception e) {
            LOGGER.error("viewItemsByCategory : " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<DistinctItemDTO> viewItemsByType(String type, String category) {
        LOGGER.info("Execute items by item Type");
        try {
            List<DistinctItemDTO> itemDTOS = new ArrayList<>();
            List<String> itemEntities = itemRepository.findItemsByTypeAndCategory(type,category);

            if(itemEntities.isEmpty()){
                 return itemDTOS;
            }
            else{
                for(String obj : itemEntities){
                    DistinctItemDTO itemDTO = new DistinctItemDTO();
                    String[] x = obj.split(",");
                    itemDTO.setImage(x[3]);
                    itemDTO.setItem_name(x[2]);
                    itemDTO.setSerial_no(Integer.parseInt(x[1]));
                    itemDTO.setId(x[0]);
                    itemDTOS.add(itemDTO);
                }
                return itemDTOS;
            }

        }catch (Exception e) {
            LOGGER.error("viewItemsByItemTYpe : " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<ItemDTO> viewItemsBySerialNo(int serial_no) {
        LOGGER.info("Execute get items by serial no");
        try {
            List<ItemDTO> itemDTOS = new ArrayList<>();
            List<ItemEntity> itemEntities = itemRepository.findItemTypesBySerial(serial_no);
            if(!itemEntities.isEmpty()){
                for(ItemEntity e : itemEntities){
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setType(e.getType());
                    itemDTO.setSize(e.getSize());
                    itemDTO.setPrice(e.getPrice());
                    itemDTO.setItem_name(e.getItem_name());
                    itemDTO.setId(e.getItem_id());
                    itemDTO.setImage(e.getImage());
                    itemDTO.setCategory(e.getCategory());
                    itemDTO.setSerial_no(serial_no);
                    itemDTOS.add(itemDTO);
                }
                return itemDTOS;
            } else{
                return itemDTOS;
            }
        }catch (Exception e) {
            LOGGER.error("get items by serial no : " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<String> viewItemsSizes(int serial_no) {
        LOGGER.info("Execute get item sizes");
        try {
            List<String> itemsizes = new ArrayList<>();
            List<ItemEntity> itemEntities = itemRepository.findItemTypesBySerial(serial_no);
            if (!itemEntities.isEmpty()) {
                for (ItemEntity e : itemEntities) {
                    String size = e.getSize().toString();
                    itemsizes.add(size);
                }
                return itemsizes;
            } else {
                return itemsizes;
            }
        } catch (Exception e) {
            LOGGER.error("get item sizes : " + e.getMessage(), e);
            throw e;
        }
    }
}
