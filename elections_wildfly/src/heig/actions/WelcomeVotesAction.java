package heig.actions;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Result(name="success", location="page.welcome.partis", type="tiles")
public class WelcomeVotesAction extends ActionSupport {
}
