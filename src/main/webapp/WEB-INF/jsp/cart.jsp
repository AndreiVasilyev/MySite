<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/css/common.css"/>">
<link rel="stylesheet" href="<c:url value="/css/cart.css"/>">
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<title>QHP (Quick help for printing)</title>
</head>

<body id="topBody">
	<c:url var="indexUrl" value="/"></c:url>
	<c:url var="commentsUrl" value="/comments"></c:url>
	<c:url var="conditionsUrl" value="/conditions"></c:url>
	<c:url var="organizationsUrl" value="/organizations"></c:url>
	<c:url var="blogUrl" value="/blog"></c:url>
	<c:url var="costsUrl" value="/costs"></c:url>
	<div class="modal fade" id="confirmOrderModal" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header pb-0 my-modal-border">
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body text-center">
					<p id='responseText'></p>
				</div>
				<div class="modal-footer my-modal-border">
					<button type="button" class="btn order-button" data-dismiss="modal">Закрыть</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="callOrderModal" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header pb-0 my-modal-border">
					<div class="text-center p-0">
						<h5 class="modal-title">Заказать звонок</h5>
						<span class="d-block">(оставьте номер телефона и мы
							перезвоним Вам в кратчайшие сроки)</span>
					</div>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="callback-form">
						<div class="form-row">
							<div class="form-group col-12 mb-0">
								<label for="customerNameCall" class="required"></label> <input
									placeholder="Введите Имя"
									class="input-element p-4 form-control" required="required"
									id="customerNameCall">
								<div class="invalid-feedback"></div>
							</div>
							<div class="form-group col-12 mb-0">
								<label for="phoneNumberCall" class="required"></label> <input
									type="tel" placeholder="+375(ХХ) ХХХ-ХХ-ХХ"
									class="input-element p-4 form-control" required="required"
									id="phoneNumberCall">
								<div class="invalid-feedback"></div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer my-modal-border">
					<button type="button" id="callOrderSubbmit"
						class="btn order-button" data-dismiss="modal">Отправить</button>
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade" id="quickOrderModal" data-backdrop="static">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header pb-0 my-modal-border">
					<div class="text-center p-0">
						<h5 class="modal-title">Быстрый заказ</h5>
					</div>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="quick-order-form">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="customerName" class="required">Имя(Фамилия)</label>
								<input placeholder="Введите Имя" required="required"
									class="input-element form-control" id="customerName">
								<div class="invalid-feedback"></div>
							</div>
							<div class="form-group col-md-6">
								<label for="phoneNumber" class="required">Телефон</label> <input
									type="tel" placeholder="+375()" required="required"
									class="input-element form-control" id="phoneNumber">
								<div class="invalid-feedback"></div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="customerAddress" class="required">Адрес</label> <input
									placeholder="Введите адрес" class="input-element form-control"
									required="required" id="customerAddress">
								<div class="invalid-feedback"></div>
							</div>
							<div class="form-group col-md-6">
								<label for="deviceModel">Модель</label> <input
									placeholder="Введите модель устройства"
									class="input-element form-control" id="deviceModel">
								<div class="invalid-feedback"></div>
							</div>
							<div class="form-group col-12">
								<label for="addInfo">Дополнительная информация</label>
								<textarea rows="5"
									placeholder="Удобное время визита, как найти, описание проблемы и т.д."
									class="input-element form-control" id="addInfo"></textarea>
								<div class="invalid-feedback"></div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer my-modal-border">
					<button type="button" class="btn order-button" data-dismiss="modal"
						id="quickOrderSubbmit">Отправить</button>
				</div>
			</div>
		</div>
	</div>
	<div class="fix-btn-container">
		<div class="callback-btn fix-btn">
			<a href="#callOrderModal" data-toggle="modal"
				data-target="#callOrderModal">
				<div class="text-fix-btn">
					<i class="fa fa-phone"></i> <span>Заказать<br>звонок
					</span>
				</div>
			</a>
		</div>
		<div class="quickorder-btn fix-btn">
			<a href="#quickOrderModal" data-toggle="modal"
				data-target="#quickOrderModal">
				<div class="text-fix-btn">
					<i class="fa fa-list-alt"></i> <span>Быстрый<br>заказ
					</span>
				</div>
			</a>
		</div>
	</div>
	<div id="back-top" data-toggle="tooltip" title="Наверх">
		<a href="#topBody">
			<div>
				<i class="fa fa-arrow-circle-o-up"></i><br>
			</div>
		</a>
	</div>
	<div class="topLine text-center mb-2 sticky-top">
		<nav class="navbar navbar-light navbar-expand-md p-0">
			<div class="container nav-container">
				<div class="row w-100">
					<div class="col text-left  py-2">
						<button type="button" class="navbar-toggler mb-1 ml-2"
							data-toggle="collapse" data-target="#navbar-main">
							<i class="fa fa-bars"></i>
						</button>
						<div class="collapse navbar-collapse" id="navbar-main">
							<ul class="navbar-nav ">
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${indexUrl}" class="main-nav nav-link ">Главная</a></li>
								<li class="nav-item d-none d-md-inline"><span
									class="nav-link nav-delimiter">|</span></li>
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${conditionsUrl}" class="main-nav nav-link">Условия</a></li>
								<li class="nav-item d-none d-md-inline"><span
									class="nav-link nav-delimiter">|</span></li>
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${costsUrl}" class="nav-link main-nav">Цены</a></li>
								<li class="nav-item d-none d-md-inline"><span
									class="nav-link nav-delimiter">|</span></li>
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${organizationsUrl}" class="nav-link main-nav">Организациям</a></li>
								<li class="nav-item d-none d-md-inline"><span
									class="nav-link nav-delimiter">|</span></li>
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${commentsUrl}" class="nav-link main-nav">Отзывы</a></li>
								<li class="nav-item d-none d-md-inline"><span
									class="nav-link nav-delimiter">|</span></li>
								<li class="nav-item ml-md-1 mt-1 mt-md-0"><a
									href="${blogUrl}" class="nav-link main-nav">Блог</a></li>
							</ul>
						</div>
					</div>
					<div id="cart" class="p-0 col text-right">
						<a href="#" class=""> <i class="fa fa-cart-arrow-down"> </i>
						</a> <span class="badge badge-light" id="totalQuantity">${cart.totalItemsQuantity}</span>
						<span class="badge badge-light" id="totalCost">Сумма:
							${cart.totalCost} руб.</span>
					</div>
				</div>
			</div>
		</nav>
	</div>
	<header>
		<div class="container mb-3">
			<div class="row justify-content-start align-items-center">
				<div class="col-12 col-md-6 p-0">
					<div class="container">
						<div class="row align-items-center justify-content-start">
							<div class="col-3 col-md-4">
								<img src="/images/qhp.png" class="rounded img-fluid ">
							</div>
							<div class="col-8 col-md-6 align-self-start">
								<h1 class="main-header">Обслуживание оргтехники</h1>
							</div>
						</div>
					</div>
				</div>
				<div class='col-12 col-md-6'>
					<div class="container">
						<div class="row justify-content-end">
							<div class="col">

								<div class="row justify-content-end align-items-center">
									<img src="/images/A1_logo.png" width="25" height="25"> <span
										class="phones ">+375(29)6125060</span>
								</div>
								<div class="row justify-content-end align-items-center">
									<img src="/images/viber_logo.png" width="25" height="25"
										class="rounded"> <span class="phones">+375(29)6715130</span>
								</div>
								<div class="row justify-content-end align-items-center mt-2">
									<img src="/images/support.svg" width="35" height="35"
										class="rounded call-icon">
									<button class="btn btn-outline btn-sm order-button"
										type="button" data-toggle="modal"
										data-target="#callOrderModal">Заказать звонок</button>
								</div>
								<div class="row justify-content-end mt-2">
									<span class="additionInfo">прием заявок через сайт в
										режиме 24/7</span>
								</div>
								<div class="row justify-content-end align-items-center">
									<img src="/images/timetable3.svg" class="timetable" width="25"
										height="25">
									<figure class="figure m-0 ml-1">
										<img src="/images/check-green.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">пн</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/check-green.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">вт</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/check-green.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">ср</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/check-green.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">чт</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/check-green.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">пт</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/quit-red.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">сб</figcaption>
									</figure>
									<figure class="figure m-0 ml-1">
										<img src="/images/quit-red.svg"
											class="figure-img img-fluid rounded" width="15">
										<figcaption class="figure-caption text-center">вс</figcaption>
									</figure>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</header>
	<main class="mb-1 mainContext">
		<div class="container">
			<div class="row mt-4 mb-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1 cart-header">
						<h2 class="my-header">Корзина</h2>
						<a class="btn clear-cart-btn p-1 px-2">Очистить</a>
					</div>
					<div>
						<div class="container mt-2 cart-block">
							<c:forEach var="item" items="${cart.cartItems}">
								<c:if test="${item.itemsQuantity gt 0}">
									<div class="row align-items-center justify-content-end mt-4">
										<div class="hidden">${item.printer.id}</div>
										<div
											class="col-3 col-md-2 col-lg-1 order-2 order-lg-1 p-0 text-right">
											<img class="rounded w-75" alt="printer image"
												src="/images/printers/${item.printer.imageLink}">
										</div>
										<div class="col-9 col-md-2 order-3 order-lg-2 text-center">
											<span>${item.printer.vendorName}
												${item.printer.modelName}</span>
										</div>
										<div
											class="col-9 col-md-2 col-lg-2  order-4 order-lg-3 mb-2 mb-md-0 text-center">
											<span>Заправка</span>
										</div>
										<div
											class="col-5 col-md-2 order-6 order-lg-4 text-left text-lg-right">
											<span class="cost-style">${item.currentPrice}<span>
													руб.</span></span>
										</div>
										<div class="col-4 col-md-1 order-5 text-right">
											<input
												class="form-check-input chip-checkboxes ${item.printer.originalCartridge.isChipped?'':'d-none'}"
												type="checkbox" id="chipCheckbox"
												${item.isChips?'checked':''}> <label
												class="form-check-label ${item.printer.originalCartridge.isChipped?'':'d-none'}"
												for="chipCheckbox">чип</label>
											<div class="hidden">${item.printer.originalCartridge.isChipped?item.printer.originalCartridge.chipCost:0}</div>
										</div>
										<div
											class="col-9 col-md-3 col-lg-1 order-7 order-lg-6  p-0 input-group justify-content-center px-2 px-lg-0 my-2">
											<div class="input-group-prepend">
												<button class="quantityValueBtn minusValueBtn px-0" disabled>-</button>
											</div>
											<input style="" value="${item.itemsQuantity}"
												class="form-control text-center quantityValueInput" disabled>
											<div class="input-group-append">
												<button class="quantityValueBtn plusValueBtn px-0">+</button>
											</div>
										</div>
										<div
											class="col-5 col-md-2 order-8 order-lg-7 text-left text-lg-center">
											<span class="cost-style cost-selector">${item.isChips?item.itemsQuantity*item.currentPrice+item.itemsQuantity*item.printer.originalCartridge.chipCost:item.itemsQuantity*item.currentPrice}<span>
													руб.</span></span>
										</div>
										<div class="col-12 col-lg-1 order-1 order-lg-8 mb-2 mb-lg-0">
											<button type="button"
												class="close removeItemButton float-left">
												<span>&times;</span>
											</button>
										</div>
									</div>
								</c:if>
								<c:if test="${item.isFlash}">
									<div class="row align-items-center justify-content-end mt-4">
										<div class="hidden">${item.printer.id}</div>
										<div
											class="col-3 col-md-2 col-lg-1 order-2 order-lg-1 p-0 text-right">
											<img class="rounded w-75" alt="printer image"
												src="/images/printers/${item.printer.imageLink}">
										</div>
										<div class="col-9 col-md-2 order-3 order-lg-2 text-center">
											<span>${item.printer.vendorName}
												${item.printer.modelName}</span>
										</div>
										<div
											class="col-9 col-md-2 col-lg-2  order-4 order-lg-3 mb-2 mb-md-0 text-center">
											<span>Прошивка</span>
										</div>
										<div
											class="col-5 col-md-2 order-6 order-lg-4 text-left text-lg-right">
											<span class="cost-style">${item.printer.reflashCost}<span>
													руб.</span></span>
										</div>
										<div class="col-4 col-md-1 order-5 text-right"></div>
										<div
											class="col-9 col-md-3 col-lg-1 order-7 order-lg-6  p-0 input-group justify-content-center px-2 px-lg-0 my-2">
											<div class="input-group-prepend">
												<button class="quantityValueBtn minusValueBtn px-0" disabled>-</button>
											</div>
											<input style="" value="1"
												class="form-control text-center quantityValueInput" disabled>
											<div class="input-group-append">
												<button class="quantityValueBtn plusValueBtn px-0">+</button>
											</div>
										</div>
										<div
											class="col-5 col-md-2 order-8 order-lg-7 text-left text-lg-center">
											<span class="cost-style cost-selector">${item.printer.reflashCost}<span>
													руб.</span></span>
										</div>
										<div class="col-12 col-lg-1 order-1 order-lg-8 mb-2 mb-lg-0">
											<button type="button"
												class="close removeItemButton float-left">
												<span>&times;</span>
											</button>
										</div>
									</div>
								</c:if>
							</c:forEach>
							<c:if test="${empty cart.cartItems}">
								<div class="row  mt-4 align-items-center" style="height: 10rem">
									<div class="col text-center">
										<span>корзина пуста</span>
									</div>

								</div>
							</c:if>
							<div class="row align-items-center my-3">
								<div class="col-12 col-md-6 text-left my-3 my-md-0">
									<span class="cost-style"><span>Итого: </span>${(cart.totalCost==null||empty cart.totalCost)?0:cart.totalCost}<span>
											руб.</span></span>
								</div>
								<div class="col-12 col-md-6 text-right">
									<a href="#orderFormLink" class="mr-2 btn order-button" id="orderFormLink">Оформить
										заказ</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row mt-4 mb-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1">
						<h2 class="my-header">Оформление заказа</h2>
					</div>
					<div>
						<div class="container mt-2">
							<div class="row align-items-start">
								<form class="order-form container">
									<div class="form-row mt-3">
										<div class="form-group col-md-6">
											<label for="customerName" class="required">Имя(Фамилия):</label>
											<input placeholder="Введите Имя" required="required"
												class="input-element form-control" id="customerNameInput">
											<div class="invalid-feedback"></div>
										</div>
										<div class="form-group col-md-6">
											<label for="phoneNumber" class="required">Телефон:</label> <input
												type="tel" placeholder="+375()" required="required"
												class="input-element form-control" id="phoneNumberInput">
											<div class="invalid-feedback"></div>
										</div>
									</div>
									<div class="form-row">
										<div class="form-group col-12 mt-2">
											<label for="customerAddress" class="required">Адрес:</label>
											<input placeholder="Введите адрес"
												class="input-element form-control" required="required"
												id="customerAddressInput">
											<div class="invalid-feedback"></div>
										</div>
										<div class="form-group col-12 mt-2">
											<label for="addInfo">Дополнительная информация:</label>
											<textarea rows="5"
												placeholder="Удобное время визита, как найти, описание проблемы и т.д."
												class="input-element form-control" id="addInfoInput"></textarea>
											<div class="invalid-feedback"></div>
										</div>
									</div>
								</form>
							</div>
							<div class="row align-items-center my-3">
								<div class="col-12 text-right">
									<button class="mr-2 btn order-button" id="orderSubbmit">Отправить</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<div class="container">
			<div class="row mt-4">
				<div class="col text-right mt-3">
					<span>Поделиться:</span>
				</div>
			</div>
			<div class="row mb-4">
				<div class="col text-right mb-2">
					<a href="https://vk.com/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="vk.com" target="_blank"> <img
						src="/images/vk_icon.svg" alt="" class="img-fluid rounded"
						width="25">
					</a> <a href="https://ok.ru/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="ok.ru" target="_blank"> <img
						src="/images/odnoklassniki_icon.svg" alt=""
						class="img-fluid rounded" width="25">
					</a> <a href="https://www.facebook.com/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="facebook.com" target="_blank"> <img
						src="/images/facebook_icon.svg" alt="" class="img-fluid rounded"
						width="25">
					</a> <a href="https://www.instagram.com/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="instagram.com" target="_blank"> <img
						src="/images/instagram_icon.svg" alt="" class="img-fluid rounded"
						width="25">
					</a> <a href="https://twitter.com/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="twitter.com" target="_blank"> <img
						src="/images/twitter_icon.svg" alt="" class="img-fluid rounded"
						width="25">
					</a> <a href="https://telegram.org/" class="btn p-0 shareLink"
						data-toggle="tooltip" title="telegram.org" target="_blank"> <img
						src="/images/telegram_icon.svg" alt="" class="img-fluid rounded"
						width="25">
					</a>
				</div>
			</div>
			<div class="row">
				<div class="col text-center mb-2">
					<span>&#169; 2020-2025 www.qhp.by - Все права защищены.</span>
				</div>
			</div>
		</div>
	</footer>
	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>
	<script src="<c:url value="/js/jquery.dotdotdot.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/custom-validation.js"/>"></script>
	<script src="<c:url value="/js/jquery.maskedinput.min.js"/>"></script>
	<script src="<c:url value="/js/common.js"/>"></script>
	<script src="<c:url value="/js/ajax-requests.js"/>"></script>	
	<script src="<c:url value="/js/cart.js"/>"></script>	
</body>

</html>