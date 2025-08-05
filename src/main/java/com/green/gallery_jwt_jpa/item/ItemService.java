package com.green.gallery_jwt_jpa.item;

import com.green.gallery_jwt_jpa.config.util.MyFileUtils;
import com.green.gallery_jwt_jpa.item.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemMapper itemMapper;
    private final MyFileUtils myFileUtils;

    public int save(MultipartFile img, ItemPostReq req) {
        String savedFileName = myFileUtils.makeRandomFileName(img); //저장할 파일명
        req.setImgPath(savedFileName);
        int result = itemMapper.save(req);

        String directoryPath = String.format("/item/%d", req.getId());
        myFileUtils.makeFolders(directoryPath);

        String savedPathFileName = directoryPath + "/" + savedFileName;
        try {
            myFileUtils.transferTo(img, savedPathFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }

    public List<ItemGetRes> findAll(List<Integer> ids) {
        return itemMapper.findAllByIdIn(ids);
    }
}