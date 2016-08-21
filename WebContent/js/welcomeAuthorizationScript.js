
function validateEmail(email) {	
	var re = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;	
	if (!re.test(email)) {		
		var answer = confirm("Invalid email: " + email);
		if (answer) {
			$('#login').focus();
		}
	}    
}

function validatePassword(passwordLength) {	
	
	if (passwordLength < 4 || passwordLength > 10) {
		var answer = confirm("Invalid password. Must be more than 4 and less than 10 characters.");
		if (answer) {
			$('#password').focus();
		}
	} 
}