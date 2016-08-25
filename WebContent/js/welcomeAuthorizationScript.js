function validateEmail(email) {
	var lang = $('#language').val();

	if (email != '') {
		var pattern = /^([a-z0-9_\-]+\.)*[a-z0-9_\-]+@([a-z0-9][a-z0-9\-]*[a-z0-9]\.)+[a-z]{2,6}$/i;
		if (pattern.test(email)) {
			$('#login').css({
				'border' : '2px solid #569b44'
			});
			if (lang === 'ru') {
				$('#validEmail').text('Верно');
			} else if (lang === 'en') {
				$('#validEmail').text('True');
			}

		} else {
			$('#login').css({
				'border' : '2px solid #ff0000'
			});
			if (lang == 'ru') {
				$('#validEmail').text('Некорректный e-mail');
			} else if (lang == 'en') {
				$('#validEmail').text('Incorrect e-mail');
			}
		}
	} else {
		$('#login').css({
			'border' : '2px solid #ff0000'
		});
		if (lang === 'ru') {
			$('#validEmail').text('Поле "пароль" не должно быть пустым');
		} else if (lang === 'en') {
			$('#validEmail').text('The "Email" must not be empty');
		}

	}
}

function validatePassword(passwordLength) {

	var lang = $('#language').val();
	if (passwordLength < 4 || passwordLength > 10) {
		$('#password').css({
			'border' : '2px solid #ff0000'
		});

		if (lang === 'ru') {
			$('#validPassword').text('Должно быть больше 3 и меньше 11 символов');
		} else if (lang === 'en') {
			$('#validPassword').text('Must be more than 3 and less than 11 characters');
		}
	} else {
		$('#password').css({
			'border' : '2px solid #569b44'
		});
		if (lang === 'ru') {
			$('#validPassword').text('Верно');
		} else if (lang === 'en') {
			$('#validPassword').text('True');
		}

	}
}