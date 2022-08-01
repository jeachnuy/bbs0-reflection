package service;

import java.util.List;

import dto.BbsDto;
import model.Bbs;

/**
 * BbsService
 */
public interface BbsService {
    void insert(BbsDto dto);

    List<Bbs> select();

    int delete();
}
