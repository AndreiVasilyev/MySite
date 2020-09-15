/*---- get data with filter--------------*/

function getDataViaAjax(controller, filter) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(filter),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			displayRecords(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");
		}
	});
};

function displayRecords(data) {
	let activeTabElement=getActiveTabElement();	
	let searchResultElement=activeTabElement.querySelector('.searchResult');
	searchResultElement.innerHTML=data.totalQuantity;
	let oldRecordElements=activeTabElement.querySelectorAll('tr');
	for (let i=2;i<oldRecordElements.length;i++) {
		oldRecordElements[i].remove();
	}	
	switch (activeTabElement.dataset.tabsource) {
	case "printer":
		displayPrinterRecords(data);		
		break;
	case "cartridge":
		displayCartridgeRecords(data);		
		break;
	case "part":
		displayPartRecords(data);		
		break;
	case "comment":
		displayCommentRecords(data);		
		break;
	case "order":
		displayOrderRecords(data);		
		break;
	default:
		return;
	}
	if(data.totalQuantity>0){
		addEditLinkListeners();
		addRemoveLinkListeners();
		enableFilters(true);
	}
}

function displayPrinterRecords(data) {
	let printerRecordsHTML = '';	
	for (let i = 0; i < data.totalQuantity; i++) {
		printerRecordsHTML += '<tr><td scope="row">' + data.printers[i].id
				+ '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].vendorName + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].modelName + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].imageLink + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].popularityRating + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].isReflashable + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].reflashCost + '</td>';
		printerRecordsHTML += '<td>' + data.printers[i].originalCartridge.id
				+ '</td>';
		printerRecordsHTML += '<td class="action-width"><a href="" class="text-warning mr-1 edit-link"><i class="fa fa-pencil"></i>';
		printerRecordsHTML += '</a><a href="" class="text-danger mr-1 remove-link"><i class="fa fa-trash-o"></i></a></td></tr>';
	}	
	printersTableBody.insertAdjacentHTML('beforeend',printerRecordsHTML);	
}

function displayCartridgeRecords(data) {
	let cartridgeRecordsHTML = '';	
	for (let i = 0; i < data.totalQuantity; i++) {
		cartridgeRecordsHTML += '<tr><td scope="row">' + data.cartridges[i].id
				+ '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].modelName + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].imageLink + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].refillCost + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].isChipped + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].chipCost + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].resource + '</td>';
		cartridgeRecordsHTML += '<td>' + data.cartridges[i].tonerCapacity + '</td>';
		cartridgeRecordsHTML += '<td class="action-width"><a href="" class="text-warning mr-1 edit-link"><i class="fa fa-pencil"></i>';
		cartridgeRecordsHTML += '</a><a href="" class="text-danger mr-1 remove-link"><i class="fa fa-trash-o"></i></a></td></tr>';
	}	
	cartridgesTableBody.insertAdjacentHTML('beforeend',cartridgeRecordsHTML);	
}

function displayPartRecords(data) {
	let partRecordsHTML = '';		
	for (let i = 0; i < data.totalQuantity; i++) {		
		partRecordsHTML += '<tr><td scope="row">' + data.parts[i].id
				+ '</td>';
		partRecordsHTML += '<td>' + data.parts[i].sparePartName + '</td>';
		partRecordsHTML += '<td>' + data.parts[i].imageLink + '</td>';
		partRecordsHTML += '<td>' + data.parts[i].replacementСost + '</td>';
		partRecordsHTML += '<td class="action-width"><a href="" class="text-warning mr-1 edit-link"><i class="fa fa-pencil"></i>';
		partRecordsHTML += '</a><a href="" class="text-danger mr-1 remove-link"><i class="fa fa-trash-o"></i></a></td></tr>';
	}	
	partsTableBody.insertAdjacentHTML('beforeend',partRecordsHTML);	
}

