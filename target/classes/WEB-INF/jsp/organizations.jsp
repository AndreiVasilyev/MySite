<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/css/bootstrap.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/organizations.css">
<link rel="stylesheet" href="/css/font-awesome.css">
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
					<p id='responseText'>${(not empty errorMessage)?errorMessage:''}</p>
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
						<a href="/cart"> <i class="fa fa-cart-arrow-down"> </i>
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
			<div class="row my-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1">
						<h2 class="my-header">Оранизациям</h2>
					</div>
					<div class="p-4">
						<p class="text-indent">Обслуживание организаций является
							основным и приоритетным напрвлением деятельности нашей компании.
							Мы рады видеть в числе наших клиентов предприятия различных
							размеров и с различным парком печатной техники. Для каждой
							организации мы можем подобрать наиболее комфортный режим
							сотрудничества исходя из имеющихся требований. И, конечно же, для
							всех корпоративных клиентов действует уникальная, особая и гибкая
							система ценообразования. Ниже приведем основные факторы, которые
							влияют на качество предоставляемых услуг и их стоимость, которые
							каждый заказчик может подобрать и настроить под свои нужды и
							пожелания:</p>
						<ul>
							<li><strong>приоритетность в очереди исполнения
									заявок</strong>. Насмотря на достаточно короткий срок исполнения заказа
								для любого нашего клиента, при заключении договора мы можем
								изменить срок и условия реагирования на заявку, в том числе и в
								случае аварийного выезда;</li>
							<li><strong>ежемесячное составление Акта
									выполненных работ</strong>. Для крупных заказчиков мы практикуем схему
								работы с ежемесячным составлением Акты выполненных работ. Это
								сокращает документооборот и является дополнительной отсрочкой по
								оплате услуг для наших клиентов;</li>
							<li><strong>отсрочка по оплате услуг</strong>. В зависимости
								от ежемесячных объемов обслуживаемых единиц техники, мы можем
								предоставить различные сроки по отсрочке оплаты оказанных услуг
								с момента подписания Акта выполненных работ;</li>
							<li><strong>подменный фонд при ремонте техники</strong>. В
								случае необходимости длительного ремонта печатной техники, для
								наших постоянных клиентов эта опция предоставляется на
								бесплатной основе. Все детали, сроки, цены на данную услугу мы
								можем дополнительно оговаривать в заключаемых договорах;</li>
							<li><strong>полный контроль за печатным процессом
									на предприятии</strong>. Мы можем взять на себя все заботы по
								обеспечению бесперебыойности печатного процесса на предприятии,
								начиная от регламентного обслуживания картриджей и печатной
								техники, заканчивая сложным ремонтом любых блоков и узлов. Со
								стороны клиента необходимо только обеспечить доступ к парку
								печатной техники. Весь порядок такого сотрудничества
								оговаривается индивидуально с каждым заказчиком и учитывает все
								его условия и пожелания.</li>
							<li><strong>качество используемых расходных
									материалов и запчастей</strong>. Беспокоясь о своей репутации, в
								повседневной работе мы используем расходные материалы и запчасти
								самого лучшего качества от ведущих мировых производителей <strong>IMEX,
									Tomoegawa, Static Control, Mitsubishi, HiBlack</strong>. При этом,
								естественно, что стоимость таких услуг будет несколько выше, чем
								при использовании материалов других, менее известных
								производителей. Поэтому для наших заказчиков, которые ограничены
								в бюджете но хотят сотрудничать с нами и готовы на
								соответствующие компромиссы, мы всегда готовы предложить более
								бюджетные варианты по стоимости обслуживания с минимальными
								потерями в качестве;</li>
							<li><strong>срочные и аварийные выезды к заказчику
									исходя из режима его работы</strong>. Мы с пониманием относимся к тому,
								что многие предприятия устанавливают режим работы, выходящий за
								рамки общепринятого рабочего дня, включая выходные и праздники.
								Поэтому мы готовы оговаривать условия взаимовыгодного
								сотрудничества исходя из условий режима работы наших заказчиков.</li>
						</ul>
						<p class="text-indent">При необходимости приобрести или
							заменить оргтехнику, всех заказчиков, заключивших договора на
							обслуживание, мы бесплатно консультируем по оптимальной покупке
							печатной техники, а так же оказываем помощь в поиске поставщиков.
							Эта, небольшая на первый взгляд, услуга может в дальнейшем
							сэкономить значительные ресурсы предприятия.</p>
						<p class="text-indent">
							Кроме того, мы всегда готовы принять участие в проводимых
							тендерах и конкурсах на заключение договоров по обслуживанию
							оргтехники. Ваши приглашения можно отправить по контактам,
							представленным на сайте выше, или на <a href=""
								class="email-form-link">электронную почту</a>, заполнив форму
							ниже на этой странице.
						</p>
					</div>
				</div>
			</div>
			<div class="row mb-4 my-border">
				<div class="col-12 p-0">
					<div class="my-header-bg text-center p-1 feedback-header">
						<h2 class="my-header">Оставить сообщение</h2>
					</div>
					<spring:form action="/organizations/email"  modelAttribute="email" method="post" id="newMessageForm"
						class="needs-validation my-4 col-11 mx-auto"
						novalidate="novalidate" enctype="multipart/form-data">
						<div class="form-group row ">
							<label for="inputTitle" class="col-2 input-label required text-right">Тема:</label>
							<div class="col-10">
								<spring:input type="text" class="form-control" id="inputTitle"
									placeholder="Тема сообщения" required="required" path="title"/>
								<div class="invalid-feedback"></div>
								<spring:errors path="title" cssClass="error"></spring:errors>
							</div>
						</div>
						<div class="form-group row">
							<label for="inputMessage" class="col-2 input-label required text-right">Сообщение:</label>
							<div class="col-10">
								<spring:textarea rows="5" cols="" class="form-control" path="message"
									id="inputMessage" placeholder="Сообщение" required="required"></spring:textarea>
								<div class="invalid-feedback"></div>
								<spring:errors path="message" cssClass="error"></spring:errors>
							</div>
						</div>
						<div class="form-group row">							
							<div class="col-2 text-right">
							<label for="inputFile" class="attach-label text-center">
								<i class="fa fa-paperclip"></i>
								<span>Файлы</span>
								<spring:input type="file" id="inputFile" multiple="multiple" path="files"/>								
							</label>
							</div>
							<div class="file-names col-10">
							
							</div>
						</div>
						<div class="form-group row">
							<div class="col-12 text-right">
								<input type="submit" class="btn order-button" id="formSubbmit"
									value="Отправить">
							</div>
						</div>
					</spring:form>
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
	<script src="/js/jquery-3.0.0.min.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.js"></script>
	<script src="/js/jquery.dotdotdot.js" type="text/javascript"></script>
	<script src="/js/custom-validation.js"></script>
	<script src="/js/jquery.maskedinput.min.js"></script>
	<script src="/js/common.js"></script>
	<script src="/js/organizations.js"></script> 

</body>

</html>