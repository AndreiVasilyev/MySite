//----Ajax запросы добавления/удаления в корзину в cart.jsp--------


function addCartItemViaAjax(cartItem, controller) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(cartItem),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			displayCart(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");

		}
	});
};
function removeCartItemViaAjax(controller) {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			displayCartItems(data);
			addCartListeners();
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");

		}
	});
};

function displayCart(data) {
	totalQuantity.innerHTML = data.totalItemsQuantity;
	totalCost.innerHTML = "Сумма: " + data.totalCost + " руб.";
};

function displayCartItems(data){
	var cartItemsHTML = '';	
	if (data.errorMessage == '') {
		if(data.cartItems.length!=0){
			for(var i=0; i<data.cartItems.length;i++){
				if(data.cartItems[i].itemsQuantity>0){
					cartItemsHTML += '<div class="row align-items-center justify-content-end mt-4">';
					cartItemsHTML += '<div class="hidden">'+data.cartItems[i].printer.id+'</div>';
					cartItemsHTML += '<div class="col-3 col-md-2 col-lg-1 order-2 order-lg-1 p-0 text-right">';
					cartItemsHTML += '<img class="rounded w-75" alt="printer image"	src="/images/printers/'+data.cartItems[i].printer.imageLink+'">';
					cartItemsHTML += '</div><div class="col-9 col-md-2 order-3 order-lg-2 text-center">';
					cartItemsHTML += '<span>'+data.cartItems[i].printer.vendorName+' '+data.cartItems[i].printer.modelName+'</span>';
					cartItemsHTML += '</div><div class="col-9 col-md-2 col-lg-2  order-4 order-lg-3 mb-2 mb-md-0 text-center">';
					cartItemsHTML += '<span>Заправка</span></div>';
					cartItemsHTML += '<div class="col-5 col-md-2 order-6 order-lg-4 text-left text-lg-right">';
					cartItemsHTML += '<span class="cost-style">'+data.cartItems[i].currentPrice+'<span> руб.</span></span>';
					cartItemsHTML += '</div><div class="col-4 col-md-1 order-5 text-right">';
					cartItemsHTML += '<input class="form-check-input chip-checkboxes';		
					if(!data.cartItems[i].printer.originalCartridge.isChipped){
						cartItemsHTML += ' d-none';
					};							
					cartItemsHTML += '" type="checkbox" id="chipCheckbox"';
					if(data.cartItems[i].isChips){
						cartItemsHTML += ' checked';
					};
					cartItemsHTML += '><label class="form-check-label';					
					if(!data.cartItems[i].printer.originalCartridge.isChipped){
						cartItemsHTML += ' d-none';
					};		
					cartItemsHTML += '"	for="chipCheckbox">чип</label><div class="hidden">';
					if(data.cartItems[i].printer.originalCartridge.isChipped){
						cartItemsHTML += data.cartItems[i].printer.originalCartridge.chipCost;
					} else {
						cartItemsHTML += '0';
					};
					cartItemsHTML += '</div></div><div class="col-9 col-md-3 col-lg-1 order-7 order-lg-6  p-0 input-group justify-content-center px-2 px-lg-0 my-2">';
					cartItemsHTML += '<div class="input-group-prepend"><button class="quantityValueBtn minusValueBtn px-0" disabled>-</button>';
					cartItemsHTML += '</div><input style="text" value="'+data.cartItems[i].itemsQuantity+'" class="form-control text-center quantityValueInput" disabled>';
					cartItemsHTML += '<div class="input-group-append"><button class="quantityValueBtn plusValueBtn px-0">+</button></div></div>';
					cartItemsHTML += '<div class="col-5 col-md-2 order-8 order-lg-7 text-left text-lg-center">';
					cartItemsHTML += '<span class="cost-style cost-selector">';
					if(data.cartItems[i].isChips){
						cartItemsHTML += data.cartItems[i].itemsQuantity*data.cartItems[i].currentPrice+data.cartItems[i].itemsQuantity*data.cartItems[i].printer.originalCartridge.chipCost;
					} else{
						cartItemsHTML +=data.cartItems[i].itemsQuantity*data.cartItems[i].currentPrice;
					};
					cartItemsHTML += '<span> руб.</span></span></div><div class="col-12 col-lg-1 order-1 order-lg-8 mb-2 mb-lg-0">';
					cartItemsHTML += '<button type="button" class="close removeItemButton float-left"><span>&times;</span></button></div></div>';
					
				};
				if(data.cartItems[i].isFlash){
					cartItemsHTML += '<div class="row align-items-center justify-content-end mt-4">';
					cartItemsHTML += '<div class="hidden">'+data.cartItems[i].printer.id+'</div>';
					cartItemsHTML += '<div class="col-3 col-md-2 col-lg-1 order-2 order-lg-1 p-0 text-right">';
					cartItemsHTML += '<img class="rounded w-75" alt="printer image"	src="/images/printers/'+data.cartItems[i].printer.imageLink+'">';
					cartItemsHTML += '</div><div class="col-9 col-md-2 order-3 order-lg-2 text-center">';
					cartItemsHTML += '<span>'+data.cartItems[i].printer.vendorName+' '+data.cartItems[i].printer.modelName+'</span></div>';
					cartItemsHTML += '<div class="col-9 col-md-2 col-lg-2  order-4 order-lg-3 mb-2 mb-md-0 text-center">';
					cartItemsHTML += '<span>Прошивка</span></div><div class="col-5 col-md-2 order-6 order-lg-4 text-left text-lg-right">';
					cartItemsHTML += '<span class="cost-style">'+data.cartItems[i].printer.reflashCost+'<span> руб.</span></span>';
					cartItemsHTML += '</div><div class="col-4 col-md-1 order-5 text-right"></div>';
					cartItemsHTML += '<div class="col-9 col-md-3 col-lg-1 order-7 order-lg-6  p-0 input-group justify-content-center px-2 px-lg-0 my-2">';
					cartItemsHTML += '<div class="input-group-prepend"><button class="quantityValueBtn minusValueBtn px-0" disabled>-</button>';
					cartItemsHTML += '</div><input style="text" value="1" class="form-control text-center quantityValueInput" disabled>';
					cartItemsHTML += '<div class="input-group-append"><button class="quantityValueBtn plusValueBtn px-0">+</button></div>';
					cartItemsHTML += '</div><div class="col-5 col-md-2 order-8 order-lg-7 text-left text-lg-center">';
					cartItemsHTML += '<span class="cost-style cost-selector">'+data.cartItems[i].printer.reflashCost+'<span> руб.</span></span>';
					cartItemsHTML += '</div><div class="col-12 col-lg-1 order-1 order-lg-8 mb-2 mb-lg-0">';
					cartItemsHTML += '<button type="button" class="close removeItemButton float-left"><span>&times;</span></button></div></div>';
										
				};
			};
			cartItemsHTML += '<div class="row align-items-center my-3"><div class="col-12 col-md-6 text-left my-3 my-md-0">';
			cartItemsHTML += '<span class="cost-style"><span>Итого: </span>';
			if(data.totalCost==null){
				cartItemsHTML += '0';
			} else{
				cartItemsHTML += data.totalCost;
			}	
			cartItemsHTML += '<span> руб.</span></span></div><div class="col-12 col-md-6 text-right">';
			cartItemsHTML += '<a href="#" class="mr-2 btn order-button">Оформить заказ</a></div></div>';
		} else{			
			cartItemsHTML += '<div class="row  mt-4 align-items-center" style="height: 10rem">';
			cartItemsHTML += '<div class="col text-center"><span>корзина пуста</span></div></div>';			
		};
		totalQuantity.innerHTML = data.totalItemsQuantity;
		totalCost.innerHTML = "Сумма: " + data.totalCost + " руб.";
		var cartItemsBlock=document.querySelector('.cart-block');
		cartItemsBlock.innerHTML=cartItemsHTML;
	};
};





