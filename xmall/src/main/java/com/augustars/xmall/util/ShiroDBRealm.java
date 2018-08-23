package com.augustars.xmall.util;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.augustars.xmall.entity.User;
import com.augustars.xmall.service.UserService;

//无法确定在哪一层设定Bean组件，可以采用@Component
/**
 *若是指定控制层的Bean组件，采用@Controller注解
 *若是指定服务层的Bean组件，采用@Service注解
 *若是指定数据持久层的Bean组件，采用@ResponseBody注解
 */
@Component("shiroDBRealm")
public class ShiroDBRealm extends AuthorizingRealm{
	@Reference(version="1.0.0")
	private UserService userService;
	/**
	 * 进行权限授权的时调用doGetAuthenticationInfo()方法
	 * doGetAuthenticationInfo():进行用户授权的时候调用的方法,用户获得当前用户的授权信息
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		return null;
	}

	/**
	 * 进行用户身份验证时调用doGetAuthenticationInfo()方法
	 * doGetAuthenticationInfo():当我们调用subject.login进行认证的方法，
	 * 							 这个方法的参数token就是我们subject.login调用的
								这里面我们就可以查询数据库对用户名和密码进行认证 
								如果认证成功将用户信息封装成SimpleAuthenticationInfo 
								认证失败根据情况抛出异常
	 * 
	 */
	protected AuthenticationInfo doGetAuthenticationInfo
						(AuthenticationToken token) throws AuthenticationException {		
		//将默认的token切换成UsernamePasswordToken
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		//获取username和password
		String loginName = userToken.getUsername();
		//由于password是char类型的数组，必须将其转换成String类型
		String password = new String(userToken.getPassword());
		//对该原始的密码进行加密
		password = EncryptionUtil.encrypt(password);
		//将加密后的密码重新放回userToken中
		//对于shiro来说，进行验证时，是将Token中的密码与用户输入的密码进行比较
		userToken.setPassword(password.toCharArray());
		
		try {
			//对于Shiro来说，必须提供一个正确的登录用户对象
			//通过用户的登录名查询用户的信息
			User user = userService.getUserByLogibName(loginName);
			//调用shiro进行验证信息是否正确
			if (user != null) {
				if (user.getStatus().equals("Y")) {
					//用户存在,用户名，密码，验证名
					//info为登陆成功的标志位
					SimpleAuthenticationInfo info =
							new SimpleAuthenticationInfo(
									user.getLoginName(),user.getPassword(),getName());
					//默认登录成功，绑定Session对象
					SecurityUtils.getSubject().getSession().setAttribute("user", user); 
					return info;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}







