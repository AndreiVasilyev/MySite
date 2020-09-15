function getOrderFilterInputValues(filtersInputElements,sortElement){	
	let filter={};
	if(filtersInputElements.length>=10){
		filter['id']=filtersInputElements[0].value;
		filter['customerName']=filtersInputElements[1].value;
		filter['phoneNumber']=filtersInputElements[2].value;
		filter['dateOrder']=filtersInputElements[3].value;		
		filter['emailAddress']=filtersInputElements[4].value;
		filter['customerAddress']=filtersInputElements[5].value;
		filter['additionalInfo']=filtersInputElements[6].value;
		filter['deviceModel']=filtersInputElements[7].value;
		filter['orderCondition']=filtersInputElements[8].value;
		filter['orderSource']=filtersInputElements[9].value;
		filter['orderByField']=sortElement.dataset.name;
		filter['orderDirection']=sortElement.dataset.order;
	}
	return filter;
}


function getCommentFilterInputValues(filtersInputElements,sortElement){	
	let filter={};
	if(filtersInputElements.length>=8){
		filter['id']=filtersInputElements[0].value;
		filter['authorName']=filtersInputElements[1].value;
		filter['dateMessage']=filtersInputElements[2].value;
		filter['emailAddress']=filtersInputElements[3].value;		
		filter['phoneNumber']=filtersInputElements[4].value;
		filter['companyName']=filtersInputElements[5].value;
		filter['message']=filtersInputElements[8].value;
		if(!filtersInputElements[6].checked && !filtersInputElements[7].checked){
			let currentChkElement=event.currentTarget;
			currentChkElement.checked=true;
			return 0;
		}
		if(filtersInputElements[6].checked && filtersInputElements[7].checked){
			filter['isPublic']='all';			
		}else if(filtersInputElements[6].checked && !filtersInputElements[7].checked){
			filter['isPublic']='true';			
		} else {
			filter['isPublic']='false';			
		}			
		filter['orderByField']=sortElement.dataset.name;
		filter['orderDirection']=sortElement.dataset.order;
	}
	return filter;
}

function getPartFilterInputValues(filtersInputElements,sortElement){	
	let filter={};
	if(filtersInputElements.length>=4){
		filter['id']=filtersInputElements[0].value;
		filter['sparePartName']=filtersInputElements[1].value;
		filter['imageLink']=filtersInputElements[2].value;
		filter['replacementСost']=filtersInputElements[3].value;		
		filter['orderByField']=sortElement.dataset.name;
		filter['orderDirection']=sortElement.dataset.order;
	}
	return filter;
}

function getCartridgeFilterInputValues(filtersInputElements,sortElement){	
	let filter={};	
	if(filtersInputElements.length>=8){		
		filter['id']=filtersInputElements[0].value;
		filter['modelName']=filtersInputElements[1].value;
		filter['imageLink']=filtersInputElements[2].value;
		filter['refillCost']=filtersInputElements[3].value;		
		if(!filtersInputElements[4].checked && !filtersInputElements[5].checked){
			let currentChkElement=event.currentTarget;
			currentChkElement.checked=true;
			return 0;
		}
		if(filtersInputElements[4].checked && filtersInputElements[5].checked){
			filter['isChipped']='all';			
		}else if(filtersInputElements[4].checked && !filtersInputElements[5].checked){
			filter['isChipped']='true';			
		} else {
			filter['isChipped']='false';			
		}					
		filter['chipCost']=filtersInputElements[6].value;
		filter['resource']=filtersInputElements[7].value;
		filter['tonerCapacity']=filtersInputElements[8].value;
		filter['orderByField']=sortElement.dataset.name;
		filter['orderDirection']=sortElement.dataset.order;
	}
	return filter;
}

