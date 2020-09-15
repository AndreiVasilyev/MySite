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
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" href="<c:url value="/css/common.css"/>">
<link rel="stylesheet" href="<c:url value="/css/conditions.css"/>">

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
						<a href="/cart"> 
							<i class="fa fa-cart-arrow-down">								
							</i>							
						</a>
						<span class="badge badge-light" id="totalQuantity">${cart.totalItemsQuantity}</span>
						<span class="badge badge-light" id="totalCost">Сумма: ${cart.totalCost} руб.</span>
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
			<div class="row mt-4 mb-5">
				<h1 class="col-12 text-center">КАК МЫ РАБОТАЕМ?</h1>
				<h3 class="col-12 text-center">ВСЁ ОЧЕНЬ ПРОСТО!</h3>
				<div class="col col-md-12 mt-4 p-5 scheme">
					<div class="row align-items-center">
						<div class="col-12 col-md text-center align-self-start p-0">
							<i class="fa fa-bell scheme-icons"></i>
							<p class="scheme-text">звонок или заявка</p>
						</div>
						<div class="col-12 col-md text-center p-0">
							<i
								class="fa fa-caret-right scheme-icons d-none d-md-inline-block"></i>
							<i class="fa fa-caret-down scheme-icons d-md-none"></i>
						</div>
						<div class="col-12 col-md text-center align-self-start p-0">
							<i class="fa fa-question-circle scheme-icons"></i>
							<p class="scheme-text">согласование</p>
						</div>
						<div class="col-12 col-md text-center p-0">
							<i
								class="fa fa-caret-right scheme-icons d-none d-md-inline-block"></i>
							<i class="fa fa-caret-down scheme-icons d-md-none"></i>
						</div>
						<div class="col-12 col-md text-center align-self-start p-0">
							<i class="fa fa-truck scheme-icons"></i>
							<p class="scheme-text">выезд</p>
						</div>
						<div class="col-12 col-md text-center p-0">
							<i
								class="fa fa-caret-right scheme-icons d-none d-md-inline-block"></i>
							<i class="fa fa-caret-down scheme-icons d-md-none"></i>
						</div>
						<div class="col-12 col-md text-center align-self-start p-0">
							<i class="fa fa-wrench scheme-icons"></i>
							<p class="scheme-text">выполнение работ</p>
						</div>
						<div class="col-12 col-md text-center p-0">
							<i
								class="fa fa-caret-right scheme-icons d-none d-md-inline-block"></i>
							<i class="fa fa-caret-down scheme-icons d-md-none"></i>
						</div>
						<div class="col-12 col-md text-center align-self-start p-0">
							<i class="fa fa-thumbs-up scheme-icons"></i>
							<p class="scheme-text">оформление и расчет</p>
						</div>
					</div>
				</div>
				<div class="col-12 mt-4">
					<div class="row">
						<div class="col-12 col-md-6 order-2 order-md-1 mt-4 mt-md-0">
							<i class="fa fa-bell d-block float-left scheme-icons"></i>
							<h3 class="m-0">Шаг №1:</h3>
							<h4>звонок или заявка</h4>
							<div class="clearfix"></div>
							<p class="m-0 my-text-block">Вы связываетесь с нами наиболее удобным для
								Вас способом:</p>
							<ul>
								<li>телефонный звонок в любой рабочий день с 8-00 до 20-00;</li>
								<li>оставляете заявку через сайт или сообщение в Viber в
									любое время в режиме 24/7;</li>
							</ul>
							<p class="m-0 my-text-block">В рабочее время мы реагируем на Вашу заявку в течение 1 -
								4 часов. При получении заявки в нерабочее время, мы свяжемся с
								Вами в первые часы следующего рабочего дня.</p>
						</div>
						<div class="col-12 col-md-6 order-1 order-md-2">
							<img class="img-fluid" alt="contact us"
								src="/images/contact-us.jpg">
						</div>
					</div>
				</div>
				<div class="col-12 mt-4">
					<div class="row">
						<div class="col-12 col-md-6">
							<img class="img-fluid" alt="contact us" src="/images/woman.jpg">
						</div>
						<div class="col-12 col-md-6 mt-4 mt-md-0">
							<i class="fa fa-question-circle scheme-icons float-left"></i>
							<h3 class="m-0">Шаг №2:</h3>
							<h4>согласование</h4>
							<div class="clearfix"></div>
							<p class="m-0 my-text-block">Процесс согласования включает в себя уточнение
								всех вопросов по заявленной Вами проблеме с техникой, адреса,
								времени визита и ориентировочной стоимости ремонта с нашей
								стороны, и ответы на все возникшие у Вас вопросы.</p>
							<ul>
								<li>в среднем, согласованная заявка исполняется в течение
									1-4 часов;</li>
								<li>максимальный срок исполнения заявки составляет 48
									часов;</li>
								<li>время визита зависит от текущей загрузки специалиста;</li>
								<li>клиеты, заключившие договор на абонентское обслуживание
									с соответствующими условиями, имеют приоритет по срокам
									реагирования;</li>
								<li>срок исполнения заказ может быть увеличен в связи с
									необходимостью заказа соответствующих запчастей и
									согласовывыется с заказчиком дополнительно;</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-12 mt-4">
					<div class="row">
						<div class="col-12 col-md-6 order-2 order-md-1 mt-4 mt-md-0">
							<i class="fa fa-truck scheme-icons float-left"></i>
							<h3 class="m-0">Шаг №3:</h3>
							<h4>выезд</h4>
							<div class="clearfix"></div>
							<p class="m-0 my-text-block">В согласованный ранее промежуток времени
								специалист приезжает к Вам для проведения диагностики и
								выполнения работ. Выезд к заказчику осуществляется абсолютно
								бесплатно.</p>
							<p class="m-0 my-text-block">В случае отсутствия заказчика по различным форс-можорным
								обстоятельствам в оговоренное время на месте, заказчик обязан
								уведомить нас по телефону. В противном случае, при дальнейшем
								сотрудничестве, оставляем за собой право требовать оплаты за
								сорванный по вине заказчика выезд специалиста.</p>
						</div>
						<div class="col-12 col-md-6 order-1 order-md-2">
							<img class="img-fluid" alt="depature" src="/images/car.jpg">
						</div>
					</div>
				</div>
				<div class="col-12 mt-4">
					<div class="row">
						<div class="col-12 col-md-6">
							<img class="img-fluid" alt="completing the work"
								src="/images/copier.jpg">
						</div>
						<div class="col-12 col-md-6 mt-4 mt-md-0">
							<i class="fa fa-wrench scheme-icons float-left"></i>
							<h3 class="m-0">Шаг №4:</h3>
							<h4>выполнение работ</h4>
							<div class="clearfix"></div>
							<p class="m-0 my-text-block">Выполнение работ происходит в несколько
								этапов:</p>
							<ul>
								<li>диагностика - производится осмотр техники, выясняется
									полный список необходимых мероприятий по устранению всех
									заявленных дефектов;</li>
								<li>окончательное согласование - согласовывается
									окончательная стоимость услуг;</li>
								<li>выполнение работы - производятся все необходимые
									ремонтные работы;</li>
							</ul>
							<p class="m-0 my-text-block">В случае невозможности выполнения всех работ в силу
								необходимости заказа деталей или более глубокой диагностики в
								условиях стационарной мастерской, проводится соответствующее
								согласование с заказчиком по всем условиям и срокам. Доставка
								техники в стационар и обратно, в случае согласования с
								заказчиком, выполняется абсолютно бесплатно.</p>
						</div>
					</div>
				</div>
				<div class="col-12 mt-4">
					<div class="row">
						<div class="col-12 col-md-6 order-2 order-md-1 mt-4 mt-md-0">
							<i class="fa fa-thumbs-up scheme-icons float-left"></i>
							<h3 class="m-0">Шаг №5:</h3>
							<h4>оформление и расчет</h4>
							<div class="clearfix"></div>
							<p class="m-0 my-text-block">Проводится выписка всех необходимых документов и выполняется окончательный расчет по всем выполненным услугам.</p>
							
						</div>
						<div class="col-12 col-md-6 order-1 order-md-2">
							<img class="img-fluid" alt="payment" src="/images/contract.jpg">
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
</body>

</html>