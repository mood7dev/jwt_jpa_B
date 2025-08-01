package com.green.gallery_jwt_jpa.item;

import com.green.gallery_jwt_jpa.item.model.ItemGetRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;

    public List<ItemGetRes> findAll(List<Integer> ids) {
        return itemMapper.findAllByIdIn(ids);
    }
}
