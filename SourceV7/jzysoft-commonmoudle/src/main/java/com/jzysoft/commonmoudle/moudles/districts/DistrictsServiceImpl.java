package com.jzysoft.commonmoudle.moudles.districts;


import com.jzysoft.commonmoudle.lib.config.page.DaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区 服务层实现
 * 
 * @author jzysoft
 * @date 2018-12-19
 */
@Service
public class DistrictsServiceImpl implements IDistrictsService 
{
	/*@Autowired
	private DistrictsMapper districtsMapper;*/
	@Autowired
	DaoSupport daoSupport;

	/**
     * 查询地区信息
     * 
     * @param id 地区ID
     * @return 地区信息
     */
    @Override
	public Districts selectDistrictsById(Integer id) throws Exception
	{
	    //return districtsMapper.selectDistrictsById(id);
		return (Districts) daoSupport.findForObject("DistrictsMapper.selectDistrictsById", id);
	}
	
	/**
     * 查询地区列表
     * 
     * @param districts 地区信息
     * @return 地区集合
     */
	@Override
	public List<Districts> selectDistrictsList(Districts districts) throws Exception
	{
	    //return districtsMapper.selectDistrictsList(districts);
		return (List<Districts>) daoSupport.findForList("DistrictsMapper.selectDistrictsList", districts);
	}
	
    /**
     * 新增地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	@Override
	public int insertDistricts(Districts districts) throws Exception
	{
		return (int)daoSupport.save("DistrictsMapper.insertDistricts", districts);
		//return districtsMapper.insertDistricts(districts);
	}
	
	/**
     * 修改地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	@Override
	public int updateDistricts(Districts districts) throws Exception
	{
		//return districtsMapper.updateDistricts(districts);
		return (int)daoSupport.update("DistrictsMapper.updateDistricts", districts);
	}

	/**
     * 删除地区对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDistrictsByIds(String ids) throws Exception
	{
		//return districtsMapper.deleteDistrictsByIds(Convert.toStrArray(ids));
		return  (int)daoSupport.delete("DistrictsMapper.deleteDistrictsByIds", ids);
	}
	
}
