function addRemoveListeners(){
	let removeElements=document.querySelectorAll('.btn-danger');
	for (let element of removeElements) {
		element.onclick=function(){				
			let currentTargetSelectElement=event.currentTarget.parentElement.nextElementSibling.firstElementChild;
			let selectedElements=[];
			for (let currentElement of currentTargetSelectElement.options) {
				if(currentElement.selected){						
					selectedElements.push(currentElement);				
				}
			}
			for (let selectedElement of selectedElements) {										
				currentTargetSelectElement.removeChild(selectedElement);
			}		
		}
	}
}

function addAddListeners(){
	let addElements=document.querySelectorAll('.btn-success');
	for (let element of addElements) {
		element.onclick=function(){	
			let currentSourceSelectElement=event.currentTarget.parentElement.previousElementSibling.firstElementChild;
			let currentTargetSelectElement=event.currentTarget.parentElement.nextElementSibling.firstElementChild;
			outerLoop:
			for (let element of currentSourceSelectElement.options) {
				if(element.selected){	
					for (let selectedElement of currentTargetSelectElement.options) {						
						if(selectedElement.value==element.value) continue outerLoop;						
					}
					currentTargetSelectElement.append(new Option(element.text,element.value,true,true));
				}
			}			
		}
	}
}

function addAttachFileListener(){
	attachImageLink.onchange=function(){
		let file=this.files;
		if(file.length>0){
			inputImageLink.value=this.files[0].name;
			let path=URL.createObjectURL(this.files[0]);
			imageContainer.innerHTML='<img alt="'+path+'" src="'+path+'" height="50" width="50">';
		}
	}	
}

document.addEventListener("DOMContentLoaded",function(){	
	addAddListeners();
	addRemoveListeners();
	addAttachFileListener();
	addSubmitFormListener();
	addInputImageListener();
})