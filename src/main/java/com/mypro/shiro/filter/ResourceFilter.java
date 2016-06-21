package com.mypro.shiro.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.mypro.shiro.realm.CasRealmCustom;


/**
 * 拦截资源访问进行权限控制
 * @author liuxin
 *
 */
public class ResourceFilter extends PathMatchingFilter {
	
	private static Logger logger = LoggerFactory.getLogger(ResourceFilter.class);
	
private String applicationIdentity;//当前应用系统标识
	
	private String unauthorizedUrl;//未授权时跳转的URL
	
	private boolean openAdminPower=false;//是否开启admin账户的直接访问权限，开启后就无需给admin分配资源路径权限也可以进行访问.
	
	private CasRealmCustom casRealmCustom;
	
	private PathMatcher pathMatcher=new AntPathMatcher();

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		if(openAdminPower){
			if(currentUser.hasRole("admin")){
				return true;
			}
		}
		HttpServletRequest hreq=(HttpServletRequest)request;
		try {
			AuthorizationInfo authInfo=casRealmCustom.getAuthorizationInfo(currentUser.getPrincipals());
			Collection<Permission> permissions=casRealmCustom.getPermissions(authInfo);
			boolean isPermitted=false;
			String currentPath=("["+applicationIdentity+"]:["+hreq.getServletPath()+"]").toLowerCase();
			for(Permission per:permissions){
				if(pathMatcher.match(per.toString(),currentPath)){
					isPermitted = true;
					break;
				}
			}
			if(isPermitted){
				return true;
			}
			WebUtils.issueRedirect(request, response, unauthorizedUrl);
		} catch (IOException e) {
			logger.error("Cannot redirect to unauthorized url : {}", unauthorizedUrl, e);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return false;
	}

	public String getApplicationIdentity() {
		return applicationIdentity;
	}

	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}

	public String getUnauthorizedUrl() {
		return unauthorizedUrl;
	}

	public void setUnauthorizedUrl(String unauthorizedUrl) {
		this.unauthorizedUrl = unauthorizedUrl;
	}

	public CasRealmCustom getCasRealmCustom() {
		return casRealmCustom;
	}

	public void setCasRealmCustom(CasRealmCustom casRealmCustom) {
		this.casRealmCustom = casRealmCustom;
	}

	public boolean isOpenAdminPower() {
		return openAdminPower;
	}

	public void setOpenAdminPower(boolean openAdminPower) {
		this.openAdminPower = openAdminPower;
	}

}
