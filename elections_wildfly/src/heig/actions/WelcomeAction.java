package heig.actions;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Result(name="success", location="page.welcome", type="tiles")
public class WelcomeAction extends ActionSupport {
}