function getPrinterFilterInputValues(filtersInputElements,sortElement){		
	let filter={};
	if(filtersInputElements.length>=8){
		filter['id']=filtersInputElements[0].value;
		filter['vendorName']=filtersInputElements[1].value;
		filter['modelName']=filtersInputElements[2].value;
		filter['imageLink']=filtersInputElements[3].value;
		filter['rating']=filtersInputElements[4].value;		
		if(!filtersInputElements[5].checked && !filtersInputElements[6].checked){
			let currentChkElement=event.currentTarget;
			currentChkElement.checked=true;
			return 0;
		}
		if(filtersInputElements[5].checked && filtersInputElements[6].checked){
			filter['isReflashable']='all';			
		}else if(filtersInputElements[5].checked && !filtersInputElements[6].checked){
			filter['isReflashable']='true';			
		} else {
			filter['isReflashable']='false';			
		}				
		filter['reflashCost']=filtersInputElements[7].value;
		filter['originCartridge']=filtersInputElements[8].value;		
		filter['orderByField']=sortElement.dataset.name;
		filter['orderDirection']=sortElement.dataset.order;
	}
	return filter;
}

function addModalWindowListeners(){
	$('#modalWindow').on('hidden.bs.modal', function() {		
		modalMessage.innerHTML='Будет удалена запись с id=<span id="idValue"></span>';
		removeModalButton.classList.remove('d-none');
		removeModalButton.previousElementSibling.innerHTML="Отмена";
		doAjaxQuery();
		});
}

function getCurrentId(currentLinkElement){
	let currentIdElement=currentLinkElement.parentElement.parentElement.firstElementChild;
	return currentIdElement.innerHTML;
}

function addRemoveLinkListeners(){
	let activeTabElement=getActiveTabElement();
	let removeLinkElements=activeTabElement.querySelectorAll('.remove-link');
	for (let element of removeLinkElements) {
		element.onclick=function(){
			event.preventDefault();
			let id=getCurrentId(event.currentTarget);
			idValue.innerHTML=id;
			removeModalButton.onclick=function(){
				let activeTabElement=getActiveTabElement();
				let tabSource=activeTabElement.dataset.tabsource;
				let controller='/admin/'+tabSource+'/remove';	
				let id=idValue.innerHTML;
				let modalButtons=document.querySelectorAll('.modal-button');
				for (let element of modalButtons) {
					element.setAttribute('disabled','disabled');
				}
				removeDataViaAjax(controller,id);				
			}			
			$('#modalWindow').modal({backdrop: 'static', keyboard: false});			
		}
	}
}

function addEditLinkListeners(){
	let activeTabElement=getActiveTabElement();
	let editLinkElements=activeTabElement.querySelectorAll('.edit-link');
	for (let element of editLinkElements) {
		element.onclick=function(){
			event.preventDefault();			
			let id=getCurrentId(event.currentTarget);
			let activeTabElement=getActiveTabElement();
			let tabSource=activeTabElement.dataset.tabsource;
			let editPageUrl='/admin/'+tabSource+'/edit?id=';
			window.location.href=editPageUrl+id;			
		}
	}
}

function addResetButtonListener(){	
	let resetButtons=document.querySelectorAll('.reset-filter-button');
	for (let resetButton of resetButtons) {
		resetButton.onclick=function(){
			let activeTabElement=getActiveTabElement();
			let filtersInputElements=activeTabElement.querySelectorAll('.filter-input');		
			if (filtersInputElements.length>0){
				for(let element of filtersInputElements){
					if(element.type=="checkbox"){
						element.checked=true;
					}else element.value="";		
				}
			doAjaxQuery();	
			}
		}	
	}
	
}

function addFilterListeners(){	
	let filtersInputElements=document.querySelectorAll('.filter-input');	
	let typingTimer;
	let timerInterval=1000;
	if (filtersInputElements.length>0){
		for(let element of filtersInputElements){
			if(element.type=="checkbox"){
				element.onchange=function(){
					clearTimeout(typingTimer);
					doAjaxQuery();
				}
			}else element.oninput=function(){
				clearTimeout(typingTimer);
				typingTimer=setTimeout(doAjaxQuery,timerInterval);				
			}
		}
	}
}

