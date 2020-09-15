<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/css/common.css"/>">
<link rel="stylesheet" href="<c:url value="/css/index.css"/>">
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
	<div id="commentModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header my-modal-border">
					<h4 class="modal-title float-left font-italic font-weight-light"></h4>
					<button type="button" class="close" data-dismiss="modal">
						<span>&times;</span>
					</button>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer my-modal-border">
					<div class="text-right font-weight-bold" id="author"></div>
					<div class="text-right font-italic" id="company"></div>
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
			<div class="row mt-4">
				<div id="carousel" class="carousel slide col-12 p-0"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel" data-slide-to="0" class="active"></li>
						<li data-target="#carousel" data-slide-to="1"></li>
						<li data-target="#carousel" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner my-border">
						<div class="carousel-item active">
							<img src="/images/printer.jpg" class="d-block img-fluid"
								alt="first slide">
							<div class="carousel-caption d-block">
								<h5 class="">Обслуживание лазерной цветной и черно-белой
									оргтехники</h5>
								<p>заправка, ремонт, техобслуживание, прошивка принтеров и
									копировальных аппаратов</p>
							</div>
						</div>
						<div class="carousel-item">
							<img src="/images/toners.jpg " class="d-block img-fluid"
								alt="second slide">
							<div class="carousel-caption d-block">
								<h5>Высококачественные тонеры и оригинальные запчасти</h5>
								<p>Тонеры известных мировых марок IMEX, Tomoegawa, Catun,
									HiBlack и т.д., оригинальные запчасти HP, Canon, Samsung,
									Kyocera</p>
							</div>
						</div>
						<div class="carousel-item">
							<img src="/images/deal2.jpg" class="d-block img-fluid"
								alt="third slide">
							<div class="carousel-caption d-block">
								<h5>Обслуживание малых офисов и больших предприятий</h5>
								<p>Гибкие условия ценообразования и удобные индивидуальные
									схемы взаимодействия с предприятиями разных размеров</p>
							</div>
						</div>
					</div>
					<a href="#carousel" class="carousel-control-prev" role="button"
						data-slide="prev">
						<div class="carousel-control-icon-bg">
							<span class="carousel-control-prev-icon" area-hidden="true"></span>
						</div>
					</a> <a href="#carousel" class="carousel-control-next" role="button"
						data-slide="next">
						<div class="carousel-control-icon-bg">
							<span class="carousel-control-next-icon"></span>
						</div>
					</a>
				</div>
			</div>
			<div class="row mt-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1">
						<h2 class="my-header">Поможем</h2>
					</div>
					<div class="p-2">
						<p class="about">Уважаемый заказчик! Мы всегда рады помочь Вам
							в вопросах обслуживании любой оргтехники в г.Бобруйске и
							прилегающих районных центрах. Опыт работы в данной сфере
							составляет более 6 лет. Выезд к заказчику и доставка техники в
							сервис и обратно выполняется абсолютно бесплатно. Оказываемые
							нами услуги:</p>
						<ul class="services-list">
							<li>заправка, обслуживание, восстановление картриджей
								лазерных черно-белых и цветных принтеров и копировальных
								аппаратов</li>
							<li>ремонт и техобслуживание принтеров, копировальных
								аппаратов, факсов</li>
							<li>прошивка или замена чипов в принтерах и копироваальных
								аппаратах</li>
							<li>заключение договоров на обслуживание офисов и больших
								предприятий</li>
							<li>консультация по выбору и покупке печатной техники</li>
						</ul>
						<div class="w-100 text-right my-link-bg">
							<a href="${organizationsUrl}" class="my-links badge">Информация для предприятий &#8594;</a>
						</div>

					</div>
				</div>
			</div>
			<div class="row mt-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1">
						<h2 class="my-header">Наши преимущества</h2>
					</div>
					<div class="p-2">
						<div class="container">
							<div class="row">
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/quality.svg" width="35" alt=""
											class="d-inline">
										<h6 class="advantege-header">Качество</h6>
									</div>
									<p class="advantage-text">используем оригинальные запчасти,
										высококачественный тонер и расходные материалы,
										специализированное оборудование, специалисты имеют высокий
										уровень квалификации</p>
								</div>
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/reliability.svg" width="35" alt=""
											class="d-inline">
										<h6 class="advantege-header">Надежность</h6>
									</div>
									<p class="advantage-text">более 6 лет опыта работы на
										рынке, стабильные отношения с постоянными клиентами, гарантия
										на производимые работы, оперативное решение всех нештатных
										ситуаций</p>
								</div>
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/speed.svg" width="35" alt="" class="d-inline">
										<h6 class="advantege-header">Скорость</h6>
									</div>
									<p class="advantage-text">прием заявок через сайт в режиме
										24/7, реакция на заказанный звонок в срок не более 2 часов,
										большинство заявок закрываются в течение 4 часов с момента
										поступления</p>
								</div>
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/interest.svg" width="35" alt=""
											class="d-inline">
										<h6 class="advantege-header">Экономия</h6>
									</div>
									<p class="advantage-text">честная заправка согласно
										спецификаций, бесплатный выезд специалиста, бесплатныя
										доставка техники для ремонта в стационаре в обе стороны,
										экономия времени и сил на поездки в сервис</p>
								</div>
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/variable.svg" width="35" alt=""
											class="d-inline">
										<h6 class="advantege-header">Гибкость</h6>
									</div>
									<p class="advantage-text">действует гибкая система скидок в
										зависимости от объема предоставляемых работ, возможны
										различные, удобные для конкретного заказчика, схемы работы,
										которые обсуждаются при заключении договора</p>
								</div>
								<div class="col-12 col-md-4 my-subborder">
									<div class="w-100">
										<img src="/images/responsiability.svg" width="35" alt=""
											class="d-inline">
										<h6 class="advantege-header">Отзывчивость</h6>
									</div>
									<p class="advantage-text">оказываем квалифицированную
										помощь в консультировании по выбору и покупке оргтехники и
										расходных материалов в зависимости от стоящих задач</p>
								</div>
							</div>
						</div>
						<div class="w-100 text-right my-link-bg mt-2">
							<a href="${conditionsUrl}" class="my-links badge">Полные условия
								обслуживания &#8594;</a>
						</div>
					</div>

				</div>
			</div>
			<div class="row mt-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1">
						<h2 class="my-header">Обслуживаемые брэнды</h2>
					</div>
					<div class="p-2 ">
						<img src="/images/brandsLogo.png" class="img-fluid"
							alt="brands-logo">
						<div class="w-100 text-right my-link-bg">
							<a href="${costsUrl}" class="my-links badge">Узнать цену &#8594;</a>
						</div>

					</div>
				</div>
			</div>

			<div class="row mt-4 mb-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1 feedback-header">
						<h2 class="my-header">Отзывы заказчиков</h2>
						<a href="${commentsUrl}#newCommentLink"
							class="btn add-feedback-btn p-1" data-toggle="tooltip"
							data-placement="bottom" title="Добавить отзыв"><img
							src="/images/add_comment.png" class="img-fluid"
							alt="add_feedback"></a>
					</div>
					<div>
						<div class="container mt-2">
							<div class="row align-items-start">
								<c:forEach var="index" begin="0" end="3">
									<fmt:formatDate var="formatedDate"
										value="${allComments[index].dateMessage}" pattern="dd/MM/yyyy" />
									<div class="col-12 col-lg-3 p-0 mb-4 mb-lg-0">
										<div class="container">
											<div class="row">
												<div class=" col-12 commentDate font-italic mb-2">${formatedDate}</div>
												<div
													class="col-12 commentText p-2 ${index lt 3?'comment-border':''}">${allComments[index].message}
													<a href="#commentModal" class="commentLink"
														data-message="${allComments[index].message}"
														data-date="${formatedDate}"
														data-author="${allComments[index].authorName}"
														data-company="${allComments[index].companyName}"><i
														class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
												</div>
												<div
													class="col-12 commentAuthor pt-2 pr-2 text-right font-weight-bold">${allComments[index].authorName}</div>
												<div
													class="col-12 commentCompany pr-2 text-right font-italic">${allComments[index].companyName}</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>

						<div class="w-100 text-right my-link-bg mt-2">
							<a href="${commentsUrl}" class="my-links mr-2 badge btn">Все
								отзывы &#8594;</a>
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
	<script src="<c:url value="/js/index.js"/>"></script>
</body>

</html>