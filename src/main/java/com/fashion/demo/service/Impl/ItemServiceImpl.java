package com.fashion.demo.service.Impl;

import com.fashion.demo.Entity.ItemEntity;
import com.fashion.demo.Entity.ItemStockEntity;
import com.fashion.demo.Enum.ItemType;
import com.fashion.demo.Enum.StockStatus;
import com.fashion.demo.Exception.ServiceException;
import com.fashion.demo.Repository.ItemRepository;
import com.fashion.demo.Repository.ItemStockRepository;
import com.fashion.demo.dto.item.AddItemsReqDTO;
import com.fashion.demo.dto.item.DistinctItemDTO;
import com.fashion.demo.dto.item.ItemDTO;
import com.fashion.demo.service.ItemService;
import com.fashion.demo.util.ImageSaveAndUpdate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.fashion.demo.constant.ApplicationConstant.INPUT_NOT_FOUND;
import static com.fashion.demo.constant.ApplicationConstant.ITEM_ICON_IMG;

@Service(value = "itemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemStockRepository itemStockRepository;
    private final ImageSaveAndUpdate imageSaveAndUpdate;
    private static final Logger LOGGER = LogManager.getLogger(ItemServiceImpl.class);

    public ItemServiceImpl(ItemRepository itemRepository, ItemStockRepository itemStockRepository, ImageSaveAndUpdate imageSaveAndUpdate) {
        this.itemRepository = itemRepository;
        this.itemStockRepository = itemStockRepository;
        this.imageSaveAndUpdate = imageSaveAndUpdate;
    }

    @Override
    public void addItem(AddItemsReqDTO addItemsReqDTO) {
        LOGGER.info("Execute method addItem ");
        try {
            if(!addItemsReqDTO.equals(null)){

                Optional<ItemEntity> item = itemRepository.findById(addItemsReqDTO.getItem_id());

                //add as a new item=============================================================
                if(!item.isPresent()){
                    ItemEntity itemEntity = new ItemEntity();
                    itemEntity.setPrice(addItemsReqDTO.getPrice());
                    itemEntity.setCategory(addItemsReqDTO.getCategory());
                    itemEntity.setItem_name(addItemsReqDTO.getItem_name());
                    itemEntity.setSize(addItemsReqDTO.getSize());
                    itemEntity.setItem_name(addItemsReqDTO.getItem_name());
                    itemEntity.setType(addItemsReqDTO.getType());
                    itemEntity.setItem_serial_no(addItemsReqDTO.getItem_serial_no());

                    //create stock entity
                    ItemStockEntity stockEntity = new ItemStockEntity();
                    stockEntity.setItem_stock_count(addItemsReqDTO.getItem_stock_count());
                    stockEntity.setItemEntity(itemEntity);

                    if(addItemsReqDTO.getItem_stock_count()>100){
                        stockEntity.setStock_type(StockStatus.AVAILABLE);
                    } if(addItemsReqDTO.getItem_stock_count()<100 && addItemsReqDTO.getItem_stock_count()>0){
                        stockEntity.setStock_type(StockStatus.FINAL_STAGE);
                    } else{
                        stockEntity.setStock_type(StockStatus.SOLD_OUT);
                    }

                    //image
                    itemEntity.setImage(imageSaveAndUpdate.saveOrUpdateImage(addItemsReqDTO.getImage(),ITEM_ICON_IMG));
                    itemStockRepository.save(stockEntity);
                    itemRepository.save(itemEntity);

                }
                //Update Exsisting Item=============================================================
                else{
                    item.get().setItem_serial_no(addItemsReqDTO.getItem_serial_no());
                    item.get().setType(addItemsReqDTO.getType());
                    item.get().setItem_name(addItemsReqDTO.getItem_name());
                    item.get().setAddedDate(new Date());
                    item.get().setSize(addItemsReqDTO.getSize());
                    item.get().setPrice(addItemsReqDTO.getPrice());
                    item.get().setCategory(addItemsReqDTO.getCategory());

                    //check stock
                    Optional<ItemStockEntity> itemStockEntity = itemStockRepository.findByItemId(addItemsReqDTO.getItem_id());

                    //update existing stock entity
                    if(itemStockEntity.isPresent()){

                        //update count
                        int existing_count = itemStockEntity.get().getItem_stock_count();
                        int new_count = existing_count + addItemsReqDTO.getItem_stock_count();
                        itemStockEntity.get().setItem_stock_count(new_count);

                        //update stock type
                        if(new_count>100){
                            itemStockEntity.get().setStock_type(StockStatus.AVAILABLE);
                        } if(new_count<100 && new_count>50){
                            itemStockEntity.get().setStock_type(StockStatus.FINAL_STAGE);
                        }else{
                            itemStockEntity.get().setStock_type(StockStatus.SOLD_OUT);
                        }
                        itemStockRepository.save(itemStockEntity.get());
                    }

                    //create new stock entity
                    else{
                      // create new item stock entity
                      ItemStockEntity newStock = new ItemStockEntity();
                      newStock.setItemEntity(item.get());
                      newStock.setItem_stock_count(addItemsReqDTO.getItem_stock_count());
                        if(addItemsReqDTO.getItem_stock_count()>100){
                            newStock.setStock_type(StockStatus.AVAILABLE);
                        } if(addItemsReqDTO.getItem_stock_count()<100 && addItemsReqDTO.getItem_stock_count()>50){
                            newStock.setStock_type(StockStatus.FINAL_STAGE);
                        }else{
                            newStock.setStock_type(StockStatus.SOLD_OUT);
                        }
                        itemStockRepository.save(newStock);
                    }

                    //set image
                    item.get().setImage(imageSaveAndUpdate.saveOrUpdateImage(addItemsReqDTO.getImage(), ITEM_ICON_IMG));
                    itemRepository.save(item.get());
                }
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

    @Override
    public ItemDTO viewItemDetails(long id) {
        LOGGER.info("Execute get item details");
        try {
            ItemDTO itemDetails = new ItemDTO();
            Optional<ItemEntity> itemEntities = itemRepository.findById(id);
            if(!itemEntities.isPresent()){
                itemDetails.setSerial_no(itemEntities.get().getItem_serial_no());
                itemDetails.setCategory(itemEntities.get().getCategory());
                itemDetails.setImage(itemEntities.get().getImage());
                itemDetails.setId(itemEntities.get().getItem_id());
                itemDetails.setItem_name(itemEntities.get().getItem_name());
                itemDetails.setPrice(itemEntities.get().getPrice());
                itemDetails.setType(itemEntities.get().getType());

                return itemDetails;
            } else{
                return itemDetails;
            }
        }catch (Exception e) {
            LOGGER.error("get item details : " + e.getMessage(), e);
            throw e;
        }
    }
}
