package com.fashion.demo.service.Impl;

import com.fashion.demo.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "itemService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItemServiceImpl implements ItemService {
}
