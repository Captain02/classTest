package com.jzysoft.commonmoudle.moudles.districts;

//import com.jzysoft.system.domain.Districts;
import java.util.List;

/**
 * 地区 服务层
 * 
 * @author jzysoft
 * @date 2018-12-19
 */
public interface IDistrictsService 
{
	/**
     * 查询地区信息
     * 
     * @param id 地区ID
     * @return 地区信息
     */
	public Districts selectDistrictsById(Integer id) throws Exception;
	
	/**
     * 查询地区列表
     * 
     * @param districts 地区信息
     * @return 地区集合
     */
	public List<Districts> selectDistrictsList(Districts districts)  throws Exception;
	
	/**
     * 新增地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	public int insertDistricts(Districts districts) throws Exception;
	
	/**
     * 修改地区
     * 
     * @param districts 地区信息
     * @return 结果
     */
	public int updateDistricts(Districts districts) throws Exception;
		
	/**
     * 删除地区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDistrictsByIds(String ids) throws Exception;


	
}
