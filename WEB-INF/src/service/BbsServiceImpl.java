package service;

import java.util.List;

import dao.BbsRepository;
import dto.BbsDto;
import model.Bbs;
import util.LogUtil;

/**
 * BbsServiceImpl
 */
public class BbsServiceImpl implements BbsService {
    private static final String TAG = "BbsServiceImpl";
    private final BbsRepository bbsRepository;

    public BbsServiceImpl(BbsRepository bbsRepository) {
        this.bbsRepository = bbsRepository;
    }

    @Override
    public void insert(BbsDto dto) {
        LogUtil.getInstance().info(TAG, "insert()");
        bbsRepository.save(new Bbs(dto));
    }

    @Override
    public List<Bbs> select() {
        LogUtil.getInstance().info(TAG, "select()");
        return bbsRepository.findAll();
    }

    @Override
    public int delete() {
        LogUtil.getInstance().info(TAG, "delete()");
        int count = bbsRepository.findAll().size();
        bbsRepository.deleteAll();
        return count;
    }
}
