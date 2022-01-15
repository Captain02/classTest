package

        com.jzysoft.commonmoudle.moudles.ckZclxJc;


import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import java.util.List;

/**
 * 参见"测控组成类型"状态图 服务层
 *
 * @author zsd
 * @date 2020-03-28
 */
public interface CkZclxJcService {

    //    列表
    List<PageData> selectCkZclxJcList(Page page) throws Exception;
    //    id
    PageData selectCkZclxJcById(PageData pageData) throws Exception;

    //    保存
    void insertCkZclxJc(PageData pageData) throws Exception;

    //    修改
    void updateCkZclxJc(PageData pageData) throws Exception;

    //    删除
    void deleteCkZclxJcByIds(PageData pageData) throws Exception;

}
