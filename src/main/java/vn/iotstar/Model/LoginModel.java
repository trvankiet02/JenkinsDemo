package vn.iotstar.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
	
	@NotEmpty(message = "mail khong duoc phep de trong")
	@Email(message = "Mail khong hop le")
	private String email;
	
	@NotEmpty(message = "mat khau khong duoc de trong")
	@Min(value = 6, message = "pass tu 6 ky tu tro len")
	private String password;
	
	private Boolean rememberMe = false;

}
