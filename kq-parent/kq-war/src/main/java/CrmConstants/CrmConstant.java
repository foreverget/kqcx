package CrmConstants;

import java.util.ArrayList;
import java.util.List;

public class CrmConstant {

	/**
	 * 正常班打卡开始时间
	 */
	public static String NORMAL_START = String.valueOf("08:50");

	/**
	 * 正常班打卡结束时间
	 */
	public static String NORMAL_END = String.valueOf("17:50");

	/**
	 * 加班打卡开始时间
	 */
	public static String OVERString_START = String.valueOf("8:50");

	/**
	 * 加班打卡结束时间
	 */
	public static String OVERString_END = String.valueOf("17:50");

	/**
	 * 白加班打卡开始时间
	 */
	public static String NEWOVERString_START = String.valueOf("8:50");

	/**
	 * 白加班打卡结束时间
	 */
	public static String NEWOVERString_END = String.valueOf("21:00");

	/**
	 * 正常班打卡开始时间
	 */
	public static String SPECIAL_NORMAL_START = String.valueOf("9:30");

	/**
	 * 正常班打卡结束时间
	 */
	public static String SPECIAL_NORMAL_END = String.valueOf("18:30");

	/**
	 * 加班打卡开始时间
	 */
	public static String SPECIAL_OVERString_START = String.valueOf("09:30");

	/**
	 * 加班打卡结束时间
	 */
	public static String SPECIAL_OVERString_END = String.valueOf("18:30");

	/**
	 * 白加班打卡开始时间
	 */
	public static String SPECIAL_NEWOVERString_START = String.valueOf("18:30");

	/**
	 * 白加班打卡结束时间
	 */
	public static String SPECIAL_NEWOVERString_END = String.valueOf("21:30");

	/**
	 * 特殊的组别
	 */
	@SuppressWarnings("serial")
	public static List<String> SPECIALGROUP = new ArrayList<String>() {

		{
			add("帕拉蒂尼销售三部二组");
			add("帕拉蒂尼销售三部四组");
		}
	};

	/**
	 * 特殊的部门
	 */
	@SuppressWarnings("serial")
	public static List<String> SPECIALDEP = new ArrayList<String>() {

		{
			add("第六军团销售二部");
		}
	};
}
