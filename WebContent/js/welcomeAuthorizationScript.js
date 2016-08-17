
function validateEmail(email) {	
	var re = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;	
	if (!re.test(email)) {		
		var answer = confirm("Invalid email: " + email);
		if (answer) {
			$('#login').focus();
		}
	}    
}