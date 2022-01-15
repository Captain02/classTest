package

        com.jzysoft.commonmoudle.moudles.ckZclxJc;

import com.github.pagehelper.PageHelper;
import com.jzysoft.commonmoudle.lib.config.page.Page;
import com.jzysoft.commonmoudle.lib.config.page.PageData;
import com.jzysoft.commonmoudle.lib.util.BaseJQController;
import com.jzysoft.commonmoudle.lib.util.RJQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 参见"测控组成类型"状态图 信息操作处理
 *
 * @author sxd
 * @date 2020-03-28
 */
@Controller
@RequestMapping("/ckZclxJc/ckzclxjc")
public class CkZclxJcController extends BaseJQController //BaseController
{
    private String prefix = "moudles/ckZclxJc";
    @Autowired
    CkZclxJcService ckZclxJcService;

    @RequestMapping(value = "")
    public String ckZclxJc() {
        return prefix + "/ckZclxJc";
    }

    /**
     * 列表
     * sxd
     */
    @ResponseBody
    @GetMapping("/list")
    public RJQ selckZclxJcPage(Page page) throws Exception {
        PageData pageData = this.getPageData();
        page.setPd(pageData);
        PageHelper.startPage(page.getCurrentResult(), page.getShowCount());
        List<PageData> list = ckZclxJcService.selectCkZclxJcList(page);
        return RJQ.ok().put("page", page).put("data", list);
    }

    /**
     * 根据id获取数据参见"测控组成类型"状态图
     * sxd
     */
    @ResponseBody
    @GetMapping("/findByid")
    public RJQ getckZclxJcInfo() throws Exception {
        PageData pageData = this.getPageData();
        PageData data = ckZclxJcService.selectCkZclxJcById(pageData);
        return RJQ.ok().put("data", data);
    }

    /**
     * 新增参见"测控组成类型"状态图
     * sxd
     */
    @ResponseBody
    @PostMapping("/adddata")
    public RJQ saveckZclxJcData(@RequestBody PageData pageData) throws Exception {
        ckZclxJcService.insertCkZclxJc(pageData);
        PageData pageData1 = new PageData();
//        pageData1.put("ID",pageData.get("ID"));
        pageData.put("InOrder",pageData.get("ID"));
        ckZclxJcService.updateCkZclxJc(pageData);
        return RJQ.ok();
    }

    /**
     * 修改参见"测控组成类型"状态图
     * sxd
     */
    @ResponseBody
    @PostMapping("/updatedata")
    public RJQ editckZclxJcData(@RequestBody PageData pageData) throws Exception {
        ckZclxJcService.updateCkZclxJc(pageData);
        return RJQ.ok();
    }

    /**
     * 批量删除参见"测控组成类型"状态图
     * sxd
     */
    @ResponseBody
    @PostMapping("/del")
    public RJQ delckZclxJcDataList(@RequestBody Integer[] ids) throws Exception {
        PageData pageData = this.getPageData();
        for (Integer id : ids) {
            pageData.put("ID", id);
            ckZclxJcService.deleteCkZclxJcByIds(pageData);
        }
        return RJQ.ok();
    }
}
