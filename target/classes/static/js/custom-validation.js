function CustomValidation() {
	this.invalidities = [];
	this.validityChecks = [];
}

CustomValidation.prototype = {
	addInvalidity : function(message) {
		this.invalidities.push(message);
	},
	getInvalidities : function() {
		return this.invalidities;
	},
	checkValidity : function(input) {
		for (var i = 0; i < this.validityChecks.length; i++) {
			var isInvalid = this.validityChecks[i].isInvalid(input);
			if (isInvalid) {
				this.addInvalidity(this.validityChecks[i].invalidityMessage);
			}
		}
	}
};

var authorNameValidityChecks = [ {
	isInvalid : function(input) {
		return input.value.length < 2;
	},
	invalidityMessage : 'Имя(Фамилия) должны сожержать минимум 2 символа'
}, {
	isInvalid : function(input) {
		var illegalCharacters = input.value.match(/[^a-zA-Zа-яА-Я. ]/g);
		return illegalCharacters ? true : false;
	},
	invalidityMessage : 'Поле содержит недопустимые символы'
} ];

var companyNameValidityChecks = [
		{
			isInvalid : function(input) {
				return input.value.length == 1;
			},
			invalidityMessage : 'Название компании должно сожержать минимум 2 символа'
		},
		{
			isInvalid : function(input) {
				var illegalCharacters = input.value
						.match(/[^a-zA-Zа-яА-Я0-9. &!@#$%^_№"~':,/\|*)(-]/g);
				return illegalCharacters ? true : false;
			},
			invalidityMessage : 'Поле содержит недопустимые символы'
		} ];

var customerAddressValidityChecks = [ {
	isInvalid : function(input) {
		return input.value.length < 5;
	},
	invalidityMessage : 'Поле адреса  должно сожержать минимум 5 символов'
}, {
	isInvalid : function(input) {
		var isLegalAddress = input.value.match(/[a-zA-Zа-яА-Я0-9. #№"':,/-]/g);
		return isLegalAddress ? false : true;
	},
	invalidityMessage : 'Поле адреса содержит недопустимые символы'
} ];

var emailAddressValidityChecks = [
		{
			isInvalid : function(input) {
				return input.value.length < 5 && input.value.length > 0;
			},
			invalidityMessage : 'Поле email  должен сожержать минимум 5 символов'
		},
		{
			isInvalid : function(input) {
				var isLegalEmail = input.value.length == 0
						|| input.value.match(/.+@.+\..+/i);
				return isLegalEmail ? false : true;
			},
			invalidityMessage : 'Недопустимый формат email'
		} ];

var phoneNumberValidityChecks = [
		{
			isInvalid : function(input) {
				return input.value.length < 18;
			},
			invalidityMessage : 'Поле обязательное к заполнению'
		},
		{
			isInvalid : function(input) {
				var isLegalPhone = input.value
						.match(/\+375\([1-9]{2}\) [1-9][0-9]{2}(-[0-9]{2}){2}/);
				return isLegalPhone ? false : true;
			},
			invalidityMessage : 'Недопустимый формат номера телефона'
		} ];

var commentValidityChecks = [ {
	isInvalid : function(input) {
		return input.value.length == 0;
	},
	invalidityMessage : 'Поле обязательное к заполнению'
}, {
	isInvalid : function(input) {
		return input.value.length < 20;
	},
	invalidityMessage : 'Сообщение должно содержать не менее 20 символов'
} ];

var addInfoValidityChecks = [ {
	isInvalid : function(input) {
		return input.value.length < 5 && input.value.length > 0;
	},
	invalidityMessage : 'Поле дополнительной информации  не может содержать менее 5 символов'
} ];

var deviceModelValidityChecks = [ {
	isInvalid : function(input) {
		return input.value.length < 2 && input.value.length > 0;
	},
	invalidityMessage : 'Поле модели  не может содержать менее 2-х символов'
} ];

var titleMessageEmailChecks = [ {
	isInvalid : function(input) {
		return input.value.length == 0;
	},
	invalidityMessage : 'Поле обязательное к заполнению'
}];
var messageEmailChecks = [ {
	isInvalid : function(input) {
		return input.value.length == 0;
	},
	invalidityMessage : 'Поле обязательное к заполнению'
}]; 

function checkInput(input, checkCurrentFormFunction, currentSubbmit) {

	input.CustomValidation.invalidities = [];
	input.CustomValidation.checkValidity(input);
	input.nextSibling.nextSibling.innerHTML = '';
	if (input.CustomValidation.invalidities.length == 0) {
		input.classList.remove('is-invalid');
		input.classList.add('is-valid');		
		checkCurrentFormFunction();
	} else {		
		input.classList.add('is-invalid');
		input.classList.remove('is-valid');
		var message = input.CustomValidation.getInvalidities();
		for (var i = 0; i < message.length; i++) {
			input.nextSibling.nextSibling.innerHTML += '<p class="d-block mb-0">\u2022\u0020'
					+ message[i] + '</p>';
		}
		currentSubbmit.setAttribute('disabled', 'disabled');

	}

}