function doAjaxQuery(){		
		let activeTabElement=getActiveTabElement();			
		let tabSource=activeTabElement.dataset.tabsource;			
		let filtersInputElements=activeTabElement.querySelectorAll('.filter-input');
		let sortElement=activeTabElement.querySelector('.sort-link[data-order*="sc"]');			
		enableFilters(false);
		let filter={};				
		switch(tabSource){
			case 'printer':				
				filter=getPrinterFilterInputValues(filtersInputElements,sortElement);
				break;
			case 'cartridge':
				filter=getCartridgeFilterInputValues(filtersInputElements,sortElement);				
				break;
			case 'part':
				filter=getPartFilterInputValues(filtersInputElements,sortElement);
				break;
			case 'comment':
				filter=getCommentFilterInputValues(filtersInputElements,sortElement);
				break;
			case 'order':
				filter=getOrderFilterInputValues(filtersInputElements,sortElement);
				break;
			default:
					console.log("skip switch");				
		}
		if(filter!=0){
			let controller='/admin/'+tabSource+'/filter';				
			getDataViaAjax(controller,filter);	
		} else enableFilters(true)
}		

function addSortLinkListeners(){	
	let sortLinkListeners=document.querySelectorAll('.sort-link');
	if(sortLinkListeners.length>0){
		for (let link of sortLinkListeners) {
			link.onclick=function(){
				let currentLink=event.currentTarget;
				let currentState=currentLink.dataset.order;
				if (currentState=='asc'){
					currentLink.innerHTML='<i class="fa fa-sort-desc"></i>';
					currentLink.dataset.order='desc';
				}else {
					if(currentState==='no'){
						let activeTabElement=getActiveTabElement();
						let allLinkListeners=activeTabElement.querySelectorAll('.sort-link');
						if(allLinkListeners.length>0){
							for (let link of allLinkListeners) {
								link.innerHTML='<i class="fa fa-sort"></i>';
								link.dataset.order='no';
							}
						}
					}
					currentLink.innerHTML='<i class="fa fa-sort-asc"></i>';
					currentLink.dataset.order='asc';
				}								
				doAjaxQuery();
			}
		}
	}	
}

function enableFilters(flag){	
	let activeTabElement=getActiveTabElement();
	let filtersInputElements=activeTabElement.querySelectorAll('.filter-input');
	if (filtersInputElements.length>0){
		for(let element of filtersInputElements){
			if(flag){
				element.removeAttribute("disabled");
			}else element.setAttribute("disabled","disabled");
		}
	}
}

function getActiveTabElement(){
	return document.querySelector('.tab-pane.show.active');
}

function isRecordsEmpty(){
	let activeTabElement=getActiveTabElement();
	let recordsCount=activeTabElement.querySelector(".searchResult").innerHTML;
	return (recordsCount>0)? false:true;	
}

function addTabChangeListeners(){
	let tabElements=document.querySelectorAll('a[data-toggle="tab"]');
	for (let element of tabElements) {
		$(element).on('hide.bs.tab', function (event) {
			let previousTabElement=getActiveTabElement();			
			let oldRecordElements=previousTabElement.querySelectorAll('tr');
			for (let i=2;i<oldRecordElements.length;i++) {
				oldRecordElements[i].remove();
			}				
		});
		$(element).on('shown.bs.tab', function (event) {			
			let currentTabElement=getActiveTabElement();			
			document.cookie="tab="+currentTabElement.id;			
			doAjaxQuery();			
		});
		
	}
	
}

function ready(){	
	addTabChangeListeners();
	let currentTab=document.querySelector('.current-tab').innerHTML;		
	if(currentTab=='printerstab'){
		doAjaxQuery();	
	}else{
		let targetCSS='a[href="#'+currentTab+'"]';
		let targetTabLink=document.querySelector(targetCSS);		
		$(targetTabLink).tab('show');
	}		
	addSortLinkListeners();		
	addFilterListeners();		
	addResetButtonListener();		
	addModalWindowListeners();			
}

document.addEventListener("DOMContentLoaded",ready);
