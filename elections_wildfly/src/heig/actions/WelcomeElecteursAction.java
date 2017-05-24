package heig.actions;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Result(name="success", location="page.welcome.electeurs", type="tiles")
public class WelcomeElecteursAction extends ActionSupport {
}