function displayCommentRecords(data) {
	let commentRecordsHTML = '';		
	for (let i = 0; i < data.totalQuantity; i++) {	
		let date=new Date(Date.parse(data.comments[i].dateMessage));
		let newDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		let isChecked='';
		if(data.comments[i].isPublic) isChecked='checked';		
		commentRecordsHTML += '<tr><td scope="row">' + data.comments[i].id + '</td>';
		commentRecordsHTML += '<td>' + data.comments[i].authorName + '</td>';
		commentRecordsHTML += '<td>' + newDate + '</td>';
		commentRecordsHTML += '<td>' + data.comments[i].emailAddress + '</td>';
		commentRecordsHTML += '<td>' + data.comments[i].phoneNumber + '</td>';
		commentRecordsHTML += '<td>' + data.comments[i].companyName + '</td>';
		commentRecordsHTML += '<td><input type="checkbox" class="public-comment" '+isChecked+'></td>';
		commentRecordsHTML += '<td>' + data.comments[i].message + '</td></tr>';	
		
	}		
	commentsTableBody.insertAdjacentHTML('beforeend',commentRecordsHTML);	
	let comments = document.querySelectorAll('.public-comment');
	for (let comment of comments) {
		comment.onchange=function(event){
			let currentCheckBox=event.currentTarget;
			let commentId=currentCheckBox.parentElement.parentElement.firstElementChild.innerHTML;
			changeCommentPublicViaAjax(commentId,currentCheckBox);
		}
	}
}
 
function displayOrderRecords(data) {
	let orderRecordsHTML = '';		
	for (let i = 0; i < data.totalQuantity; i++) {	
		let date=new Date(Date.parse(data.orders[i].dateOrder));
		let newDate=date.getDate()+"-"+(date.getMonth()+1)+"-"+date.getFullYear()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		orderRecordsHTML += '<tr class="'+getRowColor(data.orders[i].orderCondition)+'"><td scope="row">' + data.orders[i].id + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].customerName + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].phoneNumber + '</td>';
		orderRecordsHTML += '<td>' + newDate + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].emailAddress + '</td>';		
		orderRecordsHTML += '<td>' + data.orders[i].customerAddress + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].additionalInfo + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].deviceModel + '</td>';
		orderRecordsHTML += '<td data-source="orderCondition">' + createSelectElement(data.orders[i].orderCondition, data.conditions) + '</td>';
		orderRecordsHTML += '<td>' + data.orders[i].orderSource + '</td>';	
		if(data.orders[i].orderAdditions.length>0){
			orderRecordsHTML += '<td><div class="dropdown"><a class="btn btn-success dropdown-toggle" href="#" data-toggle="dropdown">';
			orderRecordsHTML +='<i class="fa fa-plus-circle"></i></a><div class="dropdown-menu">';
			orderRecordsHTML +='<table class="table table-sm"><thead><tr><th scope="col">#</th>';
			orderRecordsHTML +='<th scope="col">PrinterName</th><th scope="col">RefillQuantity</th>';
			orderRecordsHTML +='<th scope="col">RefillPrice</th><th scope="col">isChip</th>';
			orderRecordsHTML +=' <th scope="col">chipPrice</th><th scope="col">isFlash</th>';
			orderRecordsHTML +='<th scope="col">flashPrice</th><th scope="col">flashQuantity</th>';
			orderRecordsHTML +='</tr></thead><tbody>';
			let counter=0;
			for (let element of data.orders[i].orderAdditions) {
				orderRecordsHTML +='<tr><th scope="row">'+(++counter)+'</th>';
				orderRecordsHTML +='<td>'+element.fullName+'</td>';
				orderRecordsHTML +='<td>'+element.itemsQuantity+'</td>';
				orderRecordsHTML +='<td>'+element.currentPrice+'</td>';
				orderRecordsHTML +='<td>'+element.isChips+'</td>';
				orderRecordsHTML +='<td>'+element.chipPrice+'</td>';
				orderRecordsHTML +='<td>'+element.isFlash+'</td>';
				orderRecordsHTML +='<td>'+element.flashPrice+'</td>';
				orderRecordsHTML +='<td>'+element.flashQuantity+'</td></tr>';
			}
			orderRecordsHTML +='</tbody></table></div></div></td></tr>';			
		}else {
		orderRecordsHTML += '<td></td></tr>';
		}		
			
	}		
	ordersTableBody.insertAdjacentHTML('beforeend',orderRecordsHTML);	
	let orderAttributeElements=document.querySelectorAll('.order-attribute');
	for (let element of orderAttributeElements) {
		element.onchange=function(event){
			let currentElement=event.currentTarget;
			let attributeValue=currentElement.value;
			let idValue=currentElement.parentElement.parentElement.firstElementChild.innerHTML;			
			let attribute={
				id:idValue,				
				attributeValue:attributeValue	
			};			
			changeOrderAttributeViaAjax(attribute, currentElement);			
		}
	}
}

