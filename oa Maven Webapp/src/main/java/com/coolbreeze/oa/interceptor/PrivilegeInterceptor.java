package com.coolbreeze.oa.interceptor;

import com.coolbreeze.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		User user=(User) ActionContext.getContext().getSession().get("user");
		
		String url=invocation.getProxy().getActionName();
		
		if(user==null){
			
			if(url.startsWith("userAction_login")){
				return invocation.invoke();
			}else{
				return "redirectLoginUI";
			}
			
		}else{
			return invocation.invoke();
		}
		
	}

}
