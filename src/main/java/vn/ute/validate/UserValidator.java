package vn.ute.validate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//import inventory.model.Users;
//import vn.ute.service.UserService;
import vn.ute.service.UserWebService;
import vn.ute.entity.UserWeb;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserWebService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == UserWeb.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserWeb user = (UserWeb) target;
		ValidationUtils.rejectIfEmpty(errors, "userName", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
		if(user.getId()==null) {
			ValidationUtils.rejectIfEmpty(errors, "name", "msg.required");
		}
		List<UserWeb> users = userService.findByProperty("userName", user.getName());
		if (users != null && !users.isEmpty()) {
			errors.rejectValue("userName", "msg.username.exist");
		}
	}

}