function getPageViaAjax(controller, flag) {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);
			if (flag == 1) {				
				displayCount(data);
			} else {				
				display(data);
			}
			addLinksListener();
			addCardListeners();
		},
		error : function(e) {
			console.log("ERROR: ", e);
			displayError(e);
		},
		done : function(e) {
			console.log("DONE");
			enableLinkPaginationElements(true);
		}
	});

};

//-----------------------AJAX запросы блока пагинации в costs.jsp-------------

function enableLinkPaginationElements(flag) {
	$(".page-link").prop("disabled", flag);
	$("#vendorSelector").prop("disabled", flag);
};



function displayError(e) {
	document.querySelector('.card-block').innerHTML = "неудалось получить ответ сервера";
};

function display(data) {
	var cardBlockHTML = '';	
	if (data.errorMessage == '') {		
		cardBlockHTML += '<div class="container">';
		for (var i = 0; i < data.printersPage.length; i++) {
			if (i == 0 || i % 4 == 0) {
				cardBlockHTML += '<div class="row mt-4 justify-content-center">';
			}
			cardBlockHTML += '<div class="col-9 col-sm-6 col-md-3 p-0"><div class="card-item  text-center mx-auto mb-3">';

			cardBlockHTML += '<div class="front h-100 w-100"><div hidden="true">'
					+ data.printersPage[i].id
					+ '</div><div class="card-header">'
					+ data.printersPage[i].vendorName + '</div>';
			cardBlockHTML += '<img class="card-img-top w-50 mx-auto rounded" alt="printr image" src="/images/printers/'
					+ data.printersPage[i].imageLink + '">';
			cardBlockHTML += '<div class="card-body"><div class="card-title">'
					+ data.printersPage[i].modelName + '</div>';
			cardBlockHTML += '<div class="card-text">Заправка</div><div class="card-text">от <span class="refillCost">';
			cardBlockHTML += (data.printersPage[i].originalCartridge.refillCost - 2.5)
					+ '</span> руб.</div>';
			cardBlockHTML += '<div class="card-text">Прошивка/чип</div><div class="card-text">';
			if (!data.printersPage[i].isReflashable
					&& !data.printersPage[i].originalCartridge.isChipped) {
				cardBlockHTML += 'не требуется';
			} else {
				var reflashCost = data.printersPage[i].reflashCost;
				var chipCost = data.printersPage[i].originalCartridge.chipCost;
				if (reflashCost == null || (chipCost!=null && reflashCost > chipCost)) {
					cardBlockHTML += 'чип от ' + chipCost + ' руб.';
				} else {
					cardBlockHTML += 'прошивка от ' + reflashCost + ' руб.';
				}
			}
			cardBlockHTML += '</div></div><div class="card-footer"><a class="btn w-100 choose-button">Выбрать</a></div></div>';
			cardBlockHTML += '<div class="back h-100 w-100"><div class="card-header">'
					+ data.printersPage[i].originalCartridge.modelName;
			cardBlockHTML += '<i class="fa fa-undo back-link"></i></div><img class="card-img-top w-25 mx-auto rounded" alt="cartridge image" ';
			cardBlockHTML += 'src="/images/cartridges/'
					+ data.printersPage[i].originalCartridge.imageLink + '">';
			cardBlockHTML += '<div class="card-body"><div class="card-title mb-0">Заправка</div><div class="card-text row m-0">';
			cardBlockHTML += '<div class="col-6 text-right p-1">1 шт.</div><div class="col-6 text-left p-1">';
			cardBlockHTML += data.printersPage[i].originalCartridge.refillCost
					+ '<span> руб.</span></div></div>';
			cardBlockHTML += '<div class="card-text row m-0"><div class="col-6 text-right p-1">2-4 шт.</div>';
			cardBlockHTML += '<div class="col-6 text-left p-1">'
					+ (data.printersPage[i].originalCartridge.refillCost - 0.5)
					+ '<span> руб.</span></div>';
			cardBlockHTML += '</div><div class="card-text row m-0"><div class="col-6 text-right p-1">5-10 шт.</div>';
			cardBlockHTML += '<div class="col-6 text-left p-1">'
					+ (data.printersPage[i].originalCartridge.refillCost - 1.5)
					+ '<span> руб.</span></div>';
			cardBlockHTML += '</div><div class="card-text row m-0"><div class="col-6 text-right p-1">> 10 шт.</div>';
			cardBlockHTML += '<div class="col-6 text-left p-1">'
					+ (data.printersPage[i].originalCartridge.refillCost - 2.5)
					+ '<span> руб.</span></div>';
			cardBlockHTML += '</div><div class="card-title mb-0">Прошивка/чип</div>';

			if (!data.printersPage[i].isReflashable
					&& !data.printersPage[i].originalCartridge.isChipped) {
				cardBlockHTML += '<div class="card-text" id="emptyCost">не требуется</div></div>';
			} else {
				if (data.printersPage[i].reflashCost != null) {
					cardBlockHTML += '<div class="card-text row m-0" id="flashCost"><div class="form-check form-check-inline col-6 text-right justify-content-end p-1 m-0">';
					cardBlockHTML += '<input class="form-check-input" type="checkbox" id="reflashCheckbox'
							+ i + '" value="false">';
					cardBlockHTML += '<label class="form-check-label" for="reflashCheckbox'
							+ i + '">прошивка</label>';
					cardBlockHTML += '</div><div class="col-6 text-left p-1">'
							+ data.printersPage[i].reflashCost
							+ ' <span> руб.</span></div></div></div>';
				}
				if (data.printersPage[i].originalCartridge.chipCost != null) {
					cardBlockHTML += '<div class="card-text row m-0" id="chipCost"><div class="form-check form-check-inline col-6 text-right justify-content-end p-1 m-0">';
					cardBlockHTML += '<input class="form-check-input" type="checkbox" id="chipCheckbox'
							+ i + '" value="false">';
					cardBlockHTML += '<label class="form-check-label" for="chipCheckbox'
							+ i + '">чип</label></div>';
					cardBlockHTML += '<div class="col-6 text-left p-1">'
							+ data.printersPage[i].originalCartridge.chipCost
							+ '<span> руб.</span></div></div></div>';					
				}
			}

			cardBlockHTML += '<div class="card-footer"><div class="card-total-cost">0 <i class="fa fa-rub"></i></div>';
			cardBlockHTML += '<div class="container"><div class="row"><div class="col-4 p-0 input-group">';
			cardBlockHTML += '<div class="input-group-prepend"><button class="quantityBtn minusBtn px-0" disabled>-</button></div>';
			cardBlockHTML += '<input style="" value="0" class="form-control px-0 text-center quantityInput"><div class="input-group-append">';
			cardBlockHTML += '<button class="quantityBtn plusBtn px-0">+</button></div></div><div class="col-8 p-0 pl-1">';
			cardBlockHTML += '<a class="btn choose-button w-100">В корзину</a></div></div></div></div></div></div></div>';
			if (i == (data.printersPage.length - 1) || (i + 5) % 4 == 0) {
				cardBlockHTML += '</div>';
			}

		}
		cardBlockHTML += '</div>';
	} else {
		cardBlockHTML += data.errorMessage;
	}
	document.querySelector('.card-block').innerHTML = cardBlockHTML;
	var listPaginationHTML = '';
	if (data.totalPages > 1) {
		listPaginationHTML += '<nav><ul class="pagination pagination-sm justify-content-center"><li class="page-item';
		listPaginationHTML += (data.pageNumber == 0) ? ' disabled' : '';
		listPaginationHTML += '"><a class="page-link" href="#" data-index="0">Первая</a></li><li class="page-item';
		listPaginationHTML += (data.pageNumber == 0) ? ' disabled' : '';
		listPaginationHTML += '"><a class="page-link" href="#" data-index="'
				+ (data.pageNumber - 1) + '">';
		listPaginationHTML += '<span>&#171;</span></a></li>';
		var pagesLable = (data.totalPages > 2 && data.pageNumber == (data.totalPages - 1)) ? data.pageNumber - 2
				: data.pageNumber - 1;
		for (var i = 0; (data.totalPages > 2) ? i < 3 : i < 2; i++) {
			listPaginationHTML += '<li class="page-item';
			listPaginationHTML += ((data.pageNumber == 0 && data.pageNumber == i)
					|| (data.pageNumber > 0 && data.pageNumber == pagesLable) ? ' active'
					: '');
			listPaginationHTML += '"><a class="page-link" href="#" data-index="'
					+ (data.pageNumber == 0 ? i : pagesLable) + '">';
			listPaginationHTML += data.pageNumber == 0 ? (i + 1)
					: (pagesLable + 1) + '</a></li>';
			pagesLable++;
		}
		listPaginationHTML += '<li class="page-item'
				+ (data.pageNumber == (data.totalPages - 1) ? ' disabled' : '')
				+ '">';
		listPaginationHTML += '<a class="page-link" href="#" data-index="'
				+ (data.pageNumber + 1) + '"><span>&#187;</span>';
		listPaginationHTML += '</a></li><li class="page-item'
				+ (data.pageNumber == (data.totalPages - 1) ? ' disabled' : '')
				+ '">';
		listPaginationHTML += '<a class="page-link" href="#" data-index="'
				+ (data.totalPages - 1) + '">Последняя</a></li></ul>';
		listPaginationHTML += '</nav>';
	}
	;
	document.querySelector('.listPagination').innerHTML = listPaginationHTML;
};
function displayCount(data) {
	document.getElementById('foundValue').innerHTML = data.printerCount;
	document.getElementById('collapseCountValue').classList.add('show');
	if (data.printerCount > 0) {
		document.getElementById("showResultsButton")
				.removeAttribute("disabled");
	}

};

//----Ajax запросы обновление корзины в cart.jsp--------


function updateCartItemViaAjax(item, controller) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : controller,
		data : JSON.stringify(item),
		dataType : 'json',
		timeout : 100000,
		success : function(data) {
			console.log("SUCCESS: ", data);	
			displayCart(data);
		},
		error : function(e) {
			console.log("ERROR: ", e);

		},
		done : function(e) {
			console.log("DONE");

		}
	});
};