function createSelectElement(selectedValue,list){		
	selectElement='<select class="form-control order-attribute" data-previous="'+selectedValue+'">';
	selectElement+='<option value="" label=""></option>';
	for (let selectValue of list) {
		if(selectedValue==selectValue){
			selectElement+='<option value="'+selectValue+'" label="'+selectValue+'" selected></option>';
		}else selectElement+='<option value="'+selectValue+'" label="'+selectValue+'"></option>';
	}
	return selectElement;
}

function getRowColor(condition){
	switch(condition){
	case 'RECIEVED':
		return 'bg-warning';		
	case 'ACCEPTED':
		return 'bg-info';
	case 'DELAYED':
		return 'bg-secondary';
	case 'COMPLETED':
		return 'bg-success';
	case 'CANCELED':
		return 'bg-danger';
	default:
		return '';
	}
}

/*---- remove data --------------*/

function removeDataViaAjax(controller, id) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(id),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			displayResult(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");
		}
	});
};

function displayResult(data){
	removeModalButton.classList.add('d-none');
	removeModalButton.previousElementSibling.innerHTML="Закрыть";	
	if(data.responseStatus=='OK'){
		modalMessage.innerHTML='Запись успешно удалена';
	} else{
		modalMessage.innerHTML=data.responseMessage;
	}		
	let modalButtons=document.querySelectorAll('.modal-button');
	for (let element of modalButtons) {
		element.removeAttribute('disabled');
	}		
}

/*---- find image --------------*/

function findImageViaAjax(fileName,path) {
	$.ajax({
		type : "GET",
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		url : "/admin/image",
		data : {"fileName":fileName, "path":path},
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			displayImage(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");
		}
	});
};

function displayImage(data){
	if(data.responseStatus=='OK'){		
		imageContainer.innerHTML='<img alt="'+data.responseMessage+'" src="'+data.responseMessage+'" height="50" width="50">';
		} 
	else console.log('not exist')
}

/*--------------public comments --------------------*/

function changeCommentPublicViaAjax(commentId,currentCheckBox){
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/admin/comment",
		data : JSON.stringify(commentId),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);			
		},
		error : function(e) {
			console.log("ERROR: ", e);
			cancelCommentChanges(currentCheckBox);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
};

function cancelCommentChanges(currentCheckBox){
	if(currentCheckBox.checked) {
		currentCheckBox.checked=false
	} else currentCheckBox.checked=true;
	
};

/*--------------order attributes --------------------*/

function changeOrderAttributeViaAjax(attribute,currentElement){
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url : "/admin/order/attributes",
		data : JSON.stringify(attribute),
		dataType : 'json',
		timeout : 100000,
		success : function(data){
			console.log("SUCCESS: ", data);			
				doAjaxQuery();			
		},
		error : function(e){
			console.log("ERROR: ", e);
			cancelOrderChanges(currentElement);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}

function cancelOrderChanges(currentElement){
	let previousValue=currentElement.dataset.previous;
	currentElement.value=previousValue;	
};