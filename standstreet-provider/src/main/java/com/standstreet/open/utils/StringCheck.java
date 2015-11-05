package com.standstreet.open.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串进行处理的工具类
 *
 * @author Pescadito 2013-12-02 17:55 修改记录： 1.2014-01-02 增加deviceType的验证方法
 */
public class StringCheck {

	/**
	 * 判断一个字符串是否为纯数字
	 *
	 * @param str
	 *            字符串
	 * @return
	 */
	public static boolean isNumber( String str ) {
		if( str == null || str.trim().isEmpty() )
			return false;
		boolean isNumberStr = str.matches( "[0-9]+" );
		return isNumberStr;
	}

	/**
	 * 判断一个字符串中是否含有中文字符
	 *
	 * @param str
	 * @return
	 */
	public static final boolean isContainChineseCharacter( String str ) {
		if( str == null || str.trim().isEmpty() )
			return false;
		char[] charArray = str.toCharArray();
		for( int i = 0; i < charArray.length; i++ ) {
			if( ( charArray[ i ] >= 0x4e00 ) && ( charArray[ i ] <= 0x9fbb ) ) {
				// Java判断一个字符串是否有中文是利用Unicode编码来判断，
				// 因为中文的编码区间为：0x4e00--0x9fbb
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个字符串是否是合法长度的纯数字字符串
	 *
	 * @param str
	 *            需要判断的字符串
	 * @param length
	 *            字符串的正确长度
	 * @return
	 */
	public static boolean isRightLengthNum( String str, int length ) {
		if( str == null || str.trim().isEmpty() )
			return false;
		boolean isNumber = isNumber( str );
		boolean isLengthRight = ( str.length() == length ) ? true : false;
		if( isNumber && isLengthRight ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断用户名是否合法
	 *
	 * @param userName
	 *            用户名
	 * @return 0：合法；1：用户名为空；2：用户名超出最大长度限制；3：用户名超出最小长度限制；
	 */
	public static int isLegalUserName( String userName ) {
		int result = 0;
		// 字符长度，用来记录包含中文的用户名的长度
		int valueLength = 0;
		// 中文unicode码范围
		String chinese = "[\u4e00-\u9fa5]";

		// 为空返回不合法
		if( userName == null || userName.trim().isEmpty() ) {
			result = 1;// 为空时候返回1
			return result;
		}

		// 计算userName长度，中文长度为2，其他为1
		for( int i = 0; i < userName.length(); i++ ) {
			// 获取一个字符
			String temp = userName.substring( i, i + 1 );
			// 判断是否为中文字符
			if( temp.matches( chinese ) ) {
				// 中文字符长度为2
				valueLength += 2;
			} else {
				// 其他字符长度为1
				valueLength += 1;
			}
		}

		// 长度是否合法
		if( valueLength > Constant.USERNAME_MAX_LENGTH ) {
			result = 2;// 超出长度限制，返回2
			return result;
		} else if( valueLength < Constant.USERNAME_MIN_LENGTH ) {
			result = 3;// 超出最小长度限制，返回3
			return result;
		}

		return result;
	}

	/**
	 * 判断密码是否合法
	 *
	 * @param pwd
	 *            密码
	 * @return 0：合法；1：密码为空；2：超出最大限制；3：超出最小限制；4：密码中有中文
	 */
	public static int isLegalPassword( String pwd ) {
		int result = 0;
		// 为空返回1
		if( pwd == null || pwd.trim().isEmpty() ) {
			result = 1;
			return result;
		}
		// 长度是否合法
		if( pwd.length() > Constant.PWD_MAX_LENGTH ) {
			result = 2;// 密码超过最大长度限制
			return result;
		} else if( pwd.length() < Constant.PWD_MIN_LENGTH ) {
			result = 3;// 密码超过最小长度限制
			return result;
		}
		// 密码中是否包含中文字符
		if( isContainChineseCharacter( pwd ) ) {
			result = 4;// 密码中包含中文字符
			return result;
		}
		return result;
	}

	/**
	 * 判断昵称是否合法
	 *
	 * @param nickName
	 *            昵称
	 * @return 0：合法；1：昵称为空；2：昵称超过最大长度限制；3：昵称超出最小长度限制
	 */
	public static int isLegalNickName( String nickName ) {
		int result = 0;
		// 为空返回1
		if( nickName == null || nickName.trim().isEmpty() ) {
			result = 1;// 昵称为空，返回1
			return result;
		}
		// 长度是否合法
		if( nickName.length() > Constant.NICKNAME_MAX_LENGTH ) {
			result = 2;// 昵称长度超出最大限度，返回2
			return result;
		} else if( nickName.length() < Constant.NICKNAME_MIN_LENGTH ) {
			result = 3;// 昵称长度超出最小限度，返回3
			return result;
		}
		return result;
	}

	/**
	 * 用户真实姓名的长度判断
	 *
	 * @param realName
	 *            真实姓名
	 * @return 0：合法；1：真是姓名为空；2：真实姓名超过最大长度限制；3：真实姓名超过最小长度限制
	 */
	public static int isLegalRealName( String realName ) {
		int result = 0;
		// 为空返回不合法
		if( realName == null || realName.trim().isEmpty() ) {
			result = 1;// 真实姓名为空，返回1
			return result;
		}
		// 长度是否合法
		if( realName.length() > Constant.REALNAME_MAX_LENGTH ) {
			result = 2;// 真实姓名超过最大限制，返回2
			return result;
		} else if( realName.length() < Constant.REALNAME_MIN_LENGTH ) {
			result = 3;// 真实姓名超过最小限制，返回3
			return result;
		}
		return result;
	}

	/**
	 * 判断生日字段是否合法，合法结构为“2010-10-10”
	 *
	 * @param birthday
	 * @return 0：生日合法；1：生日为空；2：生日年份不合法；3：生日月份不合法；4：生日天不合法；5：生日长度不合法；
	 *         6：生日当中包含数字和“-”之外的其他特殊字符;7:“-”字符的位置是否正确
	 */
	public static int isLegalBirthday( String birthday ) {
		int result = 0;
		// 为空返回不合法
		if( birthday == null || birthday.trim().isEmpty() ) {
			result = 1;// 生日为空，返回1
			return result;
		}
		// 长度是否合法
		if( birthday.length() != Constant.BIRTHDAY_LENGTH ) {
			result = 5;// 生日长度不合法，返回5
			return result;
		}
		// 包含数字和“-”之外的其他特殊字符
		if( birthday.replaceAll( "\\d*-*", "" ).length() != 0 ) {
			result = 6;// 生日当中包含数字和“-”之外的其他特殊字符，返回6
			return result;
		}
		// “-”字符的位置是否正确
		if( !birthday.substring( 4, 5 ).equals( "-" ) ) {
			result = 7;// 生日当中第4位不是“-”，返回7
			return result;
		}
		if( !birthday.substring( 7, 8 ).equals( "-" ) ) {
			result = 7;// 生日当中第7位不是“-”，返回7
			return result;
		}

		// 年份的合法性
		String year = birthday.substring( 0, 4 );
		if( !isRightLengthNum( year, 4 ) ) {
			result = 2;// 生日年份不合法，返回2
			return result;
		}
		// 月份的合法性
		String month = birthday.substring( 5, 7 );
		int monthInt = Integer.parseInt( month );
		if( !isRightLengthNum( month, 2 ) || monthInt > 12 || monthInt < 1 ) {
			result = 3;// 生日月份不合法，返回3
			return result;
		}
		// 日期的合法性
		String day = birthday.substring( 8, 10 );
		int dayInt = Integer.parseInt( day );
		if( !isRightLengthNum( month, 2 ) || dayInt > 31 || dayInt < 1 ) {
			result = 4;// 日期不合法，返回4
			return result;
		}

		return result;
	}

	/**
	 * 验证用户地址信息的合法性
	 *
	 * @param location
	 *            用户地址信息
	 * @return 0：地址合法；1：地址信息为空；2：地址信息超过最大长度限制；3：地址信息超过最小长度限制；
	 */
	public static int isLegalLocation( String location ) {
		int result = 0;
		// 为空返回不合法
		if( location == null || location.trim().isEmpty() ) {
			result = 1;// 地址为空，返回1
			return result;
		}
		// 地址长度的合法性
		if( location.length() > Constant.LOCATION_MAX_LENGTH ) {
			result = 2;// 地址长度超出最大限制，返回2
			return result;
		}
		//2015-3-30 取消最小限制 @author tao.jiang
//		} else if( location.length() < Constant.LOCATION_MIN_LENGTH ) {
//			result = 3;// 地址长度超出最小限制，返回3
//			return result;
//		}

		return result;
	}

	/**
	 * 判断性别的合法性
	 *
	 * @param sex
	 *            性别
	 * @return 0：性别合法；1：性别为空；2：性别不是0（女）和1（男）
	 */
	public static int isLegalSex( String sex ) {
		int result = 0;
		// 性别是否为空
		if( sex == null || sex.trim().isEmpty() ) {
			result = 1;// 性别为空，返回1
			return result;
		}
		// 性别取值判断
		if( !sex.equals( Constant.SEX_MALE ) && !sex.equals( Constant.SEX_FEMAL ) ) {
			result = 2;// 性别不是0（女）和1（男），返回2
			return result;
		}

		return result;
	}

	/**
	 * 验证血型的合法性
	 *
	 * @param bloodtype
	 *            血型
	 * @return 0：合法；1：血型为空；2：血型不是标准的四种
	 */
	public static int isLegalBloodtype( String bloodtype ) {
		int result = 0;
		// 血型是否为空
		if( bloodtype == null || bloodtype.trim().isEmpty() ) {
			result = 1;// 血型为空，返回1
			return result;
		}
		// 血型不是标准的四种
		List<String> bloodList = new ArrayList<String>();
		bloodList.add( "A" );
		bloodList.add( "B" );
		bloodList.add( "O" );
		bloodList.add( "AB" );
		boolean isContain = false;
		for( String blood : bloodList ) {
			if( blood.equals( bloodtype ) ) {
				isContain = true;
				break;
			}

		}
		if( isContain ) {
			result = 0;
		} else {
			result = 2;// 如果血型不是标准的四种，返回2
		}
		return result;
	}

	/**
	 * 验证电子邮件的合法性
	 *
	 * @param email
	 *            电子邮件
	 * @return
	 *         0：合法；1：电子邮件为空；2：电子邮件长度超出最大限制；3：电子邮件长度超过最小限制；4：电子邮件中有除开子母、数字、“@”、“-
	 *         ”、“_”、“.”之外的特殊字符
	 */
	public static int isLegalEmail( String email ) {
		int result = 0;
		// 判断email是否为空
		if( email == null || email.trim().isEmpty() ) {
			result = 1;// 如果email为空，返回1
			return result;
		}
		// 判断email的长度
		if( email.length() > Constant.EMAIL_MAX_LENGTH ) {
			result = 2;// 如果email的长度超过最大限度，返回2
			return result;
		} else if( email.length() < Constant.EMAIL_MIN_LENGTH ) {
			result = 3;// 如果email的长度超过最小限度，返回3
			return result;
		}
		//判断邮箱是否合法
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean ifMatch = matcher.matches();
		//合法返回0
		if(ifMatch){
			result = 0;
		} else{
			result = 4;
		}
//		// 判断email中是否有除开子母、数字、“@”、“-”、“_”、“.”之外的特殊字符
//
//		if( email.replaceAll( "[a-z]*[A-Z]*[0-9]*-*_*@*\\.*", "" ).length() == 0 ) {
//			result = 0;
//		} else {
//			result = 4;// 如果email中有除开子母、数字、“@”、“-”、“_”、“.”之外的特殊字符，返回4
//		}

		return result;
	}

	/**
	 * 验证QQ号的合法性
	 *
	 * @param qq
	 *            QQ号码
	 * @return 0：合法；1：QQ号为空；2：QQ号里面有非数字字符；3：QQ号的长度超过了最大限制；4：QQ号的长度超过了最小限制
	 */
	public static int isLegalQQ( String qq ) {
		int result = 0;
		// qq号是否为空
		if( qq == null || qq.trim().isEmpty() ) {
			result = 1;// qq号为空，返回1
			return result;
		}
		// qq好是否全是数字
		if( !isNumber( qq ) ) {
			result = 2;// qq号里面有非数字字符，返回2
			return result;
		}
		// qq号长度验证
		if( qq.length() > Constant.QQ_MAX_LENGTH ) {
			result = 3;// qq号的长度超过了最大限制，返回3
			return result;
		} else if( qq.length() < Constant.QQ_MIN_LENGTH ) {
			result = 4;// qq号的长度超过了最小限制，返回4
			return result;
		}
		return result;
	}

	/**
	 * 判断星座的合法性
	 *
	 * @param sign
	 *            星座
	 * @return 0：合法；1：星座为空；2：星座不是标准的12种
	 */
	public static int isLegalSign( String sign ) {
		int result = 0;
		// 星座是否为空
		if( sign == null || sign.trim().isEmpty() ) {
			result = 1;// 星座为空，返回1
			return result;
		}
		// 星座不是标准的12种
		List<String> signList = new ArrayList<String>();
		signList.add( "白羊座" );
		signList.add( "金牛座" );
		signList.add( "双子座" );
		signList.add( "巨蟹座" );
		signList.add( "狮子座" );
		signList.add( "处女座" );
		signList.add( "天秤座" );
		signList.add( "天蝎座" );
		signList.add( "射手座" );
		signList.add( "摩羯座" );
		signList.add( "水瓶座" );
		signList.add( "双鱼座" );
		boolean isContain = false;
		for( String blood : signList ) {
			if( blood.equals( sign ) ) {
				isContain = true;
				break;
			}

		}
		if( isContain ) {
			result = 0;
		} else {
			result = 2;// 如果星座不是标准的12种，返回2
		}
		return result;
	}

	/**
	 * 判断属相的合法性
	 *
	 * @param chineseZodiac
	 *            属相信息
	 * @return 0：合法；1：属相为空；2：属相不是标准的12种
	 */
	public static int isLegalChineseZodiac( String chineseZodiac ) {
		int result = 0;
		// 属相是否为空
		if( chineseZodiac == null || chineseZodiac.trim().isEmpty() ) {
			result = 1;// 属相为空，返回1
			return result;
		}
		// 属相不是标准的12种
		List<String> chineseZodiacList = new ArrayList<String>();
		chineseZodiacList.add( "鼠" );
		chineseZodiacList.add( "牛" );
		chineseZodiacList.add( "虎" );
		chineseZodiacList.add( "兔" );
		chineseZodiacList.add( "龙" );
		chineseZodiacList.add( "蛇" );
		chineseZodiacList.add( "马" );
		chineseZodiacList.add( "羊" );
		chineseZodiacList.add( "猴" );
		chineseZodiacList.add( "鸡" );
		chineseZodiacList.add( "狗" );
		chineseZodiacList.add( "猪" );
		boolean isContain = false;
		for( String blood : chineseZodiacList ) {
			if( blood.equals( chineseZodiac ) ) {
				isContain = true;
				break;
			}

		}
		if( isContain ) {
			result = 0;
		} else {
			result = 2;// 如果属相不是标准的12种，返回2
		}
		return result;
	}

	/**
	 * 判断电话号码的合法性
	 *
	 * @param phonenum
	 *            电话号码
	 * @return 0：合法；1：电话号码为空；2：电话号码超过最大程度限制；3：电话号码超过最小长度限制
	 */
	public static int isLegalPhonenum( String phonenum ) {
		int result = 0;
		// 如果电话号码为空
		if( phonenum == null || phonenum.trim().isEmpty() ) {
			result = 1;// 如果电话号码为空，返回1
			return result;
		}
		// 判断电话号码长度的合法性
		if( phonenum.length() > Constant.PHONENUMBER_MAX_LENGTH ) {
			result = 2;// 如果电话号码长度超过最大限制，返回2
			return result;
		} else if( phonenum.length() < Constant.PHONENUMBER_MIN_LENGTH ) {
			result = 3;// 如果电话号码长度超过最小限制，返回3
			return result;
		}
		return result;
	}

	/**
	 * 判断用户个人说明的合法性
	 *
	 * @param info
	 *            个人说明
	 * @return 0：合法；1：个人说明为空；3：个人说明长度超过最大限制
	 */
	public static int isLegalPersonalInof( String info ) {
		int result = 0;
		// 如果电话号码为空
		if( info == null || info.trim().isEmpty() ) {
			result = 1;// 如果个人信息为空，返回1
			return result;
		}
		// 判断个人信息长度的合法性
		if( info.length() > Constant.PERSONNALINFO_MAX_LENGTH ) {
			result = 2;// 如果个人说明长度超过最大限制，返回2
			return result;
		}
		return result;
	}

	/**
	 * 验证用户绑定的设备类型的合法性（专用的）
	 *
	 * @param devType
	 *            设备类型
	 * @return 0：合法；1：设备类型为空；2：设备类型不合法
	 */
	public static int isLegalDevType( Integer devType ) {
		// 设备类型是否为空
		if( ( devType.equals( null ) ) ) { return 1;// 设备类型为空，返回1
		}
		// 设备类型是否合法
		if( devType.intValue() == 4 || devType.intValue() == 3 || devType.intValue() == 2 || devType.intValue() == 1 ) { return 0; }
		return 2;
	}

	/**
	 * 验证设备类型的合法性（通用的）
	 *
	 * @param deviceType
	 *            设备类型
	 * @return 0：合法；1：设备类型为空；2：设备类型不合法
	 */

	/**
	 * 2014年10月9日9:54:41 修改对设备类型的判定，增加8888表示卖场导购的登录 修改对设备类型长度的判定
	 */
	public static int isLegalDeviceType( String deviceType ) {
		// 设备类型是否为空
		if( deviceType == null || deviceType.trim().isEmpty() ) { return 1;// 设备类型为空，返回1
		}
		// 设备类型是否合法
		if( deviceType.length() > 4 ) { return 2; }
		/*
		 * if(deviceType.matches("[a-zA-Z]+|[0-9]")){ return 0; }
		 */

		if( deviceType.matches( "[0-9]+|[8888]" ) ) { return 0; }
		// if( deviceType.equals( "0" ) || deviceType.equals( "1" ) ||
		// deviceType.equals( "2" ) || deviceType.equals( "3" ) ) { return 0; }
		return 2;
	}

	/**
	 *
	 * 判断设备序列号是否合法
	 *
	 * @param sn
	 *            设备序列号
	 *
	 * @return 0：合法；1：用户名为空；2：用户名超出最大长度限制；
	 */
	public static int isLegalSn( String sn ) {
		int result = 0;
		// 为空返回不合法
		if( sn == null || sn.trim().isEmpty() ) {
			result = 1;// 为空时候返回1
			return result;
		}
		// 长度是否合法
		if( sn.length() > 50 ) {
			result = 2;// 超出长度限制，返回2
			return result;
		}
		return result;
	}

	/**
	 *
	 * @user:HDJ
	 * @date 2014年9月26日 下午3:57:47
	 * @Title: isLegalLength 判定字符串的长度是否合法
	 * @Description: TODO
	 * @param @param arg 需要判定的参数
	 * @param @param mixlt 最小值
	 * @param @param maxlt 最大值
	 * @param @return 设定文件HDJ
	 * @return int 返回类型
	 * @throws
	 */
	public static int isLegalLength( String arg, int mixlt, int maxlt ) {
		int result = 0;
		// 为空返回不合法
		if( arg == null || arg.trim().isEmpty() ) {
			result = 1;// 为空时候返回1
			return result;
		}
		// 长度是否合法
		if( arg.length() > maxlt ) {
			result = 2;// 超出长度限制，返回2
			return result;
		} else if( arg.length() < mixlt ) {
			result = 3;// 超出最小长度限制，返回3
			return result;
		}

		return result;
	}

	/**
	 * 判断输入的字符串是否是合法用户名
	 *
	 * @param userName
	 * @return true：合法 false：不合法
	 */
	public static boolean isLegalName( String userName ) {
		boolean result = false;
		String regexStr = "^(?!\\d+$)[a-zA-Z0-9_]{4,18}$";
		Pattern pa = Pattern.compile( regexStr );
		Matcher ma = pa.matcher( userName );
		result = ma.matches();
		return result;
	}


	/**
	 * 判断是否含有表情
	 *
	 * @param source
	 * @return true：合法 false：不合法
	 */
	public static boolean isEmoji( String source ) {
		boolean result = false;
		String regexStr = "[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]";
		Pattern pa = Pattern.compile( regexStr );
		Matcher ma = pa.matcher( source );
		result = ma.matches();
		return result;
	}

}