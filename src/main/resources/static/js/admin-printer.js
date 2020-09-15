function addSubmitFormListener(){
	newPrinterForm.onsubmit=function(){
		event.preventDefault();			
		for (let currentElement of inputСompatibleCartridges.options) {
			currentElement.selected=true;
		}	
		for (let currentElement of inputSpareParts.options) {
			currentElement.selected=true;
		}	
		newPrinterForm.submit();
	}
}

function addInputImageListener(){
	let typingTimer;
	let timerInterval=2000;
	inputImageLink.onchange=function(){
		let imageFileName=inputImageLink.value;
		let loadedFiles=attachImageLink.files;
		if(imageFileName!=''){			
			if(loadedFiles.length==0){
				let imagePath='printer';
				imageFileName=imageFileName.split('.')[0];			
				clearTimeout(typingTimer);
				typingTimer=setTimeout(findImageViaAjax(imageFileName,imagePath),timerInterval);	
			}
					
		}else{
			if(loadedFiles.length>0){
				attachImageLink.value='';
			}
			imageContainer.innerHTML='';
		}
		
	}
}





