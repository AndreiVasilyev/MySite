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
<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>">
<link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>">
<link rel="stylesheet" href="<c:url value="/css/admin.css"/>">
<title>Insert title here</title>
</head>
<body>
	<c:url var="saveNewPrinterUrl" value="/admin/printers/save"></c:url>
	<c:url var="mainAdminPageUrl" value="/admin"></c:url>
	<div class="container">
		<spring:form action="${saveNewPrinterUrl}" method="post"
			modelAttribute="newPrinter" id="newPrinterForm"
			class="needs-validation my-4 mx-auto" enctype="multipart/form-data">
			<div class="form-group row ">
				<div class="col-12 text-center text-primary">
					<h3>Добавить/изменить принтер</h3>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputId" class="col-2 col-form-label">ID:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputId" path="id" value="${newPrinter.id}" readonly="true" />
				</div>
			</div>
			<div class="form-group row">
				<label for="inputVendorName" class="col-2 col-form-label">Производитель:</label>
				<div class="col-10">
					<spring:select class="form-control border-success"
						id="inputVendorName" required="required" path="vendorName">
						<c:if test="${empty newPrinter.id}">
							<spring:option value="">--Выбрать--</spring:option>
							<spring:options items="${allVendors}" />
						</c:if>
						<c:if test="${not empty newPrinter.id}">
							<c:forEach var="vendor" items="${allVendors}">
								<c:if test="${vendor eq newPrinter.vendorName}">
									<spring:option value="${vendor}" selected="true"></spring:option>
								</c:if>
								<c:if test="${vendor ne newPrinter.vendorName}">
									<spring:option value="${vendor}"></spring:option>
								</c:if>
							</c:forEach>

						</c:if>
					</spring:select>
					<spring:errors path="vendorName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row ">
				<label for="inputModelName" class="col-2 col-form-label">Модель:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputModelName" placeholder="Модель" path="modelName"
						required="required" value="${newPrinter.modelName}" />
					<spring:errors path="modelName" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputImageLink" class="col-2 col-form-label">Изображение:</label>
				<div class="col-1" id="imageContainer">
					<c:if test="${not empty newPrinter.id}">
						<img alt="${newPrinter.imageLink}" src="/images/printers/${newPrinter.imageLink}" height="50" width="50">
					</c:if>					
				</div>
				<div class="col">
					<spring:input type="text" class="form-control border-success"
						id="inputImageLink" placeholder="Изображение" path="imageLink"
						value="${newPrinter.imageLink}" />
					<spring:errors path="imageLink" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<div class="col-auto">
					<label for="attachImageLink"
						class="form-control border-success attach-label text-info font-weight-bold text-uppercase">
						<i class="fa fa-paperclip"></i> Файл <input type="file" class=""
						id="attachImageLink" name="imageFile" accept=".jpg"/>
					</label>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputIsFlashable" class="col-2 col-form-label">Необходимость
					прошивки:</label>
				<div class="col-4 justify-content-start">
					<c:if test="${newPrinter.isReflashable}">
						<spring:checkbox class="form-control form-checkbox"
							id="inputIsFlashable" path="isReflashable" checked="true"></spring:checkbox>
					</c:if>
					<c:if test="${not newPrinter.isReflashable}">
						<spring:checkbox class="form-control form-checkbox"
							id="inputIsFlashable" path="isReflashable"></spring:checkbox>
					</c:if>
					<spring:errors path="isReflashable" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
				<label for="inputPopularityRating" class="col-2 col-form-label">Рейтинг
					популярности:</label>
				<div class="col-4">
					<spring:input type="text" class="form-control border-success"
						id="inputPopularityRating" placeholder="Рейтинг"
						path="popularityRating" value="${newPrinter.popularityRating}" />
					<spring:errors path="popularityRating" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputReflashCost" class="col-2 col-form-label">Стоимость
					прошивки:</label>
				<div class="col-10">
					<spring:input type="text" class="form-control border-success"
						id="inputReflashCost" placeholder="стоимость прошивки"
						path="reflashCost" value="${newPrinter.reflashCost}"></spring:input>
					<spring:errors path="reflashCost" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputOriginalCartridge" class="col-2 col-form-label">Оригинальный
					картридж:</label>
				<div class="col-10">
					<spring:select class="form-control border-success"
						id="inputOriginalCartridge" path="originalCartridge">
						<c:if test="${empty newPrinter.id}">
							<spring:option value="" label="--Выбрать--"></spring:option>
							<spring:options items="${allCartridges}" itemValue="id"
								itemLabel="modelName" />
						</c:if>
						<c:if test="${not empty newPrinter.id}">
							<c:forEach var="cartridge" items="${allCartridges}">
								<c:if
									test="${cartridge.modelName eq newPrinter.originalCartridge.modelName}">
									<spring:option value="${cartridge.id}"
										label="${cartridge.modelName}" selected="true"></spring:option>
								</c:if>
								<c:if
									test="${cartridge.modelName ne newPrinter.originalCartridge.modelName}">
									<spring:option value="${cartridge.id}"
										label="${cartridge.modelName}"></spring:option>
								</c:if>
							</c:forEach>
						</c:if>
					</spring:select>
					<spring:errors path="originalCartridge" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputСompatibleCartridges" class="col-2 col-form-label">Совместимые
					картриджи:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allСompatibleCartridges"
						path="">
						<spring:options items="${allCartridges}" itemLabel="modelName"
							itemValue="id" />
					</spring:select>
				</div>
				<div class="col-auto p-0 align-self-center">
					<a class="btn btn-success text-white d-block mb-2"><i
						class="fa fa-angle-double-right"></i></a> <a
						class="btn btn-danger text-white d-block"><i
						class="fa fa-angle-double-left"></i></a>
				</div>
				<div class="col">
					<spring:select multiple="true" size="10"
						class="form-control text-center border-success"
						id="inputСompatibleCartridges" path="compatibleCartridgesId">
						<c:if test="${not empty newPrinter.id}">
							<spring:options items="${newPrinter.compatibleCartridges}"
								itemLabel="modelName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="compatibleCartridgesId" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>
				</div>
			</div>
			<div class="form-group row">
				<label for="inputSpareParts" class="col-2 col-form-label">Запчасти:</label>
				<div class="col-4">
					<spring:select multiple="true" size="10"
						class="form-control text-center" id="allSpareParts" path="">
						<spring:options items="${allSpareParts}" itemLabel="sparePartName"
							itemValue="id" />
					</spring:select>
				</div>
				<div class="col-auto p-0 align-self-center">
					<a class="btn btn-success text-white d-block mb-2"><i
						class="fa fa-angle-double-right"></i></a> <a
						class="btn btn-danger text-white d-block"><i
						class="fa fa-angle-double-left"></i></a>
				</div>
				<div class="col">
					<spring:select multiple="true" size="10"
						class="form-control text-center border-success"
						id="inputSpareParts" path="sparePartsId">
						<c:if test="${not empty newPrinter.id}">
							<spring:options items="${newPrinter.spareParts}"
								itemLabel="sparePartName" itemValue="id" />
						</c:if>
					</spring:select>
					<spring:errors path="spareParts" cssClass="error"
						delimiter="<br/>&#8226;&#32;"></spring:errors>

				</div>
			</div>
			<div class="form-group row justify-content-end">
				<div class="col-auto">
					<input type="submit" class="btn btn-primary"
						id="newPrinterSubbmitButton" value="Сохранить">
				</div>
				<div class="col-auto">
					<a class="btn btn-secondary" href="${mainAdminPageUrl}">Назад</a>
				</div>
			</div>
		</spring:form>
	</div>
	<script src="<c:url value="/js/jquery-3.0.0.min.js"/>"></script>
	<script src="<c:url value="/js/popper.min.js"/>"></script>
	<script src="<c:url value="/js/bootstrap.js"/>"></script>	
	<script src="<c:url value="/js/admin-printer.js"/>"></script>
	<script src="<c:url value="/js/admin-add-common.js"/>"></script>
	<script src="<c:url value="/js/admin-ajax.js"/>"></script>
</body>
</html>