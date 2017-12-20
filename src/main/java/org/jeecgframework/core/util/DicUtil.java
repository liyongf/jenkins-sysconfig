package org.jeecgframework.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;

import java.util.List;

/**
 * 
 * @author hansf
 * 
 */
public class DicUtil {
	private static Logger log = Logger.getLogger(DicUtil.class);

	/**
	 * 根据字典编码获取字典文本值
	 * @param groupCode
	 * @param typeCode
	 * @return
	 */
	public static String getTypeNameByCode(String groupCode, String typeCode) {
		String typeName="";
		if(StringUtil.isNotEmpty(typeCode)){
			if (StringUtils.isBlank(groupCode)) {
				return typeCode;
			}
			List<TSType> types = ResourceUtil.allTypes.get(groupCode.toLowerCase());
			if (types!=null && types.size()>0) {
				for (TSType tSType : types) {
					if (tSType.getTypecode().equals(typeCode)) {
						typeName = tSType.getTypename();
						break;
					}
				}
			}
		}
		
		return typeName;
	}
	
	/**
	 * 根据字典名称获取字典编码
	 * @param groupCode
	 * @param typeCode
	 * @return
	 */
	public static String getTypeCodeByName(String groupCode, String typeName) {
		String typeCode="";
		if(StringUtil.isNotEmpty(typeName)){
			if (StringUtils.isBlank(groupCode)) {
				return typeCode;
			}
			List<TSType> types = ResourceUtil.allTypes.get(groupCode.toLowerCase());
			if (types!=null && types.size()>0) {
				for (TSType tSType : types) {
					if (tSType.getTypename().equals(typeName.trim())) {
						typeCode = tSType.getTypecode();
						break;
					}
				}
			}
		}
		
		return typeCode;
	}
}
