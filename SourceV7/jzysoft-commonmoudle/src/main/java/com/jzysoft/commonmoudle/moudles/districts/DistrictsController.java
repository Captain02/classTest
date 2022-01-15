package com.jzysoft.commonmoudle.moudles.districts;

import com.jzysoft.common.annotation.Log;
import com.jzysoft.common.core.controller.BaseController;
import com.jzysoft.common.core.domain.AjaxResult;
import com.jzysoft.common.core.page.TableDataInfo;
import com.jzysoft.common.enums.BusinessType;
//import com.jzysoft.common.utils.poi.ExcelUtil;
//import com.jzysoft.framework.util.ShiroUtils;
//import com.jzysoft.system.domain.Districts;
//import com.jzysoft.system.service.IDistrictsService;
import com.jzysoft.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 地区 信息操作处理
 * 
 * @author jzysoft
 * @date 2018-12-19
 */
@Controller
@RequestMapping("/system/districts")
public class DistrictsController extends BaseController
{
    private String prefix = "moudles/districts";
	
	@Autowired
	private IDistrictsService districtsService;
	
	@RequiresPermissions("system:districts:view")
	@GetMapping()
	public String districts()
	{
	    return prefix + "/districts";
	}
	
	@RequiresPermissions("system:districts:list")
	@GetMapping("demo")
	public String demo()
	{
	    return prefix + "/demo";
	}
	
	/**
	 * 查询地区列表
	 */
	@RequiresPermissions("system:districts:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Districts districts) throws Exception
	{
		startPage();
        List<Districts> list = districtsService.selectDistrictsList(districts);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出地区列表
	 */
	@RequiresPermissions("system:districts:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Districts districts) throws Exception
	{
    	List<Districts> list = districtsService.selectDistrictsList(districts);
        ExcelUtil<Districts> util = new ExcelUtil<Districts>(Districts.class);
        return util.exportExcel(list, "districts");
    }
	
	/**
	 * 新增地区
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存地区
	 */
	@RequiresPermissions("system:districts:add")
	@Log(title = "地区", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Districts districts) throws Exception
	{		
	    districts.setPid(districts.getId()/100);
	    districts.setCreateTime(new Date());
	    districts.setUpdateTime(new Date());
	    //districts.setOperator(ShiroUtils.getLoginName());
		return toAjax(districtsService.insertDistricts(districts));
	}

	/**
	 * 修改地区
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap) throws Exception
	{
		Districts districts = districtsService.selectDistrictsById(id);
		mmap.put("districts", districts);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存地区
	 */
	@RequiresPermissions("system:districts:edit")
	@Log(title = "地区", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Districts districts) throws Exception
	{		
	    districts.setPid(districts.getId()/100);
	    //districts.setOperator(ShiroUtils.getLoginName());
	    districts.setUpdateTime(new Date());
		return toAjax(districtsService.updateDistricts(districts));
	}
	
	/**
	 * 删除地区
	 */
	@RequiresPermissions("system:districts:remove")
	@Log(title = "地区", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids) throws Exception
	{		
		return toAjax(districtsService.deleteDistrictsByIds(ids));
	}
	
}
