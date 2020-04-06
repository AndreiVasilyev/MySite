//--------------------------Валидация формы добавления комментария----------

var titleInput = document.getElementById('inputTitle');
var messageInput = document.getElementById('inputMessage');



createCustomValidation(titleInput, titleMessageEmailChecks);
createCustomValidation(messageInput, messageEmailChecks);


var checkNewMessageForm = function() {
	var isTitleCorrect = titleInput.classList.contains('is-valid');	
	var isMessageCorrect = messageInput.classList.contains('is-valid');
	if (isTitleCorrect && isMessageCorrect) {
		document.getElementById('formSubbmit').removeAttribute('disabled');
	}
}

function createCustomValidation(input, checks) {
	input.CustomValidation = new CustomValidation();
	input.CustomValidation.validityChecks = checks;
}

//-------------------------Обработчики событий-----------------

window.onload = function() {
	var inputElements = document.querySelectorAll('.form-control');
	var currentSubbmitButton = document.getElementById('formSubbmit');
	var inputFilesElement=document.getElementById('inputFile');
	for (var i = 0; i < inputElements.length; i++) {
		inputElements[i].onblur = function() {
			checkInput(this, checkNewMessageForm, currentSubbmitButton);
		};
		inputElements[i].onfocus = function() {
			currentSubbmitButton.setAttribute('disabled', 'disabled');
			this.classList.remove('is-invalid');
			this.classList.remove('is-valid');
		};
	};	
	inputFilesElement.onchange=function(){
		var files=document.getElementById('inputFile').files;
		document.querySelector('.file-names').innerHTML="";
		for(var i=0;i<files.length;i++){
			var newSpanElement=document.createElement('span');
			newSpanElement.className = "ml-2";
			var comma=(i!=0)?',':'';
			newSpanElement.innerHTML=files[i].name+comma;
			document.querySelector('.file-names').prepend(newSpanElement);			
		};
	};	
	currentSubbmitButton.setAttribute('disabled', 'disabled');	
	console.log($('#responseText').text());
	if ($('#responseText').text() != '') {
		$('#confirmOrderModal').modal('show');
	};
};



